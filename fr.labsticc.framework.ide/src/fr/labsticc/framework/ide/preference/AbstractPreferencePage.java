package fr.labsticc.framework.ide.preference;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

public abstract class AbstractPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	/**
	 * Help ID string
	 */
	private String helpContextId = null;

	/**
	 * Constructor that calls the superclass and passses in the GridLayout
	 * layout style constant.
	 */
	public AbstractPreferencePage() {
		super(GRID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		initHelp();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {

		//initializing help context id, this is consistent
		//with Eclipse preference pages
		if (helpContextId != null)
			PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, helpContextId);
			
		return super.createContents(parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	protected void createFieldEditors() {

		Composite parent = getFieldEditorParent();
		addFields(parent);

	}

	/**
	 * Adds the field editors to this composite.
	 * 
	 * @param parent the parent Composite that the field editors will be added
	 * to
	 */
	protected abstract void addFields(Composite parent);

	/**
	 * Initialize the context sensitive help id for this preference page.
	 */
	abstract protected void initHelp();

	/**
	 * Sets the context sensitive help id for this preference page.
	 * 
	 * @param id String ID for the context sensitive help
	 */
	protected void setPageHelpContextId(String id) {
		this.helpContextId = id;
	}
}
