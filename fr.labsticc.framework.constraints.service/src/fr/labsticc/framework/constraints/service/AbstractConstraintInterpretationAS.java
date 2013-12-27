package fr.labsticc.framework.constraints.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;

import fr.labsticc.framework.constraints.core.exception.ConstraintEvaluationException;
import fr.labsticc.framework.constraints.core.exception.ConstraintException;
import fr.labsticc.framework.constraints.core.exception.ConstraintInterpreterMessages;
import fr.labsticc.framework.core.exception.SystemException;
import fr.labsticc.framework.settings.model.settings.ScopeProvider;

public abstract class AbstractConstraintInterpretationAS implements	IConstraintInterpretationAS {

	private final Pattern evalResultPattern;
	
	private final String invalidStr;

	private final AdapterFactory reflectiveAdapterFactory;

	private final AdapterFactory defaultAdapterFactory;
	
	private Collection<ScopeProvider> scopeProviders;

	protected AbstractConstraintInterpretationAS( 	final Pattern p_evalResultPattern,
													final String p_invalidStr ) {
		evalResultPattern = p_evalResultPattern;
		invalidStr = p_invalidStr;
		reflectiveAdapterFactory = new ReflectiveItemProviderAdapterFactory();
		defaultAdapterFactory = new ComposedAdapterFactory( ComposedAdapterFactory.Descriptor.Registry.INSTANCE );
		scopeProviders = Collections.emptySet();
	}
	
	protected void validate( 	final Object p_constraintExpression,
								final Object... p_context )
	throws ConstraintEvaluationException {
		validateExpression( p_constraintExpression );
		validateContext( p_context );
	}
	
	protected void validateExpression( final Object p_constraintExpression ) 
	throws ConstraintEvaluationException {
		if ( p_constraintExpression == null ) {
			throw new ConstraintEvaluationException( ConstraintInterpreterMessages.noExpression );
		}
	}
	
	protected void validateContext( final Object... p_context ) 
	throws ConstraintEvaluationException {
		if ( p_context == null || p_context.length == 0 ) {
			throw new ConstraintEvaluationException( ConstraintInterpreterMessages.noContext );
		}
	}
	
	/**
	 * Converts a single object to a string.
	 * 
	 * @param object the object to print (not a collection type)
	 * @return the string form of the <code>object</code>
	 */
	@SuppressWarnings("unchecked")
	protected String toString( 	final Object p_context,
								final Object p_evalResult,
								final boolean pb_invalid ) {
		final StringBuilder resultStr = createResultHeader( p_context );
        
		if ( pb_invalid ) {
        	resultStr.append( invalidStr );
		} 
		else if ( p_evalResult instanceof String ) {
			resultStr.append( "'" + p_evalResult + "'" );  //$NON-NLS-1$//$NON-NLS-2$
		} 
		else if ( p_evalResult instanceof EObject ) {
			resultStr.append( getText( (EObject) p_evalResult ) );  //$NON-NLS-1$//$NON-NLS-2$
		}
		else if ( p_evalResult instanceof Collection<?> ) {
			final StringBuffer resultCollStr = new StringBuffer();
			
			for ( final Object elem : (Collection<Object>) p_evalResult ) {
				resultCollStr.append( getText( elem ) + ", " );
			}
			
			resultStr.append( resultCollStr );
		}
		else {
			resultStr.append( String.valueOf( p_evalResult ) );
		}
		
		return resultStr.toString();
	}
	
	protected StringBuilder createResultHeader( final Object p_context ) {
		final StringBuilder resultStr = new StringBuilder( "Context: " + getText( p_context ) + " Result: " );
        
		return resultStr;
	}
	
	protected final String getText( final Object p_object ) {
		IItemLabelProvider labeler = (IItemLabelProvider) defaultAdapterFactory.adapt( 	p_object,
																						IItemLabelProvider.class );

		if ( labeler == null ) {
			labeler = (IItemLabelProvider) reflectiveAdapterFactory.adapt( 	p_object,
																			IItemLabelProvider.class );
		}

		if ( labeler != null ) {
			return labeler.getText( p_object );
		}
		
		return String.valueOf( p_object );
	}

	@Override
	public Collection<Boolean> evaluateInvariant( 	final Object p_constraintInvariant,
													final Map<String, String> p_constraintLibs,
													final Object... p_context )
	throws ConstraintException, SystemException {
		@SuppressWarnings("unchecked")
		final Collection<String> results = (Collection<String>) evaluateConstraint( p_constraintInvariant,
																					p_constraintLibs,
																					true,
																					p_context );
		assert results.size() == p_context.length : "Constraint did not return the same number of results as there are context elements.";
		int index = 0;
		final Collection<Boolean> boolResults = new ArrayList<Boolean>();
		
		for ( final String result : results ) {
			boolResults.add( parseBooleanResult( result, p_context[ index++ ] ) );
		}
		
		return boolResults;
	}
	
	protected Boolean parseBooleanResult( 	final String p_result,
											final Object p_contextElement ) 
	throws ConstraintEvaluationException, SystemException {
		final Matcher matcher = evalResultPattern.matcher( p_result );

		if ( matcher.find() ) {
			final String boolResStr = matcher.group( 1 );
			
			// Invalid is mapped to false this may happen when comparing a numerical value to null for example. 
			if ( invalidStr.equals( boolResStr ) ) {
				return Boolean.FALSE;
			}
			
			if ( 	!Boolean.TRUE.toString().equalsIgnoreCase( boolResStr ) && 
					!Boolean.FALSE.toString().equalsIgnoreCase( boolResStr ) ) {														// TODO: USe label providers
				throw new ConstraintEvaluationException( "Constraint is not an invariant (evaluates to " + boolResStr + " instead of a boolean value)." );
			}
			
			return Boolean.valueOf( boolResStr );
		}
		
		throw new SystemException( "Illegal constraint evaluation result format: " + p_result );
	}
    
	@Override
	public void librariesContentChanged( final Collection<String> p_libraryIds ) {
		for ( final String libraryId : p_libraryIds ) {
			libraryContentChanged( libraryId );
		}
	}

	@Override
	public void setScopeProviders( final Collection<ScopeProvider> p_scopeProviders ) {
		scopeProviders = p_scopeProviders;
	}
	
	protected Collection<ScopeProvider> getScopeProviders() {
		return scopeProviders;
	}
	
	protected Object getElementFromScope( final Object p_context ) {
//		for ( final ScopeProvider scopeProvider : scopeProviders ) {
//			final Object elementFromScope = scopeProvider.getElementFromScope( p_context );
//			
//			if ( elementFromScope != null ) {
//				return elementFromScope;
//			}
//		}
//
		// Ticket # #239 Take into account the with clauses to define the scope of OCL queries
		// Now managed in AbstractAadlModelInterface class. 
		return p_context;
	}
}
