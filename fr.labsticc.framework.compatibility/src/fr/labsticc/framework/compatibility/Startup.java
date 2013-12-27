package fr.labsticc.framework.compatibility;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IStartup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.labsticc.framework.core.util.ObjectReturnRunnable;
import fr.labsticc.framework.ide.util.PluginUtil;

/**
 * Applies the compatibility patches defined in extension points. 
 * 
 * Important: the startup should not be the bundle activator otherwise problems with thread locks.
 * @author dblouin
 */
public class Startup implements IStartup {

	private static final String COMPATIBILITY_UPDATER_EXT_ID = "compatibilityUpdaters";
	private static final String COMPATIBILITY_UPDATER_EXT_UPDATER_ELEM = "updater";
	private static final String COMPATIBILITY_UPDATER_EXT_UPDATER_ELEM_CLASS_ATT = "class";
	
	
	private final Logger logger;
	
	public Startup() {
		logger = LoggerFactory.getLogger( getClass() );
	}
	
	private void applyCompatibilityPatchs() 
	throws Exception {
		final List<ICompatibilityUpdater> updaters = new ArrayList<ICompatibilityUpdater>();
		final Activator activator = Activator.getDefault();
		final IExtension[] sortedExtensions = PluginUtil.sortExtensionsByDependencies( PluginUtil.getExtensionPointExtensions( activator.getBundle().getSymbolicName(), COMPATIBILITY_UPDATER_EXT_ID ) );
		
		for( final IExtension extension : sortedExtensions ) {
			final IConfigurationElement[] elements = extension.getConfigurationElements();
		
			for( final IConfigurationElement element : elements ) {
				if ( COMPATIBILITY_UPDATER_EXT_UPDATER_ELEM.equals( element.getName() ) ) {
					final ICompatibilityUpdater updater = (ICompatibilityUpdater) element.createExecutableExtension( COMPATIBILITY_UPDATER_EXT_UPDATER_ELEM_CLASS_ATT );
					updaters.add( updater );
				}
			}
		}
		
		final Display display = activator.getWorkbench().getDisplay();
		
		final ObjectReturnRunnable runnable = new ObjectReturnRunnable() {
			
			@Override
			public void run() {
				try {
					new CompatibilityManager( updaters, getShell( display ), activator.getPreferenceStore() ).applyCompatibilityPatchs();
				}
				catch ( final Exception p_ex ) {
					exception = p_ex;
				}
			}
		};
		
		display.syncExec( runnable );
		
		if ( runnable.exception != null ) {
			if ( runnable.exception instanceof Exception ) {
				throw (Exception) runnable.exception;
			}
			
			throw new Exception( runnable.exception );
		}
	}
	
	private Shell getShell( final Display p_display ) {
		Shell shell = p_display.getActiveShell();
		
		if ( shell == null ) {
			shell = new Shell( p_display );
		}
		
		return shell;
	}

	@Override
	public void earlyStartup() {
		try {
			applyCompatibilityPatchs();
		}
		catch ( final Exception p_ex ) {
			logger.error( toString(), p_ex );
		}
	}
}
