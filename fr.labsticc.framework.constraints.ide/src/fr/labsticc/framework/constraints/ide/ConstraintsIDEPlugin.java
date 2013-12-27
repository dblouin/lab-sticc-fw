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
package fr.labsticc.framework.constraints.ide;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.labsticc.framework.constraints.ide.view.IConstraintsPreferenceConstants;
import fr.labsticc.framework.constraints.model.constraints.ConstraintLanguage;
import fr.labsticc.framework.constraints.model.constraints.ConstraintLanguagesSpecification;
import fr.labsticc.framework.constraints.model.constraints.ConstraintsFactory;
import fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage;
import fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression;
import fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary;
import fr.labsticc.framework.constraints.model.constraints.NaturalConstraintLanguage;
import fr.labsticc.framework.constraints.model.constraints.ServiceDescriptor;
import fr.labsticc.framework.constraints.service.IConstraintInterpretationAS;
import fr.labsticc.framework.constraints.service.IConstraintPersistenceAS;
import fr.labsticc.framework.constraints.view.IConstraintViewersConfiguration;
import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.util.FileUtil;
import fr.labsticc.framework.emf.core.resource.BundleEMFResourceHandler;
import fr.labsticc.framework.ide.util.PluginUtil;
import fr.labsticc.framework.settings.ide.SettingsPlugin;

/**
 * Handles the Constraints languages model defining all available constraints languages in the IDE.
 * @author dblouin
 */
public class ConstraintsIDEPlugin extends AbstractUIPlugin {

	private static final char FILE_SEPARATOR_CHAR = '/';

	private static final String CONSTRAINT_LANG_MODEL_DEF_LOC_EXT_ID = "constraintLanguagesModelDefaultLocation";
	private static final String MODEL_LOC_ELEM = "modelLocation";
	private static final String MODEL_LOC_URI_ATT = "uri";

	// The shared instance
	private static ConstraintsIDEPlugin plugin;
	
	private ConstraintLanguagesSpecification specification;

	private static final BundleEMFResourceHandler resourceHandler = new BundleEMFResourceHandler();
	
	private Logger logger;

