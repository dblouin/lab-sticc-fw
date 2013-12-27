/*******************************************************************************
 * Copyright (c) 2011 Lab-STICC Universite de Bretagne Sud, Lorient.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the CeCILL-B license available
 * at :
 * en : http://www.cecill.info/licences/Licence_CeCILL-B_V1-en.html
 * fr : http://www.cecill.info/licences/Licence_CeCILL-B_V1-fr.html
 * 
 * Contributors:
 * Dominique BLOUIN (Lab-STICC UBS), dominique.blouin@univ-ubs.fr
 ******************************************************************************/
package fr.labsticc.framework.ide.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.ManifestElement;
import org.eclipse.ui.IEditorPart;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;

/**
 * Utility class for Eclipse plugin related operations.
 * 
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique Blouin</a>
 */
public class PluginUtil {

	public static String getPluginCanonicalEntryPath(final String p_pluginId,
			final String p_entry) throws IOException {
		final File file = getPluginEntryFile(p_pluginId, p_entry);

		return file == null ? null : file.getCanonicalPath();
	}

	public static File getPluginEntryFile(final String p_pluginId,
			final String p_entry) throws IOException {
		final Bundle extResBundle = Platform.getBundle(p_pluginId);

		if (extResBundle == null) {
			throw new NullPointerException("Bundle for plugin " + p_pluginId
					+ " not found!");
		}

		final URL entry = extResBundle.getEntry(p_entry);

		if (entry == null) {
			return null;
		}

		final String path = FileLocator.toFileURL(entry).getPath();

		return new File(path);
	}

	public static String getAbsoluteFileName(final String p_fileName,
			final Bundle p_bundle) throws IOException {
		try {
			URL fileUrl = FileLocator
					.find(p_bundle, new Path(p_fileName), null);
			String result = FileLocator.toFileURL(fileUrl).getPath().toString();

			return result;
		} catch (final NullPointerException p_ex) {
			throw new FileNotFoundException("File " + p_fileName
					+ " not found.");
		}
	}

	public static boolean isPluginPresent(final String p_pluginId) {
		return Platform.getBundle(p_pluginId) != null;
	}

	public static List<IExtension> findDependentExtensions(
			final IExtension p_ext) throws BundleException {
		final String requireBundle = (String) Platform
				.getBundle(p_ext.getNamespaceIdentifier()).getHeaders()
				.get(Constants.REQUIRE_BUNDLE);
		final ManifestElement[] elements = ManifestElement.parseHeader(
				Constants.BUNDLE_CLASSPATH, requireBundle);
		final IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
				.getExtensionPoint(p_ext.getExtensionPointUniqueIdentifier());
		final List<IExtension> dependencies = new ArrayList<IExtension>();

		for (final ManifestElement manifestElement : elements) {
			for (final IExtension depExt : findExtensionsDeclaredInBundle(
					extensionPoint, manifestElement.getValue())) {
				dependencies.add(depExt);
				dependencies.addAll(findDependentExtensions(depExt));
			}
		}

		return dependencies;
	}

	public static List<IExtension> findExtensionsDeclaredInBundle(
			final IExtensionPoint p_extPoint, final String p_bundleId) {
		final List<IExtension> delcExt = new ArrayList<IExtension>();

		for (final IExtension ext : p_extPoint.getExtensions()) {
			if (p_bundleId.equals(ext.getNamespaceIdentifier())) {
				delcExt.add(ext);
			}
		}

		return delcExt;
	}

	/**
	 * Sorts the specified extensions with respect to their dependencies. In
	 * other words, if an extension depends on another one, it will be placed
	 * after the extension on which it depends.
	 * 
	 * @param p_extensions
	 *            An array containing the extensions to be sorted.
	 * @return An array of the sorted extensions.
	 */
	public static IExtension[] sortExtensionsByDependencies(
			final IExtension[] p_extensions) {
		return sortExtensionsByDependencies(p_extensions, true);
	}

	public static IExtension[] sortExtensionsByDependencies(
			final IExtension[] p_extensions, final boolean pb_ascending) {
		final IExtension[] sortedExt = Arrays.copyOf(p_extensions,
				p_extensions.length);
		Arrays.sort(sortedExt,
				new ExtensionDependenciesComparator(pb_ascending));

		return sortedExt;
	}

	/**
	 * Sorts the specified extensions with respect to their dependencies. In
	 * other words, if an extension depends on another one, it will be placed
	 * after the extension on which it depends.
	 * 
	 * @param p_extensions
	 *            The list to be sorted.
	 */
	public static void sortExtensionsByDependencies(
			final List<IExtension> p_extensions) {
		Collections.sort(p_extensions, new ExtensionDependenciesComparator());
	}

