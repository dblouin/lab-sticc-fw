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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.Choice;
import org.eclipse.ocl.helper.ChoiceKind;
import org.eclipse.ocl.helper.ConstraintKind;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.ocl.types.TupleType;
import org.eclipse.ocl.util.ToStringVisitor;
import org.eclipse.ocl.util.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.labsticc.framework.constraints.core.CompletionKind;
import fr.labsticc.framework.constraints.core.CompletionProposal;
import fr.labsticc.framework.constraints.core.ICompletionProposal;
import fr.labsticc.framework.constraints.core.exception.ConstraintEvaluationException;
import fr.labsticc.framework.constraints.core.exception.ConstraintInterpreterMessages;
import fr.labsticc.framework.constraints.service.AbstractConstraintInterpretationAS;
import fr.labsticc.framework.core.exception.SystemException;

public class OCLInterpretationAS extends AbstractConstraintInterpretationAS {

	private static final Logger logger = LoggerFactory.getLogger( OCLInterpretationAS.class );
	
	private static final String INVALID = "OclInvalid";
	
	private final IOCLFactory<?, Object, ?, ?, ?, ?, ?, ?, ?, ?, ?, Object, String, ?> oclFactory;

	private final IItemLabelProvider tupleTypeLabelProvider;
	
	private final Comparator<Choice> choiceComparator;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public OCLInterpretationAS() {
		super( Pattern.compile( "Result: (.+)" ), INVALID );
		
		oclFactory = (IOCLFactory) new ECoreOCLFactory();
		tupleTypeLabelProvider = new IItemLabelProvider() {
			
			@Override
			public Object getImage(Object object) {
				return null;
			}
		
			@Override
			public String getText( final Object p_object ) {
	            final Tuple<?, Object> tuple = (Tuple<?, Object>) p_object;
				final TupleType<?, ?> tupleType = tuple.getTupleType();
				
				StringBuffer result = new StringBuffer();
				result.append( "Tuple{" );//$NON-NLS-1$
				
				for ( final Iterator<?> iter = tupleType.oclProperties().iterator(); iter.hasNext(); ) {
					final Object property = iter.next();
					
					result.append( oclFactory.getName( property ) );
					result.append( " = " ); //$NON-NLS-1$
					result.append( OCLInterpretationAS.this.toString( null, tuple.getValue( property ), true ) );
					
					if ( iter.hasNext() ) {
						result.append(", "); //$NON-NLS-1$
					}
				}
				
				result.append( '}' );
				
				return result.toString();
			} 
		};
		
		choiceComparator = new Comparator<Choice>() {
			
			@Override
            public int compare(	final Choice p_object1,
            					final Choice p_object2 ) {
                int result = rank( p_object1 ) - rank( p_object2 );
                
                if (result == 0) {
                    result = p_object1.getName().compareTo( p_object2.getName() );
                }
                
                return result;
            }
            
            private int rank( final Choice p_choice ) {
                switch ( p_choice.getKind() ) {
                    case VARIABLE:
                        return 0;
                    case PACKAGE:
                        return 1;
                    case TYPE:
                        return 2;
                    case ENUMERATION_LITERAL:
                        return 3;
                    case STATE:
                        return 4;
                    case PROPERTY:
                        return 5;
                    case ASSOCIATION_CLASS:
                        return 6;
                    case OPERATION:
                        return 7;
                    case SIGNAL:
                        return 8;
                    default:
                        return Integer.MAX_VALUE;
                }
            }
        };
	}

	/**
	 * Evaluates an OCL expression using the OCL Interpreter's {@link OCLHelper}
	 * API.
	 * 
	 * @param expression an OCL expression
	 * 
	 * @return <code>true</code> on successful evaluation; <code>false</code>
	 *    if the expression failed to parse or evaluate
	 */
	@Override
	public Object evaluateConstraint( 	final Object p_constraintExpression,
										final Map<String, String> p_libraries,
										//final ModelingLevel p_modelingLevel,
										final boolean pb_resultsAsString,
										final Object... p_context ) 
	throws ConstraintEvaluationException, SystemException {
		logger.debug( "Evaluating constraint " + p_constraintExpression );

		validate( p_constraintExpression, p_context );

		final Collection<Object> results = new ArrayList<Object>();
		
        try {
			for ( final Object object : p_context ) {
				final Object objFromScope = getElementFromScope( object );
				// create an OCL helper to do our parsing and evaluating
				final OCL<?, Object, ?, ?, ?, ?, ?, ?, ?, ?, ?, Object> ocl = oclFactory.createOCL( objFromScope, getScopeProviders(), p_libraries/*, p_modelingLevel*/ );
	            final OCLHelper<Object, ?, ?, ?> helper = ocl.createOCLHelper();
				
				// set our helper's context classifier to parse against it
				setContext( helper, objFromScope );
				final String constraintExpr = p_constraintExpression.toString();
	            
                final OCLExpression<Object> parsed = helper.createQuery( constraintExpr );
                
                // evaluate the query
                final Object evalResult = ocl.evaluate( objFromScope, parsed );
                
                if ( pb_resultsAsString ) {
                    final boolean invalid = ocl.isInvalid( evalResult );
                	results.add( toString( objFromScope, evalResult, invalid ) );
                }
                else {
                	results.add( evalResult );
                }
			}
			
			return results;
        }
        catch ( final ParserException p_ex ) {
        	throw new ConstraintEvaluationException( p_ex.getLocalizedMessage() );
        }
	}
	
