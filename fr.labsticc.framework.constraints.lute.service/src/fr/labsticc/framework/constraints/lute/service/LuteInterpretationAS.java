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
package fr.labsticc.framework.constraints.lute.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.osate.aadl2.Element;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.instance.InstanceObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osate.analysis.lute.LuteException;
import org.osate.analysis.lute.LuteInterpreter;
import org.osate.analysis.lute.language.*;

import fr.labsticc.framework.constraints.core.ICompletionProposal;
import fr.labsticc.framework.constraints.core.exception.ConstraintEvaluationException;
import fr.labsticc.framework.constraints.core.exception.ConstraintException;
import fr.labsticc.framework.constraints.core.exception.ConstraintParsingException;
import fr.labsticc.framework.constraints.service.AbstractConstraintInterpretationAS;
import fr.labsticc.framework.core.exception.SystemException;

public class LuteInterpretationAS extends AbstractConstraintInterpretationAS {

	private static final Logger logger = LoggerFactory.getLogger( LuteInterpretationAS.class );
//	private static final com.rockwellcollins.atc.meta.Logger luteLogger = new com.rockwellcollins.atc.meta.Logger( rockwellLoggerLevel() );

	private static final String INVALID = "LuteInvalid";
	
	private final Map<String, List<Command>> libraryCache;
	
	public LuteInterpretationAS() {
		super( Pattern.compile( "Result: (.+)" ), INVALID );
		
		libraryCache = new Hashtable<String, List<Command>>();
	}

	@Override
	public Object evaluateConstraint( 	final Object p_constraintExpression,
										final Map<String, String> p_libraries,
										final boolean pb_resultsAsString,
										final Object... p_context ) 
	throws ConstraintException, SystemException {
		logger.debug( "Evaluating constraint " + p_constraintExpression );

		validate( p_constraintExpression, p_context );

		final String constraintExpr = p_constraintExpression.toString();
		
		try {
			final Expr luteExpr = parse( constraintExpr );
			final Collection<Object> results = new ArrayList<Object>();
			
			for ( final Object object : p_context ) {
				if ( !( object instanceof Element ) ) {
					throw new ConstraintEvaluationException( "Context object " + object + " is not an AADL element." );
				}
	
				final Object contextFromScope = getElementFromScope( object );
	  			Environment initialEnv = createEnvironment( (NamedElement) contextFromScope, p_libraries );
				final Val result = evaluate( initialEnv, luteExpr );
				final Object resultObj = getValue( result );
	            
	            if ( pb_resultsAsString ) {
	            	results.add( toString( contextFromScope, resultObj, false ) );
	            }
	            else {
	    			results.add( resultObj );
	            }
			}
			
			return results;
		}
		catch ( final LuteException p_ex ) {
			throw new ConstraintEvaluationException( p_ex.getLocalizedMessage() );
		}
	}
	
	private Val evaluate( 	final Environment p_initialEnv,
							final Expr p_luteExpr ) {
		return p_luteExpr.eval( p_initialEnv );
	}
	
	private Environment createEnvironment( 	final NamedElement p_aadlElement,
											final Map<String, String> p_libraries ) 
	throws ConstraintParsingException {
		Environment environment;
		
		if ( p_aadlElement instanceof InstanceObject ) {
			environment = LuteInterpreter.createInitialEnvironment( (InstanceObject) p_aadlElement );
		}
		else {
			environment = LuteInterpreter.createInitialEnvironment( p_aadlElement );
		}
		
		if ( p_libraries != null ) {
			try {
				for ( final Map.Entry<String, String> entry : p_libraries.entrySet() ) {
					final String libId = entry.getKey();
					List<Command> parsedLib = libraryCache.get( libId );
					
					if ( parsedLib == null ) {
						parsedLib = LuteInterpreter.parse( entry.getValue(), null );
						libraryCache.put( libId, parsedLib );
					}
					
					environment = execute( environment, parsedLib );
				}
			}
			catch ( final RecognitionException p_ex ) {
				throw new ConstraintParsingException( p_ex );
			}
		}
		
		return environment;
	}
	