	/**
	 * Returns whether the first extension depends on the second extension.
	 * 
	 * @param p_ext1
	 *            The first extension.
	 * @param p_ext2
	 *            The second extension.
	 * @return True if the first extension depends on the second extension.
	 * @throws BundleException
	 */
	public static boolean extensionDependsOn(final IExtension p_ext1,
			final IExtension p_ext2) throws BundleException {
		return findDependentExtensions(p_ext1).contains(p_ext2);
	}

	public static IExtension[] getExtensionPointExtensions(
			final Bundle p_declaringBundle, final String p_extPointID) {
		return getExtensionPointExtensions(p_declaringBundle.getSymbolicName(),
				p_extPointID);
	}

	public static IExtension[] getExtensionPointExtensions(
			final String p_extPointIdNs, final String p_extPointID) {
		final IExtensionRegistry extensionRegistry = Platform
				.getExtensionRegistry();

		return extensionRegistry
				.getExtensionPoint(p_extPointIdNs, p_extPointID)
				.getExtensions();
	}

	public static Object getExecutableExtension(final String p_extPointIdNs,
			final String p_extPointID, final String p_elementAttId)
			throws CoreException {
		for (final IExtension ext : getExtensionPointExtensions(p_extPointIdNs,
				p_extPointID)) {
			for (final IConfigurationElement element : ext
					.getConfigurationElements()) {
				final String classAttribute = element
						.getAttribute(p_elementAttId);

				if (classAttribute != null) {
					return element.createExecutableExtension(p_elementAttId);
				}
			}
		}

		return null;
	}

	public static IExtension getExtensionPointExtension(
			final String p_extPointIdNs, final String p_extPointID,
			final String p_elementAttId) {
		for (final IExtension ext : getExtensionPointExtensions(p_extPointIdNs,
				p_extPointID)) {
			if (p_elementAttId.equals(ext.getSimpleIdentifier())) {
				return ext;
			}
		}

		return null;
	}

	public static void removeNature(final Set<IProject> p_projects,
			final String p_natureId) throws CoreException {
		for (final IProject project : p_projects) {
			removeNature(project, p_natureId);
		}
	}

	public static boolean removeNature(final IProject p_project,
			final String p_natureId) throws CoreException {
		if (p_project.hasNature(p_natureId)) {
			final IProjectDescription descr = p_project.getDescription();
			final List<String> newNatures = new ArrayList<String>();
			newNatures.addAll(Arrays.asList(descr.getNatureIds()));
			newNatures.remove(p_natureId);
			descr.setNatureIds(newNatures.toArray(new String[newNatures.size()]));
			p_project.setDescription(descr, null);

			return true;
		}

		return false;
	}

	public static void addNature(	final Set<IProject> p_projects,
									final String p_natureId )
	throws CoreException {
		for (final IProject project : p_projects) {
			addNature(project, p_natureId);
		}
	}

	public static boolean addNature(	final IProject p_project,
										final String p_natureId )
	throws CoreException {
		if (p_project.hasNature(p_natureId)) {
			return false;
		}

		final IProjectDescription descr = p_project.getDescription();
		final List<String> newNatures = new ArrayList<String>();
		newNatures.addAll(Arrays.asList(descr.getNatureIds()));
		newNatures.add(p_natureId);
		descr.setNatureIds(newNatures.toArray(new String[newNatures.size()]));
		p_project.setDescription(descr, null);

		return true;
	}

	public static Collection<String> getEditorExtensions( final Class<? extends IEditorPart> p_editorClass ) {
		for (final IExtension extension : getExtensionPointExtensions( "org.eclipse.ui", "editors" ) ) {
			final IConfigurationElement[] elements = extension.getConfigurationElements();
			final String editorClassName = p_editorClass.getName();

			for (final IConfigurationElement element : elements) {
				if (	"editor".equals(element.getName() ) &&
						editorClassName.equals(element.getAttribute("class"))) {
					final Collection<String> extensions = new ArrayList<String>();
					final StringTokenizer tokenizer = new StringTokenizer( element.getAttribute("extensions"), "," );

					while (tokenizer.hasMoreTokens()) {
						extensions.add(tokenizer.nextToken());
					}

					return extensions;
				}
			}
		}

		return null;
	}

	public static Set<IProject> getReferencedProjects( final IProject p_project )
	throws CoreException {
		return getReferencedProjects( p_project, new HashSet<String>() );
	}

	private static Set<IProject> getReferencedProjects( final IProject p_project,
														final Set<String> p_traversedProjects ) 
	throws CoreException {
		final Set<IProject> refProjects = new LinkedHashSet<IProject>();
		
		for ( final IProject refProj : p_project.getReferencedProjects() ) {
			final String projName = refProj.getName();
			
			if ( !p_traversedProjects.contains( projName ) ) {
				p_traversedProjects.add( projName );
				
				if ( refProj.isAccessible() ) {
					refProjects.add( refProj );
					refProjects.addAll( getReferencedProjects( refProj, p_traversedProjects ) );
				}
			}
		}
		
		return refProjects;
	}
}
