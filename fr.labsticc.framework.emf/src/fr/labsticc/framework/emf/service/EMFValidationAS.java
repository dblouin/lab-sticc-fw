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
package fr.labsticc.framework.emf.service;

import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;

import fr.labsticc.framework.core.exception.FunctionalException;
import fr.labsticc.framework.core.exception.SystemException;

public class EMFValidationAS extends EObjectValidator implements IEMFValidationAS {

	private final AdapterFactory defaultAdapterFactory;

	public EMFValidationAS() {
		defaultAdapterFactory = new ComposedAdapterFactory( ComposedAdapterFactory.Descriptor.Registry.INSTANCE );
	}
	
	@Override
	public void validate( final EObject... p_objects ) 
	throws FunctionalException, SystemException, InterruptedException {
	    final Diagnostician diagnostician = createDiagnostician( /*progressMonitor*/);
	    final BasicDiagnostic diagnostic = createDiagnostic( diagnostician, p_objects );
	    validate( diagnostician, diagnostic, diagnostician.createDefaultContext(), p_objects );
	    
	    checkDiagnostic( diagnostic );
	}
	
	private void checkDiagnostic( final BasicDiagnostic p_diagnostic ) 
	throws FunctionalException {
	    if ( p_diagnostic.getSeverity() != Diagnostic.OK ) {
	    	throw new FunctionalException( p_diagnostic, p_diagnostic.getMessage(), null );
	    }
	}
	
	protected void validate( 	final Diagnostician p_diagnostician,
								final BasicDiagnostic p_diagnostic,
								final Map<Object, Object> p_context,
								final EObject... p_objects ) {
	    //int count = p_objects.length;
	    
//	    for ( final EObject eObject : p_objects ) {
//	    	for ( final Iterator<?> iterator = eObject.eAllContents(); iterator.hasNext(); iterator.next() ) {
//	    		++count;
//	    	}
//	    }

	    //progressMonitor.beginTask("", count);

	    
	    for ( final EObject eObject : p_objects ) {
	    	//progressMonitor.setTaskName(EMFEditUIPlugin.INSTANCE.getString("_UI_Validating_message", new Object [] {diagnostician.getObjectLabel(eObject)}));
	    	p_diagnostician.validate( eObject, p_diagnostic, p_context );
	    }
	}
	
	protected BasicDiagnostic createDiagnostic( final Diagnostician p_diagnostician,
												final EObject... p_objects ) {
	    final BasicDiagnostic diagnostic;
	    
	    if ( p_objects.length == 1 ) {
	    	diagnostic = p_diagnostician.createDefaultDiagnostic( p_objects[ 0 ] );
	    }
	    else {
	    	diagnostic = new BasicDiagnostic(	EObjectValidator.DIAGNOSTIC_SOURCE,
	    		  								0,
	    		  								"TODO",
	    		  								//EMFEditUIPlugin.INSTANCE.getString("_UI_DiagnosisOfNObjects_message", 
	    		  								//new String[] { Integer.toString( selectionSize ) } ),
	    		  								p_objects ); 
	    }
	    
	    return diagnostic;
	}

	protected Diagnostician createDiagnostician( /*final IProgressMonitor p_progressMonitor*/ ) {
	    return new Diagnostician() {
	        @Override
	        public String getObjectLabel( final EObject p_eObject ) {
	        	if ( !p_eObject.eIsProxy() ) {
	        		final IItemLabelProvider itemLabelProvider = (IItemLabelProvider) defaultAdapterFactory.adapt( 	p_eObject, 
	        																										IItemLabelProvider.class);
	        		if ( itemLabelProvider != null ) {
	        			return itemLabelProvider.getText( p_eObject );
	        		}
	        	}
	  
	        	return super.getObjectLabel( p_eObject );
	        }
	  
//	        @Override
//	        public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context)
//	        {
//	          progressMonitor.worked(1);
//	          return super.validate(eClass, eObject, diagnostics, context);
//	        }
	      };
	}

	@Override
	public void validate( 	final int pi_featureId,
							final boolean pb_validateFeatureValue,
							final EObject... p_objects )
	throws FunctionalException, SystemException, InterruptedException {
	    final Diagnostician diagnostician = createDiagnostician( /*progressMonitor*/);
	    final BasicDiagnostic diagnostic = createDiagnostic( diagnostician, p_objects );
	    final Map<Object, Object> context = diagnostician.createDefaultContext();
		
		for ( final EObject object : p_objects ) {
			final EClass eClass = object.eClass();
			final EStructuralFeature feature = eClass.getEStructuralFeature( pi_featureId );
			validate_EveryDefaultConstraint( object, feature, diagnostic, context );
			
			if ( pb_validateFeatureValue ) {
				validateFeatureValue( object, feature, diagnostician, diagnostic, context );
			}
		}
		
		checkDiagnostic( diagnostic );
	}
	
