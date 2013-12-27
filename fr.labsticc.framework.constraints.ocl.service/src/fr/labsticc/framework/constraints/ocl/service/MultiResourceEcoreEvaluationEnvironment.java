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
package fr.labsticc.framework.constraints.ocl.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ecore.EcoreEvaluationEnvironment;
import org.eclipse.ocl.utilities.PredefinedType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.labsticc.framework.settings.model.settings.ScopeProvider;

public class MultiResourceEcoreEvaluationEnvironment extends EcoreEvaluationEnvironment {
	
	private static final transient Logger logger = LoggerFactory.getLogger( MultiResourceEcoreEvaluationEnvironment.class );
	
	private final Collection<ScopeProvider> scopeProviders;

	/**
	 * Initializes me.
	 */
//	public MultiResourceEcoreEvaluationEnvironment( final EcoreEnvironmentFactory p_factory,
//													final Collection<ScopeProvider> p_scopeProviders ) {
//		super( p_factory );
//		
//		scopeProviders = p_scopeProviders;
//	}

	/**
	 * Initializes me with my parent evaluation environment (nesting scope).
	 * 
	 * @param parent
	 *            my parent (nesting scope); must not be <code>null</code>
	 */
	public MultiResourceEcoreEvaluationEnvironment( final EvaluationEnvironment<EClassifier,
																				EOperation,
																				EStructuralFeature,
																				EClass,
																				EObject> p_parent,
													final Collection<ScopeProvider> p_scopeProviders ) {
		super( p_parent );
		
		scopeProviders = p_scopeProviders;
	}

	@Override
	public Map<EClass, Set<EObject>> createExtentMap( final Object p_object ) {
		if ( p_object instanceof EObject ) {
			return new MultiResourceLazyExtentMap( (EObject) p_object, initScope( p_object ) );
		}

		return Collections.emptyMap();
	}
	
	private Collection<EObject> initScope( final Object p_context ) {
		for ( final ScopeProvider scopeProvider : scopeProviders ) {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			final Collection<EObject> scope = (Collection) scopeProvider.getScope( p_context );
			
			if ( scope != null ) {
				return scope;
			}
		}
		
		return Collections.singleton( (EObject) p_context );
	}
	
    /**
     * Override this to better handle invocation target exceptions
     */
	@Override
    public Object callOperation( 	final EOperation p_operation, 
    								final int pi_opcode, 
    								final Object p_source, 
    								final Object[] p_args )
	throws IllegalArgumentException {
    	if ( getParent() != null ) {
    		return getParent().callOperation( p_operation, pi_opcode, p_source, p_args );
    	}
    	
    	final Method method = getJavaMethodFor( p_operation, p_source );
    	
    	if ( method != null ) {
    		try {
    		    // coerce any collection arguments to EList as necessary
    		    final Class<?>[] parmTypes = method.getParameterTypes();
    		    
    		    for ( int i = 0; i < parmTypes.length; i++ ) {
    		        if ( EList.class.isAssignableFrom(parmTypes[i])) {
    		            if (p_args[i] == null) {
    		                p_args[i] = ECollections.EMPTY_ELIST;
    		            }
    		            else if (!( p_args[i] instanceof Collection<?>)) {
    		                EList<Object> list = new BasicEList.FastCompare<Object>(1);
    		                list.add( p_args[i]);
    		                p_args[i] = list;
    		            } 
    		            else if (!(p_args[i] instanceof EList<?>)) {
    		            	p_args[i] = new BasicEList.FastCompare<Object>((Collection<?>) p_args[i]);
    		            }
    		        }
    		    }
    		    
				return method.invoke( p_source, p_args );
			}
    		catch ( final InvocationTargetException p_ex ) {
    			if ( p_ex.getTargetException() != null ) {
    				final Throwable targetTh = p_ex.getTargetException();
    				
    				if ( targetTh == null ) {
            			logger.error( toString(), p_ex );
    				}
    				else {
    					if ( 	!( targetTh instanceof UnsupportedOperationException ) || targetTh.getMessage() == null ||
    							!targetTh.getMessage().startsWith( "Component is not allowed to have property associations" ) )
    					logger.error( toString(), targetTh );
    				}
    			}

    			return getInvalidResult();
    		}
    		catch ( final Exception p_ex ) {
    			logger.error( toString(), p_ex );

    			return getInvalidResult();
			}
    	}
    	
    	// maybe it's a comparison operation that is implemented implicitly
    	// via the Comparable interface?
    	switch ( pi_opcode ) {
    	    case PredefinedType.LESS_THAN:
    	    case PredefinedType.GREATER_THAN:
    	    case PredefinedType.LESS_THAN_EQUAL:
    	    case PredefinedType.GREATER_THAN_EQUAL:
            if ( ( p_source instanceof Comparable<?>) && ( p_args.length == 1)) {
                @SuppressWarnings("unchecked")
                Comparable<Object> comparable = (Comparable<Object>) p_source;
                Object other = p_args[0];
                
            	switch ( pi_opcode ) {
                    case PredefinedType.LESS_THAN:
                        return comparable.compareTo(other) < 0;
                    case PredefinedType.GREATER_THAN:
                        return comparable.compareTo(other) > 0;
                    case PredefinedType.LESS_THAN_EQUAL:
                        return comparable.compareTo(other) <= 0;
                    case PredefinedType.GREATER_THAN_EQUAL:
                        return comparable.compareTo(other) >= 0;
            	}
            }
            break;
    	}
    	
    	throw new IllegalArgumentException();
    }
}
