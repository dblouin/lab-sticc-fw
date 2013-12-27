package fr.labsticc.framework.emf.view.ide;

import java.util.Comparator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.presentation.EcoreEditorPlugin;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;

import fr.labsticc.framework.core.util.DefaultComparator;
import fr.labsticc.framework.emf.view.EmfFwIdePlugin;

public class OpenEcoreTypeSelectionDialog extends FilteredItemsSelectionDialog {

	private static final String DIALOG_SETTINGS = OpenEcoreTypeSelectionDialog.class.getName();
	
	private static final IStatus OK_STATUS = new Status( 	IStatus.OK,
															EmfFwIdePlugin.getDefault().getBundle().getSymbolicName(),
															0,
															"", //$NON-NLS-1$
															null );

	private final EPackage rootPackage;

	public OpenEcoreTypeSelectionDialog( 	final Shell p_shell,
											final EPackage p_package ) {
		super( p_shell );

		rootPackage = p_package;
		setListLabelProvider( new AdapterFactoryLabelProvider( new EcoreItemProviderAdapterFactory() ) );
	}

	@Override
	protected ItemsFilter createFilter() {
		return new ItemsFilter() {

			@Override
			public boolean matchItem( final Object p_item ) {
				return p_item instanceof ENamedElement && matches( getElementName( p_item ) );
			}

			@Override
			public boolean isConsistentItem( final Object p_item ) {
				return true;
			}
		};
	}

	@Override
	protected Control createExtendedContentArea(Composite parent) {
		return null;
	}

	@Override
	protected IDialogSettings getDialogSettings() {
		IDialogSettings settings = EcoreEditorPlugin.getPlugin().getDialogSettings().getSection( DIALOG_SETTINGS );

		if ( settings == null ) {
			settings = EcoreEditorPlugin.getPlugin().getDialogSettings().addNewSection( DIALOG_SETTINGS );
		}

		return settings;
	}

	@Override
	protected IStatus validateItem(Object item) {
		return OK_STATUS;
	}

	@Override
	protected Comparator<ENamedElement> getItemsComparator() {
		return new Comparator<ENamedElement>() {

			@Override
			public int compare( final ENamedElement p_elem1,
								final ENamedElement p_elem2 ) {
				return DefaultComparator.getInstance().compare( p_elem1.getName(), p_elem2.getName() );
			}
		};
	}

	@Override
	protected void fillContentProvider( final AbstractContentProvider p_contentProvider,
										final ItemsFilter p_itemsFilter,
										final IProgressMonitor p_progressMonitor )
	throws CoreException {
		fillContentProvider( rootPackage, p_contentProvider, p_itemsFilter );
		
		if ( p_progressMonitor != null ) {
			p_progressMonitor.done();
		}
	}
	
	private static void fillContentProvider( 	final EPackage p_package,
												final AbstractContentProvider p_contentProvider,
												final ItemsFilter p_itemsFilter ) {
		for ( final EClassifier eClassifier : p_package.getEClassifiers() ) {
			p_contentProvider.add( eClassifier, p_itemsFilter );
		}
		
		for ( final EPackage subPackage : p_package.getESubpackages() ) {
			fillContentProvider( subPackage, p_contentProvider, p_itemsFilter );
		}
	}

	@Override
	public String getElementName( final Object p_item ) {
		return ( (ENamedElement) p_item ).getName();
	}
	
	@Override
    public ENamedElement getFirstResult() {
		return (ENamedElement) super.getFirstResult();
	}
}