	@SuppressWarnings("unchecked")
	protected void validateFeatureValue( 	final EObject p_parent,
											final EStructuralFeature p_feature,
											final Diagnostician p_diagnostician,
											final BasicDiagnostic p_diagnostic,
											final Map<Object, Object> p_context ) {
		final Object featureValue = p_parent.eGet( p_feature );
		
		if ( featureValue instanceof EObject ) {
			validate( p_diagnostician, p_diagnostic, p_context, (EObject) featureValue );
		}
		else if ( featureValue instanceof EList<?> ) {
			for ( final EObject eObject : (EList<EObject>) featureValue ) {
				validate( p_diagnostician, p_diagnostic, p_context, eObject );
			}
		}
	}
	
	protected void validate( 	final EObject p_object,
								final EStructuralFeature p_feature,
								final EObject p_featureEobjectValue )
	throws FunctionalException, SystemException, InterruptedException {
		
		if ( p_featureEobjectValue != null ) {
			validate( p_featureEobjectValue );
		}
	}
	

	public boolean validate_EveryDefaultConstraint( final EObject p_object,
													final EStructuralFeature p_feature,
													final DiagnosticChain p_diagnostics, 
													final Map<Object, Object> p_context ) {
		boolean result = validate_MultiplicityConforms( p_object, p_feature, p_diagnostics, p_context );
	    
		if ( result || p_diagnostics != null ) {
			result &= validate_ProxyResolves( p_object, p_feature, p_diagnostics, p_context );
		}
	    
		// FIXME: Finish creating methods per feature
//		if (result || theDiagnostics != null)
//	    {
//	      result &= validate_EveryReferenceIsContained(object, theDiagnostics, context);
//	    }
//	    if (result || theDiagnostics != null)
//	    {
//	      result &= validate_EveryBidirectionalReferenceIsPaired(object, theDiagnostics, context);
//	    }
//	    if (result || theDiagnostics != null)
//	    {
//	      result &= validate_EveryDataValueConforms(object, theDiagnostics, context);
//	    }
//	    if (result || theDiagnostics != null)
//	    {
//	      result &= validate_UniqueID(object, theDiagnostics, context);
//	    }
//	    if (result || theDiagnostics != null)
//	    {
//	      result &= validate_EveryKeyUnique(object, theDiagnostics, context);
//	    }
//	    if (result || theDiagnostics != null)
//	    {
//	      result &= validate_EveryMapEntryUnique(object, theDiagnostics, context);
//	    }
	    return result;
	}

	@SuppressWarnings("unchecked")
	public boolean validate_ProxyResolves( 	final EObject p_parentObject, 
		  									final EStructuralFeature p_feature,
		  									final DiagnosticChain p_diagnostics, 
			  									final Map<Object, Object> p_context ) {
		final Object featureObject = p_parentObject.eGet( p_feature );
		    
		if ( featureObject instanceof EObject ) {
			return validateResolvedProxy( p_parentObject, p_feature, (EObject) featureObject, p_diagnostics, p_context );
	    }
		  
		boolean result = true;
		  
		if ( featureObject instanceof EList<?> ) {
			for ( final EObject eObject : (EList<EObject>) featureObject ) {
				result = result & validateResolvedProxy( p_parentObject, p_feature, eObject, p_diagnostics, p_context );
			}
		}

		return result;
	}
	  
	private boolean validateResolvedProxy( 	final EObject p_parentObject,
		  									final EStructuralFeature p_feature,
		  									final EObject p_refObject,
		  									final DiagnosticChain p_diagnostics, 
		  									final Map<Object, Object> p_context	) {
		boolean result = true;

		if ( p_refObject.eIsProxy() ) {
			result = false;
		  
			if ( p_diagnostics != null ) {
				p_diagnostics.add
	            (createDiagnostic
	              (Diagnostic.ERROR,
	               DIAGNOSTIC_SOURCE,
	               EOBJECT__EVERY_PROXY_RESOLVES,
	               "_UI_UnresolvedProxy_diagnostic",
	               new Object []
	               {
	                 getFeatureLabel( p_feature, p_context ),
	                 getObjectLabel( p_refObject, p_context ),
	                 getObjectLabel( p_refObject, p_context )
	               },
	               new Object [] { p_parentObject, p_feature, p_refObject },
	               p_context ));
			}
		}
		  
		return result;
	}
}