	private Environment execute( 	final Environment p_initialEnv,
									final List<Command> p_commands ) {
		Environment currentEnv = p_initialEnv;
		
		for ( final Command cmd : p_commands ) {
			currentEnv =  cmd.exec( currentEnv, logger );
		}
		
		return currentEnv;
	}
//	
//	private static int rockwellLoggerLevel() {
//		if ( logger == null ) {
//			return com.rockwellcollins.atc.meta.Logger.INFO;
//		}
//
//		if ( logger.isTraceEnabled() ) {
//			return com.rockwellcollins.atc.meta.Logger.STATUS;
//		}
//
//		if ( logger.isDebugEnabled() ) {
//			return com.rockwellcollins.atc.meta.Logger.STATUS;
//		}
//
//		if ( logger.isWarnEnabled() ) {
//			return com.rockwellcollins.atc.meta.Logger.WARN;
//		}
//
//		if ( logger.isErrorEnabled() ) {
//			return com.rockwellcollins.atc.meta.Logger.ERROR;
//		}
//
//		if ( logger.isInfoEnabled() ) {
//			return com.rockwellcollins.atc.meta.Logger.INFO;
//		}
//
//		return com.rockwellcollins.atc.meta.Logger.INFO;
//	}

	@Override
	public List<ICompletionProposal> computeCompletionProposals(	final Object p_constraintExpression, 
																	final Map<String, String> p_libraries,
																	final Object... p_context )
	throws ConstraintEvaluationException, SystemException {
		return Collections.emptyList();
	}

	@Override
	public String parse( final Object p_constraint ) {
//		@SuppressWarnings("unchecked")
//		final OCLExpression<Object> expression = (OCLExpression<Object>) p_constraint;
//		
//		return expression.accept( ToStringVisitor.getInstance( expression ) );
		
		throw new UnsupportedOperationException();
	}

	@Override
	public Expr parse( final String p_expressionStr ) 
	throws ConstraintParsingException, SystemException {
		try {
			final LuteParser parser = createParser( p_expressionStr );
			final Expr expression = parser.expr();
			
			if ( expression == null ) {
				throw new ConstraintParsingException( "Expression " + p_expressionStr + " is not a Lute expression." );
			}
			
			return expression;
		}
		catch ( final RecognitionException p_ex ) {
			throw new ConstraintParsingException( p_ex );
		}
	}
	
	private LuteParser createParser( final String p_expression ) {
		try {
			final CharStream charStream = new ANTLRInputStream( new ByteArrayInputStream( p_expression.getBytes() ) );
			final LuteLexer lexer = new LuteLexer( charStream, (Logger) null );
			final TokenStream tokenStream = new CommonTokenStream( lexer );
			
			return new LuteParser( tokenStream, (Logger) null );
		}
		catch ( final IOException p_ex ) {
			// Should never happen with a ByteArrayInputStream...
			throw new IllegalStateException( p_ex );
		}
	}
	
	private static Object getValue( final Val p_value ) {
		if ( p_value instanceof BoolVal ) {
			return p_value.getBool();
		}

		if ( p_value instanceof IntVal ) {
			return p_value.getInt();
		}

		if ( p_value instanceof RealVal ) {
			return p_value.getReal();
		}
		
		if ( p_value instanceof RangeVal ) {
			final RangeVal rangeVal = (RangeVal) p_value;
			final List<Object> range = new ArrayList<Object>( 2 );
			range.add( getValue( rangeVal.getLower() ) );
			range.add( getValue( rangeVal.getUpper() ) );
			
			return range;
		}

		if ( p_value instanceof SetVal ) {
			final List<Object> result = new ArrayList<Object>();
			
			for ( final Val val : ( (SetVal) p_value ).getSet() ) {
				result.add( getValue( val ) );
			}
			
			return result;
		}

		if ( p_value instanceof StringVal ) {
			return p_value.getString();
		}
		
		if ( p_value instanceof AADLVal ) {
			return p_value.getAADL();
		}

		return null;
	}
	
	@Override
	public void libraryContentChanged( final String p_libraryId ) {
		libraryCache.remove( p_libraryId );
	}
}