	@Override
	protected String toString( 	final Object p_context,
								final Object p_evalResult,
								final boolean pb_invalid ) {
		if ( p_evalResult instanceof Tuple<?,?> ) {
			final StringBuilder resultStr = createResultHeader( p_context );
			resultStr.append( tupleTypeLabelProvider.getText( p_evalResult ) );
			
			return resultStr.toString();
		}
		
		return super.toString( p_context, p_evalResult, pb_invalid );
	}
    
    private List<Choice> getOCLChoices(	final String p_text,
    									final Map<String, String> p_libraries,
										final Object p_context ) 
	throws ParserException {
        final OCL<?, Object, ?, ?, ?, ?, ?, ?, ?, ?, ?, Object> ocl = oclFactory.createOCL( p_context, getScopeProviders(), p_libraries/*, p_modelingLevel*/ );
        final  OCLHelper<Object, ?, ?, ?> helper = ocl.createOCLHelper();
        
        return helper.getSyntaxHelp( 	setContext( helper, p_context/*, p_modelingLevel*/ ),
        								p_text);
    }
    
    /**
     * Set the context of the specified OCL helper to the given element (in the
     * {@link #M1} case) or its metaclass ({@link #M2} case).
     * 
     * @param helper an OCL helper
     * @param factory a factory to help in obtaining a metaclass, where needed
     * @param element the selected context element
     * 
     * @return the kind of constraint to parse or to complete
     * 
     * @throws ParserException if the specified context is not valid for this
     *    modeling level
     */
    private <O, P> ConstraintKind setContext( 	final OCLHelper<Object, O, P, ?> p_helper, 
    											final Object p_element )
    throws ParserException {
        	final Object contextClassifier = oclFactory.getContextClassifier( p_element );
        	
        	if ( contextClassifier == null ) {
        		throw new ParserException( ConstraintInterpreterMessages.console_badContextForQuery );
        	}
            
        	p_helper.setContext( contextClassifier );
            
            return ConstraintKind.INVARIANT;
    }

	@Override
	public List<ICompletionProposal> computeCompletionProposals(	final Object p_constraintExpression, 
																	final Map<String, String> p_libraries,
																	final Object... p_context )
	throws ConstraintEvaluationException, SystemException {
		if ( p_context == null || p_context.length == 0 ) {
			throw new ConstraintEvaluationException( ConstraintInterpreterMessages.noContext );
		}
		
		try {
			List<Choice> choices = null;
			
			for ( final Object contextElem : p_context ) {
				final List<Choice> contextElemChoices = getOCLChoices( 	p_constraintExpression.toString(), 
																		p_libraries, 
																		contextElem );
				
				if ( choices == null ) {
					choices = contextElemChoices;
				}
				else {
					choices.retainAll( contextElemChoices ); 
				}
			}

			sortChoices( choices );
			final List<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();
			
			for ( final Choice choice : choices ){
				proposals.add( new CompletionProposal( 	choice.getName(), 
														choice.getDescription(), 
														choice.getElement(),
														mapCompletionChoice( choice.getKind() ) ) );
			}
			
			return proposals;
		}
		catch ( final ParserException p_ex ) {
			throw new ConstraintEvaluationException( p_ex.getLocalizedMessage() );
		}
	}
	
	private CompletionKind mapCompletionChoice( final ChoiceKind p_choiceKind ) {
		switch ( p_choiceKind ) {
			case ASSOCIATION_CLASS:
				return CompletionKind.ASSOCIATION_CLASS;
			case ENUMERATION_LITERAL:
				return CompletionKind.ENUMERATION_LITERAL;
			case OPERATION:
				return CompletionKind.OPERATION;
			case PACKAGE:
				return CompletionKind.PACKAGE;
			case PROPERTY:
				return CompletionKind.PROPERTY;
			case SIGNAL:
				return CompletionKind.SIGNAL;
			case STATE:
				return CompletionKind.STATE;
			case TYPE:
				return CompletionKind.TYPE;
			case VARIABLE:
				return CompletionKind.VARIABLE;
	
			default:
				throw new IllegalArgumentException( "Unknown choice kind (" + p_choiceKind + ")." );
		}
	}
    
    private void sortChoices(List<Choice> choices) {
        Collections.sort( choices, choiceComparator );
    }

	@Override
	public String parse( final Object p_constraint ) {
		@SuppressWarnings("unchecked")
		final OCLExpression<Object> expression = (OCLExpression<Object>) p_constraint;
		
		return expression.accept( ToStringVisitor.getInstance( expression ) );
	}

	@Override
	public EObject parse( final String p_constraintText ) {
		throw new UnsupportedOperationException();
		//final OCLResource resource = new OCLResource( URI.createFileURI( "" ) );

		// create an OCL helper to do our parsing
	//	final OCL<?, Object, ?, ?, ?, ?, ?, ?, ?, Object, ?, ?> ocl = oclFactory.createOCL( p_context, p_modelingLevel, resource );
     // OCLHelper<Object, ?, ?, Object> helper = ocl.createOCLHelper();
      
      // set our helper's context classifier to parse against it
//      ConstraintKind kind = document.getModelingLevel().setContext(
//          helper, document.getOCLContext(), document.getOCLFactory());
//		
//		OCLExpression<Object> parsed = null;
//      
//      switch (document.getModelingLevel()) {
//          case M2:
//              parsed = helper.createQuery(expression);
//              break;
//          case M1:
//              Object constraint = helper.createConstraint(kind, expression);
//              parsed = ocl.getEnvironment().getUMLReflection().getSpecification(
//                  constraint).getBodyExpression();
//              break;
//      }
      
		//return null;
	}
	
	@Override
	public void libraryContentChanged( final String p_libraryId ) {
		oclFactory.refreshLibrary( p_libraryId );
	}
}
