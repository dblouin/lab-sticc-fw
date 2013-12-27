package fr.labsticc.framework.emf.view.editor;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import fr.labsticc.framework.emf.view.editor.IEditorResourceManager;


public interface IEditorListener {

	void editorResourceLoading( IEditorResourceManager p_editorManager,
								URI p_resource );
	
	void editorResourceLoaded( 	IEditorResourceManager p_editorManager,
								Resource p_resource );

	void editorResourcesChanged( IEditorResourceManager p_editorManager );
	
	void editorClosed( IEditorResourceManager p_editorManager );
}