	/**
	 * The constructor
	 */
	public ConstraintsIDEPlugin() {
		specification = null;

		try {
			logger = LoggerFactory.getLogger( getClass() );
		}
		catch( final Throwable p_th ) {
			p_th.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start( final BundleContext p_context )
	throws Exception {
		super.start( p_context );
		
		plugin = this;
		
		try {
			initDefinedLanguages();
		}
		catch ( final Exception p_ex ) {
			logger.error( toString(), p_ex );
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop( final BundleContext p_context )
	throws Exception {
		plugin = null;
		specification = null;
	
		super.stop( p_context );
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static ConstraintsIDEPlugin getDefault() {
		return plugin;
	}
	
	private void initDefinedLanguages() 
	throws Exception {
		final IPreferenceStore prefStore = getPreferenceStore();
		final String location = prefStore.getString( IConstraintsPreferenceConstants.DEFAULT_CONSTRAINTS_LANG_MODEL_LOCATION );
		
		if ( location == null || "".equals( location ) ) {
			throw new NullPointerException( "Default constraints languages model location should not be null." );
		}
		
		final URI uri = URI.createURI( location );
		Resource specificationResource;
		final ResourceSet resSet = new ResourceSetImpl();
		final URIConverter uriConverter = resSet.getURIConverter();
		
		if ( uriConverter.exists( uri, null ) ) {
			specificationResource = resSet.getResource( uri, true );
			specificationResource.getContents().clear();
		}
		else {
			specificationResource = resSet.createResource( uri );
		}

		specificationResource.getContents().add( initDefaultSpecification( uri ) );
		specification = (ConstraintLanguagesSpecification) specificationResource.getContents().get( 0 );
		
		final String directory = location.substring( 0, location.lastIndexOf( uri.lastSegment() ) - 1 );
		final String absPath = resourceHandler.locateResource( location );
		initFormalLanguages( directory, absPath );

		// Save needed after update of libraries from files.
		saveSpecification();
	}
	
	private boolean isReadOnly( final URI p_uri ) 
	throws ResourceAccessException {
		final String resourcePath = resourceHandler.locateResource( p_uri.toString() );

		return !new File( resourcePath ).canWrite();
	}
	
	private void setReadOnly( 	final URI p_uri,
								final boolean pb_readOnly ) 
	throws  ResourceAccessException {
		final String resourcePath = resourceHandler.locateResource( p_uri.toString() );

		new File( resourcePath ).setWritable( !pb_readOnly );
	}

	public void saveSpecification() 
	throws ResourceAccessException {
		final Resource resource = specification.eResource();
		assert resource != null : "Constraints specification should be stored in a resource.";

		try {
			saveSpecification( isReadOnly( resource.getURI() ) );
		}
		catch ( final IOException p_ex ) {
			throw new ResourceAccessException( p_ex );
		}
	}
	
	private void saveSpecification( final boolean pb_setReadOnly ) 
	throws ResourceAccessException, IOException {
		final URI specUri = specification.eResource().getURI();
		
		if ( isReadOnly( specUri ) ) {
			
			// Make it not writable because we need to save (because of systematic updates of libraries).
			setReadOnly( specUri, false );
		}
		
		if ( specUri.isPlatformPlugin() ) {
			// Use absolute path because platform plugin protocol does not allow save operation.
			String absPath = resourceHandler.locateResource( specUri.toString() );
			final ResourceSet resSet = new ResourceSetImpl();
			final Resource fileRes = resSet.getResource( URI.createFileURI( absPath ), true );
			fileRes.getContents().clear();
			fileRes.getContents().add( specification );
			fileRes.save( null );
			
			// Reload the resource so that it has the proper protocol.
			specification = (ConstraintLanguagesSpecification) resSet.getResource( specUri, true ).getContents().get( 0 );
		}
		else {
			final Resource resource = specification.eResource();
			resource.save( null );
		}
		
		if ( pb_setReadOnly ) {
			setReadOnly( specUri, true );
		}
	}
	
	private ConstraintLanguagesSpecification initDefaultSpecification( final URI p_destUri ) 
	throws ResourceAccessException, IOException {
		final String bundleIdDefaultModel = getIdOfBundleDeclaringDefaultConstraintLanguagesModelLocation();
		
		if ( bundleIdDefaultModel != null ) {
			final URI uri = URI.createPlatformPluginURI( bundleIdDefaultModel + "/resources/constraints/defaultConstraintsLanguages.constraints", true );
			final ResourceSet resSet = new ResourceSetImpl();
			
			if ( resSet.getURIConverter().exists( uri, null ) ) {
				copyPredefinedConstraintsFiles( uri, p_destUri );

				// DB: Ticket #231 Fix bug that overrides user modified files.
				final Resource modelRes = resSet.getResource( p_destUri, true );
				
				return (ConstraintLanguagesSpecification) modelRes.getContents().get( 0 );
			}
		}
		
		return createDefaultConstraintLanguageSpecification();
	}
	
	private void copyPredefinedConstraintsFiles( 	final URI p_modelUri,
													final URI p_destUri )
	throws ResourceAccessException, IOException {
		if ( !p_modelUri.equals( p_destUri ) ) {
			final String defaultModelFile = resourceHandler.locateResource( p_modelUri.toString() );
			final File inDirectory = new File( defaultModelFile ).getParentFile();
			final String outModelFile = resourceHandler.locateResource( p_destUri.toString() );
			final File outDirectory = new File( outModelFile ).getParentFile();
			FileUtil.copyFile( inDirectory, outDirectory, new FileUtil.ICopyPolicy() {
				
				@Override
				public void postCopy( final File p_targetFile ) {
					p_targetFile.setReadOnly();
				}
				
				@Override
				public boolean canOverride( final File p_targetFile ) {
					return p_targetFile.isDirectory() || !p_targetFile.canWrite();
				}
			} );
		}
	}
	
	private ConstraintLanguagesSpecification createDefaultConstraintLanguageSpecification() {
		final ConstraintLanguagesSpecification spec = ConstraintsFactory.eINSTANCE.createConstraintLanguagesSpecification();
		spec.setName( "Default Constraints Specification" );
		spec.setDescription( "This constraints specification was automatically created because specified specification did not exists." );
		
		// Add the natural language
		final NaturalConstraintLanguage natLanguage = ConstraintsFactory.eINSTANCE.createNaturalConstraintLanguage();
		natLanguage.setId( "fr.labsticc.framework.constraints.core.natural" );
		natLanguage.setName( "Natural" );
		spec.getLanguages().add( natLanguage );
		
		return spec;
	}
	
	private void initFormalLanguages(	final String p_location,
										final String p_absLocation )
	throws Exception {
		for ( final ConstraintLanguage language : specification.getLanguages() ) {
			if ( language instanceof FormalConstraintLanguage ) {
				// Refresh the libraries with files found in dedicated language directories.
				initLibraries( (FormalConstraintLanguage) language, p_location, p_absLocation );
			}
		}
	}
	
	/**
	 * Read all file library content and update the constraints language specification. The library object id is
	 * composed as the protocol of the main specification.
	 * @param p_language
	 * @param p_location
	 * @param p_absLocation
	 * @throws Exception
	 */
	private void initLibraries( final FormalConstraintLanguage p_language,
								final String p_location,
								final String p_absLocation ) 
	throws Exception {
		if ( p_location != null && !"".equals( p_location ) ) {
			p_language.getLibraries().clear();
			final String languageDir = FILE_SEPARATOR_CHAR + ( p_language.getLibraryDirectory() == null ? p_language.getName().toLowerCase() : p_language.getLibraryDirectory() );
			final File directoryPath = new File( new File( p_absLocation ).getParent() + languageDir );
			
			if ( directoryPath.exists() ) {
				final List<File> libFiles = FileUtil.findFilesOfTypeInDirectory( directoryPath.getPath(), p_language.getName().toLowerCase(), true );
	
				Collections.sort( libFiles, new Comparator<File>() {
	
					@Override
					public int compare( final File p_file0, 
										final File p_file1 ) {
						return p_file0.getPath().compareTo( p_file1.getPath() );
					}
				} );
	
				for ( final File libraryFile : libFiles ) {
					final FormalLanguageExpression libExpression = ConstraintsFactory.eINSTANCE.createFormalLanguageExpression();
					libExpression.setLanguage( p_language );
					
					final String libraryPath = libraryFile.getPath();
	
					try {
						libExpression.setText( FileUtil.getFileContent( libraryPath ) );
						final FormalLanguageLibrary library = ConstraintsFactory.eINSTANCE.createFormalLanguageLibrary();
						final StringBuffer libraryId = new StringBuffer();
						
						File parent = libraryFile;
						
						while ( !directoryPath.equals( parent ) ) {
							libraryId.insert( 0, FILE_SEPARATOR_CHAR + parent.getName() );
							parent = parent.getParentFile();
						}
	
						library.setId( p_location + languageDir + libraryId.toString() );
						library.setName( libraryFile.getName() );
						library.setExpression( libExpression );
	
						if ( libraryFile.getParent().endsWith( "default" ) ) {
							library.setDefault( true );
						}
	
						p_language.getLibraries().add( library );
					}
					catch ( final IOException p_ex ) {
						logger.error( toString(), p_ex );
					}
				}
			}
		}
	}
	
	public final Collection<ConstraintLanguage> getDefinedConstraintLanguages() {
		return specification.getLanguages();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<FormalConstraintLanguage, IConstraintInterpretationAS> getConstraintInterpretationServices() 
	throws CoreException {
		final Map<FormalConstraintLanguage, IConstraintInterpretationAS> services = new HashMap<FormalConstraintLanguage, IConstraintInterpretationAS>();
		
		for ( final ConstraintLanguage language : getSpecification().getLanguages() ) {
			if ( language instanceof FormalConstraintLanguage ) {
				final FormalConstraintLanguage formalLanguage = (FormalConstraintLanguage) language;
				final ServiceDescriptor interpDescr = formalLanguage.getInterpreter();
				final Bundle bundle = Platform.getBundle( interpDescr.getProviderId() );

				try {
					if ( bundle == null ) {
						throw new IllegalAccessException( "Bundle " + interpDescr.getProviderId() + " not found." );
					}

					final IConstraintInterpretationAS service = (IConstraintInterpretationAS) bundle.loadClass( interpDescr.getServiceClass() ).newInstance();
					service.setScopeProviders( (Collection) SettingsPlugin.getDefault().allModelInterfaces() );
					services.put( formalLanguage, service );
				}
				catch ( final Throwable p_th ) {
					throw new CoreException( new Status( 	IStatus.ERROR, 
															getBundle().getSymbolicName(),
															"Error loading interpretation service class for language " + language.getId(),
															p_th ) );
				}
			}
		}
		
		return services;
	}
	
	public Map<String, IConstraintInterpretationAS> getConstraintInterpretationServicesByLanguageId() 
	throws CoreException {
		final Map<String, IConstraintInterpretationAS> services = new HashMap<String, IConstraintInterpretationAS>();
		
		for ( final Map.Entry<FormalConstraintLanguage, IConstraintInterpretationAS> entry : getConstraintInterpretationServices().entrySet() ) {
			services.put( entry.getKey().getId(), entry.getValue() );
		}
		
		return services;
	}
	
	public String getDefaultConstraintLanguagesModelLocation() {
		String uri = null;

		for( final IExtension extension : PluginUtil.getExtensionPointExtensions( getBundle(), CONSTRAINT_LANG_MODEL_DEF_LOC_EXT_ID ) ) {
			for( final IConfigurationElement confElem : extension.getConfigurationElements() ) {
				if ( MODEL_LOC_ELEM.equals ( confElem.getName() ) ) {
					uri = confElem.getAttribute( MODEL_LOC_URI_ATT );
				}
			}
		}
		
		return uri;
	}
	
	private String getIdOfBundleDeclaringDefaultConstraintLanguagesModelLocation() {
		for( final IExtension extension : PluginUtil.getExtensionPointExtensions( getBundle(), CONSTRAINT_LANG_MODEL_DEF_LOC_EXT_ID ) ) {
			for( final IConfigurationElement confElem : extension.getConfigurationElements() ) {
				if ( MODEL_LOC_ELEM.equals ( confElem.getName() ) ) {
					return extension.getNamespaceIdentifier();
				}
			}
		}
		
		return null;
	}
	
	public Map<ConstraintLanguage, IConstraintPersistenceAS> getConstraintPersistenceServices() 
	throws CoreException {
		final Map<ConstraintLanguage, IConstraintPersistenceAS> services = new HashMap<ConstraintLanguage, IConstraintPersistenceAS>();
		
		for ( final ConstraintLanguage language : getSpecification().getLanguages() ) {
			if ( language instanceof FormalConstraintLanguage ) {
				final FormalConstraintLanguage formalLanguage = (FormalConstraintLanguage) language;
				final ServiceDescriptor persistDescr = formalLanguage.getPersister();
				
				if ( persistDescr != null ) {
					final Bundle bundle = Platform.getBundle( persistDescr.getProviderId() );
					
					try {
						if ( bundle == null ) {
							throw new IllegalAccessException( "Bundle " + persistDescr.getProviderId() + " not found." );
						}

						services.put( 	formalLanguage, 
										(IConstraintPersistenceAS) bundle.loadClass( persistDescr.getServiceClass() ).newInstance() );
					}
					catch ( final Throwable p_th ) {
						throw new CoreException( new Status( 	IStatus.ERROR, 
																bundle.getSymbolicName(),
																"Error loading persistence service class for language " + language.getId(),
																p_th ) );
					}
				}
			}
		}
		
		return services;
	}
	
	public Map<String, IConstraintPersistenceAS> getConstraintPersistenceServicesByLanguageId() 
	throws CoreException {
		final Map<String, IConstraintPersistenceAS> services = new HashMap<String, IConstraintPersistenceAS>();
		
		for ( final Map.Entry<ConstraintLanguage, IConstraintPersistenceAS> entry : getConstraintPersistenceServices().entrySet() ) {
			services.put( entry.getKey().getId(), entry.getValue() );
		}
		
		return services;
	}

	ConstraintLanguage findLanguage( final String p_id ) {
		for ( final ConstraintLanguage language : getDefinedConstraintLanguages() ) {
			if ( p_id.equals( language.getId() ) ) {
				return language;
			}
		}
		
		return null;
	}
	
	public IConstraintViewersConfiguration getViewersConfiguration() {
		return new IDEConstraintViewersConfiguration();
	}
	
	public ConstraintLanguagesSpecification getSpecification() {
		return specification;
	}
}
