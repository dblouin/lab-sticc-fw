package fr.labsticc.framework.emf.view.ide;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.util.FileUtil;
import fr.labsticc.framework.emf.core.resource.BundleEMFResourceHandler;

public class ConfigurationModelManager {

	private final BundleEMFResourceHandler resourceHandler;

	public ConfigurationModelManager() {
		resourceHandler = new BundleEMFResourceHandler();
	}
	
	public EObject initModel( 	final String p_defaultModelLocation,
								final EObject p_emptyModel,
								final Collection<URI> p_defaultModelUris ) 
	throws ResourceAccessException, IOException {
		EObject defaultModel = initDefaultModel( p_defaultModelUris );
		final Resource modelRes;
		final ResourceSet resSet = new ResourceSetImpl();
		final URI destDirUri = URI.createURI( p_defaultModelLocation, true );
		
		
		if ( defaultModel == null ) {
			defaultModel = p_emptyModel;
			modelRes = resSet.createResource( destDirUri );
			modelRes.getContents().add( defaultModel );
			modelRes.save( null );
		}
		else {
			// Copy the folder content
			final String inResourcePath = resourceHandler.locateResource( defaultModel.eResource().getURI().toString() );
			final File inDirectory = new File( inResourcePath ).getParentFile();
			final String outResourcePath = resourceHandler.locateResource( p_defaultModelLocation );
			final File outDirectory = new File( outResourcePath ).getParentFile();
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
			
			final URI destDirUriMode = destDirUri.trimSegments( 1 ).appendSegment( defaultModel.eResource().getURI().lastSegment() );
			modelRes = resSet.getResource( destDirUriMode, true );
		}
			
		return modelRes.getContents().get( 0 );
	}
	
	private EObject initDefaultModel( final Collection<URI> p_defaultModelUris ) 
	throws ResourceAccessException {
		EObject model = null;
		final ResourceSet resSet = new ResourceSetImpl();
		
		for ( final URI uriDefaultModel : p_defaultModelUris ) {
			final URIConverter uriConverter = resSet.getURIConverter();
			
			// Only reuse the existing settings model if it has been changed by users (not read only anymore).
			if ( uriConverter.exists( uriDefaultModel, null ) ) {
				final Resource modelRes = resSet.getResource( uriDefaultModel, true );
				model = mergeModels( model, modelRes.getContents().get( 0 ) );
			}
		}
		
		return model;
	}
	
	private EObject mergeModels( 	final EObject p_existingModel,
									final EObject p_newModel ) {
		if ( p_existingModel == null ) {
			return p_newModel;
		}
		
		if ( p_newModel == null ) {
			return p_existingModel;
		}
		
		return p_newModel;
		
		// Matching model elements
//		try {
//			final MatchModel match = MatchService.doContentMatch( p_existingSpec, p_newSpec, Collections.<String, Object> emptyMap() );
//			
//			// Computing differences
//			final DiffModel diff = DiffService.doDiff( match, false );
//			
//			// Merges all differences from model1 to model2
//			final List<DiffElement> differences = new ArrayList<DiffElement>( diff.getOwnedElements() );
//			MergeService.merge( differences, false );		
//			
//			return p_existingSpec;//EMFUtil.merge( p_existingSpec, p_newSpec );
//		}
//		catch ( final InterruptedException p_ex ) {
//			throw new IllegalStateException( p_ex );
//		}
	}
}
