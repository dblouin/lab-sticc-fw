/*
Copyright (c) 2011, Rockwell Collins.
Developed with the sponsorship of the Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data, 
including any software or models in source or binary form, as well as any drawings, specifications, 
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so, 
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or 
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE 
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
*/
package com.rockwellcollins.atc.meta.lute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.BooleanLiteral;
import org.osate.aadl2.Bus;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentCategory;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.Connection;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.ListValue;
import org.osate.aadl2.Memory;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.NumberValue;
import org.osate.aadl2.Processor;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyAssociation;
import org.osate.aadl2.PropertyConstant;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.RangeValue;
import org.osate.aadl2.RealLiteral;
import org.osate.aadl2.ReferenceValue;
import org.osate.aadl2.StringLiteral;
import org.osate.aadl2.UnitLiteral;
import org.osate.aadl2.UnitsType;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.instance.InstanceReferenceValue;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.aadl2.properties.PropertyNotPresentException;
import org.osate.xtext.aadl2.properties.util.DeploymentProperties;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;
import org.osate.xtext.aadl2.properties.util.GetProperties;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

public class FnCallExpr extends Expr {
	final private String fn;
	final private List<Expr> args;

	public FnCallExpr(String fn, List<Expr> args) {
		super();
		this.fn = fn;
		this.args = args;
	}
	
	private Val getProperty( final List<Val> p_argValues ) {
		expectArgs( p_argValues, new int[] { 2, 3 } );

		final NamedElement aadl = p_argValues.get( 0 ).getAADL();
		final String property = p_argValues.get( 1 ).getString();
		Val result = null;
		
		if ( p_argValues.size() == 2 ) {
			result = getProperty( aadl, property );
		}
		else if ( p_argValues.size() == 3 ) {
			final String unit = p_argValues.get( 2 ).getString();
			result = getProperty( aadl, property, unit );
		}
//
		// DB: Allow null values to be returned when no property association is defined.
//		if ( result == null ) {
//			throw new LuteException( "Failed to find property " + property );
//		}
		
		return result;
	}
	
	private Val getPropertyConstant( final List<Val> p_argValues ) {
		expectArgs( p_argValues, new int[] { 1, 2 } );

		final String property = p_argValues.get( 0 ).getString();
		Val result = null;
		
		if ( p_argValues.size() == 1 ) {
			result = getPropertyConstant( property );
		}
		else if ( p_argValues.size() == 2 ) {
			final String unit = p_argValues.get( 1 ).getString();
			result = getPropertyConstant( property, unit );
		}
//
		// DB: Allow null values to be returned when no property association is defined.
//		if ( result == null ) {
//			throw new LuteException( "Failed to find property " + property );
//		}
		
		return result;
	}
	
	private Number getNumberValue( final Val p_value ) {
		if ( p_value instanceof IntVal ) {
			return p_value.getInt();
		}

		return p_value == null ? null : p_value.getReal();
	}
	
	private Val createNumberValue( final Number p_value ) {
//		if ( p_value instanceof BigInteger ) {
//			return new IntVal( ( (BigInteger) p_value ).longValue() );
//		}

		if ( p_value instanceof Long ) {
			return new IntVal( (Long) p_value );
		}

		if ( p_value instanceof Integer ) {
			return new IntVal( (Integer) p_value );
		}

//		if ( p_value instanceof BigDecimal ) {
//			return new RealVal( ( (BigDecimal) p_value ).doubleValue() );
//		}

		if ( p_value instanceof Double ) {
			return new RealVal( (Double) p_value );
		}

		if ( p_value instanceof Float ) {
			return new RealVal( (Float) p_value );
		}
		
		throw new LuteException( "Unknown number type " + p_value );
	}

	@Override
	public Val eval(Environment env) {
		final List<Val> argValues = new ArrayList<Val>();
		
		for ( final Expr arg : args ) {
			if ( arg == null ) {
				final int index = args.indexOf( arg );
				
				throw new LuteException( "Argument number " + index + " is null for function call " + fn );
			}
			
			argValues.add(arg.eval(env));
		}
		
		if ( fn.equals( LuteConstants.PROPERTY ) ) {
			return getProperty( argValues );
		}
		
		if ( fn.equals( LuteConstants.PROPERTY_CONSTANT ) ) {
			return getPropertyConstant( argValues );
		}
		
		if ( fn.equals( LuteConstants.PROPERTY_EXISTS ) ) {
			expectArgs( argValues, 2);
			final NamedElement aadl = argValues.get(0).getAADL();
			String property = argValues.get(1).getString();
			Val result = getProperty(aadl, property);
			return new BoolVal( result != null );
			
		}

		if (fn.equals( LuteConstants.IS_BOUND_TO )) {
			expectArgs( argValues, 2 );
			final NamedElement s = argValues.get(0).getAADL();
			final NamedElement t = argValues.get(1).getAADL();
			
			return new BoolVal( isBoundTo(s, t) );
		}
		
		if ( fn.equals( LuteConstants.SOURCE ) ) {
			expectArgs( argValues, 1 );
			final NamedElement aadlElement = argValues.get(0).getAADL();
			
			if ( aadlElement instanceof ConnectionInstance ) {
				final ConnectionInstance conn = (ConnectionInstance) aadlElement;
				
				return new AADLVal(conn.getSource());
			}
			
			if ( aadlElement instanceof Connection ) {
				return new AADLVal( (NamedElement) ( (Connection) aadlElement ).getSource() );
			}
			
			throw new LuteException( "Source called on non-connection object." );
		} 
		
		if ( fn.equals( LuteConstants.DESTINATION ) ) {
			expectArgs( argValues, 1 );
			final NamedElement aadlElement = argValues.get(0).getAADL();
			
			if ( aadlElement instanceof ConnectionInstance ) {
				final ConnectionInstance conn = (ConnectionInstance) aadlElement;

				return new AADLVal(conn.getDestination());
			}

			if ( aadlElement instanceof Connection ) {
				return new AADLVal( (NamedElement) ( (Connection) aadlElement ).getDestination() );
			}

			throw new LuteException("Destination called on non-connection object");
			
		}
		
		if ( fn.equals( LuteConstants.MEMBER ) ) {
			expectArgs( argValues, 2 );
			Val e = argValues.get(0);
			Collection<Val> set = argValues.get(1).getSet();
			
			return new BoolVal(set.contains(e));
			
		} 
		
		if (fn.equals( LuteConstants.OWNER ) ) {
			expectArgs( argValues, 1 );
			final NamedElement aadlElement = argValues.get( 0 ).getAADL();
			
			if ( aadlElement.getOwner() instanceof NamedElement ) {
				return new AADLVal( (NamedElement) aadlElement.getOwner() );
			}

			throw new LuteException("Owner called on un-owned object");
			
		} 
		
		if (fn.equals( LuteConstants.IS_SUBCOMPONENT_OF )) {
			expectArgs( argValues, 2 );
			NamedElement sub = argValues.get(0).getAADL();
			final NamedElement top = argValues.get(1).getAADL();
			
			while ( sub.getOwner() instanceof NamedElement ) {
				final NamedElement owner = (NamedElement) sub.getOwner();
				
				if ( top.equals( owner ) ) {
					return new BoolVal(true);
				}
				
				sub = owner;
			}
			
			return new BoolVal(false);
		}
		
		if ( fn.equals( LuteConstants.IS_OF_TYPE ) ) {
			expectArgs( argValues, 2 );
			final NamedElement component = argValues.get(0).getAADL();
			final String aadlType = argValues.get(1).getString();
			
			return new BoolVal( isOfType( component, aadlType ) );
		}
		
		if ( fn.equals( LuteConstants.MAX ) ) {
			if (argValues.size() == 1) {
				Val arg = argValues.get(0);
				if (arg instanceof SetVal) {
					SetVal set = (SetVal) arg;
					return max(set.getSet());
				} 

				return arg;
			}
				
			return max(argValues);
		} 
		
		if ( fn.equals( LuteConstants.MIN ) ) {
			if (argValues.size() == 1) {
				Val arg = argValues.get(0);
				if (arg instanceof SetVal) {
					SetVal set = (SetVal) arg;
					return min(set.getSet());
				}
					
				return arg;
			}
				
			return min(argValues);
		} 

		if ( fn.equals( LuteConstants.SUM ) ) {
			if (argValues.size() == 1) {
				Val arg = argValues.get(0);
				if (arg instanceof SetVal) {
					SetVal set = (SetVal) arg;
					
					return sum(set.getSet());
				}
					
				return arg;
			}
				
			return sum(argValues);
		} 
		
		if ( fn.equals( LuteConstants.CARDINAL ) ) {
			expectArgs( argValues, 1 );
			
			final Val value = argValues.get(0);
			
			if ( value == null ) {
				return null;
			}

			final Collection<Val> set = value.getSet();
			
			return new IntVal(set.size());
		} 
		
		if (fn.equals( LuteConstants.FIRST ) ) {
			expectArgs( argValues, 1 );
			final List<Val> set = argValues.get(0).getSet();
			
			if ( set.isEmpty() ) {
				throw new LuteException( "Set " + set + " is empty." );
			}
			
			return set.get( 0 );
		} 
		
		if (fn.equals( LuteConstants.AT ) ) {
			expectArgs( argValues, 2 );
			final List<Val> set = argValues.get( 0 ).getSet();
			
			if ( set.isEmpty() ) {
				throw new LuteException( "Set " + set + " is empty." );
			}

			final Long index = argValues.get( 1 ).getInt();
			
			return set.get( index.intValue() );
		} 
		
		if ( fn.equals( LuteConstants.LAST ) ) {
			expectArgs( argValues, 1 );
			final List<Val> set = argValues.get(0).getSet();
			
			if ( set.isEmpty() ) {
				throw new LuteException( "Set " + set + " is empty." );
			}
			
			return set.get( set.size() - 1 );
		} 
		
		if (fn.equals( LuteConstants.LOWER ) ) {
			expectArgs( argValues, 1);
			RangeVal range = argValues.get(0).getRange();
			return range.getLower();
			
		}

		if (fn.equals( LuteConstants.UPPER ) ) {
			expectArgs( argValues, 1 );
			
			final RangeVal range = argValues.get(0).getRange();

			return range.getUpper();
		} 

		// String Operations
		if ( fn.equals( LuteConstants.CONCAT ) ) {
			expectArgs( argValues, 2 );
			final String left = argValues.get(0).getString();
			final String right = argValues.get(1).getString();
			
			return new StringVal( left + right );
		} 

		if ( fn.equals( LuteConstants.SUBSTRING ) ) {
			expectArgs( argValues, 3 );
			final String strVal = argValues.get(0).getString();
			final Long beginIndex = argValues.get( 1 ).getInt();
			final Long endIndex = argValues.get( 2 ).getInt();
			
			return new StringVal( strVal.substring( beginIndex.intValue(), endIndex.intValue()) );
		} 

		if ( fn.equals( LuteConstants.INDEX_OF ) ) {
			expectArgs( argValues, 2 );
			final String strVal = argValues.get( 0 ).getString();
			final String searchVal = argValues.get( 1 ).getString();
			
			return new IntVal( strVal.indexOf( searchVal ) );
		} 

		if ( fn.equals( LuteConstants.LENGTH ) ) {
			expectArgs( argValues, 1 );
			final String strVal = argValues.get(0).getString();
			
			return new IntVal( strVal.length() );
		} 
		
		if ( fn.equals( LuteConstants.EQUALS ) ) {
			expectArgs( argValues, 2 );
			final Val left = argValues.get(0);
			final Val right = argValues.get(1);

			return new BoolVal( equals( left, right ) );
		} 
		
		if ( fn.equals( LuteConstants.NOT_EQUALS ) ) {
			expectArgs( argValues, 2 );
			Val left = argValues.get(0);
			Val right = argValues.get(1);
			
			return new BoolVal( !equals( left, right ) );
		} 
		
		if ( fn.equals( LuteConstants.GREATER_THAN ) ) {
			expectArgs( argValues, 2 );
			final Number left = getNumberValue( argValues.get(0) );
			final Number right = getNumberValue( argValues.get(1) );

			return new BoolVal( NumberUtil.isGreaterThan( left, right ) );
		} 
		
		if ( fn.equals( LuteConstants.LESS_THAN )) {
			expectArgs( argValues, 2 );
			final Number left = getNumberValue( argValues.get(0) );
			final Number right = getNumberValue( argValues.get(1) );

			return new BoolVal( NumberUtil.isLessThan( left, right ) );
		} 

		if ( fn.equals( LuteConstants.GREATER_THAN_OR_EQUALS ) ) {
			expectArgs( argValues, 2 );
			final Number left = getNumberValue( argValues.get(0) );
			final Number right = getNumberValue( argValues.get(1) );

			return new BoolVal( NumberUtil.isGreaterThanOrEqual( left, right ) );
		}
		
		if ( fn.equals( LuteConstants.LESS_THAN_OR_EQUALS ) ) {
			expectArgs( argValues, 2 );
			final Number left = getNumberValue( argValues.get(0) );
			final Number right = getNumberValue( argValues.get(1) );

			return new BoolVal( NumberUtil.isLessThanOrEqual( left, right ) );
		} 
		
		if (fn.equals( LuteConstants.PLUS ) ) {
			expectArgs( argValues, 2 );
			final Number left = getNumberValue( argValues.get(0) );
			final Number right = getNumberValue( argValues.get(1) );
			final Number result = NumberUtil.add( left, right );
			
			return createNumberValue( result );
		} 
		
		if ( fn.equals( LuteConstants.MINUS ) ) {
			expectArgs( argValues, 2 );

			final Number left = getNumberValue( argValues.get(0) );
			final Number right = getNumberValue( argValues.get(1) );
			final Number result = NumberUtil.subtract( left, right );
			
			return createNumberValue( result );
		}
		
		if ( fn.equals( LuteConstants.MULTIPLY ) ) {
			expectArgs( argValues, 2 );

			final Number left = getNumberValue( argValues.get(0) );
			final Number right = getNumberValue( argValues.get(1) );
			final Number result = NumberUtil.multiply( left, right );
			
			return createNumberValue( result );
		}
		
		if ( fn.equals( LuteConstants.DIVIDE ) ) {
			expectArgs( argValues, 2 );
			
			final Number left = getNumberValue( argValues.get( 0 ) );
			final Number right = getNumberValue( argValues.get( 1 ) );
			final Number result = NumberUtil.divide( left, right );
			
			return createNumberValue( result );
		}
		
		if ( fn.equals( LuteConstants.POWER ) ) {
			expectArgs( argValues, 2 );

			final Number left = getNumberValue( argValues.get( 0 ) );
			final Number right = getNumberValue( argValues.get( 1 ) );
			final Number result = NumberUtil.power( left, right );
			
			return createNumberValue( result );
		}

		final FunctionDefinition fd = env.lookupFn(fn);
			
		return fd.eval(argValues);
	}
	
	private static boolean equals( 	final Object p_obj1,
									final Object p_obj2 ) {
		if ( p_obj1 == null ) {
			return p_obj2 == null;
		}

		return p_obj1.equals( p_obj2 );
	}
	
	private static boolean isOfType( 	final NamedElement p_component, 
										final String p_type ) {
		if ( p_component instanceof ComponentInstance ) {
			final ComponentClassifier classifier = ( (ComponentInstance) p_component ).getComponentClassifier();
		
			return isOfType( classifier, p_type );
		}
		
		if ( p_component instanceof Classifier ) {
			return isOfType( (Classifier) p_component, p_type );
		}
		
		throw new LuteException( "Element " + p_component.getName() + " is not a component." );
	}

	private static boolean isOfType( 	final Classifier p_component, 
										final String p_type ) {
		if ( p_component == null ) {
			return false;
		}
		
		if ( p_type.equals( p_component.getQualifiedName() ) ) {
			return true;
		}

		if ( p_component instanceof ComponentImplementation ) {
			if ( isOfType( ( (ComponentImplementation) p_component ).getType(), p_type ) ) {
				return true;
			}
		}

		return isOfType( p_component.getExtended(), p_type );
	}

	private IntVal max(Collection<Val> vals) {
		if (vals.isEmpty()) {
			throw new LuteException( "Max called with no arguments." );
		}
		
		Long  r = vals.iterator().next().getInt();
		
		for (Val v : vals) {
			if (v.getInt().compareTo(r) > 0) {
				r = v.getInt();
			}
		}
		
		return new IntVal(r);
	}
	
	private IntVal min(Collection<Val> vals) {
		if (vals.isEmpty()) {
			throw new LuteException( "Min called with no arguments." );
		}

		Long r = vals.iterator().next().getInt();
		for (Val v : vals) {
			if (v.getInt().compareTo(r) < 0) {
				r = v.getInt();
			}
		}
		return new IntVal(r);
	}
	
	private RealVal sum(Collection<Val> vals) {
		if (vals.isEmpty()) {
			throw new LuteException( "Sum called with no arguments." );
		}
		
		Number total = Double.valueOf( 0.0 );
		
		for ( final Val value : vals ) {
			final Number valueToAdd = getNumberValue( value );
			
			total = NumberUtil.add( total, valueToAdd );
		}
		
//		if ( total instanceof BigDecimal ) {
//			return new RealVal( ( (BigDecimal) total );
//		}

		return new RealVal( total.doubleValue() );
	}

	private void expectArgs( 	final List<Val> p_values,
								int... p_allowedSizes ) {
		if ( p_values != null ) {
			for ( final Val value : p_values ) {
				if ( value == null ) {
					final StringBuilder message = new StringBuilder( "Function " );
					message.append( fn );
					message.append( " cannot handle null values." );
	
					throw new LuteException( message.toString() );
				}
			}
		}
		
		for ( final int expSize : p_allowedSizes ) {
			if ( p_values.size() == expSize ) {
				return;
			}
		}

		final StringBuilder message = new StringBuilder( "Function " );
		message.append( fn );
		message.append( " expects " );
		int ctr = 1;
		
		for ( final int expSize :  p_allowedSizes ) {
			message.append( expSize );
			
			if ( ctr < p_allowedSizes.length ) {
				message.append( " or " );
				ctr++;
			}
		}

		message.append( " arguments" );
		
		throw new LuteException( message.toString() );
	}
	
	private Val getProperty(	final NamedElement p_aadlElement,
								final String p_propertyName ) {
		return getProperty( p_aadlElement, p_propertyName, null );
	}
	
	private Val getProperty(	final NamedElement p_aadlElement,
								final String p_propertyName,
								final String p_unit ) {
		final Property propDef = getPropertyDefinition( p_aadlElement, p_propertyName );
		
		try {
			if ( propDef.isList() ) {
				final List<Val> list = new ArrayList<Val>();
				
				for ( final PropertyExpression propExprIter : p_aadlElement.getPropertyValueList( propDef ) ) {
					list.add( AADLPropertyValueToValue( propExprIter, p_unit ) );
				}
				
				return new SetVal( list );
			}
			
			final PropertyExpression propExpr = PropertyUtils.getSimplePropertyValue( p_aadlElement, propDef );
	
			return AADLPropertyValueToValue( propExpr, p_unit );
		}
		catch ( final PropertyNotPresentException p_ex ) {
			return null;
		}
	}
	
	private Val getPropertyConstant( final String p_propertyName ) {
		final PropertyConstant propConst = EMFIndexRetrieval.getPropertyConstantInWorkspace( OsateResourceUtil.getResourceSet(), p_propertyName );
		
		return AADLPropertyValueToValue( propConst.getConstantValue(), null );
	}
	
	private Val getPropertyConstant( 	final String p_propertyName,
										final String p_unit ) {
		final PropertyConstant propConst = EMFIndexRetrieval.getPropertyConstantInWorkspace( OsateResourceUtil.getResourceSet(), p_propertyName );
		
		return AADLPropertyValueToValue( propConst.getConstantValue(), p_unit );
	}
	
	private Property getPropertyDefinition( final EObject p_context,
											final String p_propertyName ) {
		final Property propDef = GetProperties.lookupPropertyDefinition( p_context, null, p_propertyName );
		
		if ( propDef == null ) {
			throw new LuteException( "Property " + p_propertyName + " is not defined." );
		}
		
		return propDef;
	}
	
	private UnitLiteral findUnitLiteral( 	final PropertyExpression p_propExpr,
											final String p_unit ) {
		if ( p_unit == null ) {
			return null;
		}

		
		EObject propContainer = p_propExpr.eContainer();
		
		while ( propContainer != null && !( propContainer instanceof PropertyAssociation ) && !( propContainer instanceof PropertyConstant ) ) {
			propContainer = propContainer.eContainer();
		}
		
		final UnitLiteral unitLit;
		
		if ( propContainer instanceof PropertyAssociation ) {
			unitLit = GetProperties.findUnitLiteral( ( (PropertyAssociation) propContainer ).getProperty(), p_unit );
		}
		else if ( propContainer instanceof PropertyConstant ) {
			final PropertyExpression propExpr = ( (PropertyConstant) propContainer ).getConstantValue();
			
			if ( ! ( propExpr instanceof NumberValue ) ) {
				throw new LuteException( "Property constant expression " + propExpr + " has no unit." );
			}
			
			final NumberValue numberVal = (NumberValue) propExpr;
			final UnitLiteral constantUnit = numberVal.getUnit();

			if ( constantUnit == null ) {
				throw new LuteException( "Property constant value " + propExpr + " has no unit." );
			}
			
			unitLit = GetProperties.findUnitLiteral( p_propExpr, ( (UnitsType) constantUnit.getOwner() ).getQualifiedName(), p_unit );
		}
		else {
			throw new LuteException( "Unknown unit " + p_unit );
		}
		
		return unitLit;
	}
	
	private Val AADLPropertyValueToValue( 	final PropertyExpression p_expr,
											final String p_unit ) {
		if ( p_expr == null ) {
			return null;
		} 

		if ( p_expr instanceof BooleanLiteral) {
			final BooleanLiteral lit = (BooleanLiteral) p_expr;
			
			return new BoolVal(lit.getValue());
		}
		
		if ( p_expr instanceof StringLiteral) {
			final StringLiteral lit = (StringLiteral) p_expr;

			return new StringVal(lit.getValue());
		}
		
		if ( p_expr instanceof NumberValue ) {
			final UnitLiteral unitLit;
			
			if ( p_unit == null ) {
				unitLit = null;
			}
			else {
				unitLit = findUnitLiteral( p_expr, p_unit );
				
				if ( unitLit == null ) {
					throw new LuteException( "Unknown unit: " + p_unit );
				}
			}
			
			if ( p_expr instanceof IntegerLiteral ) {
				final IntegerLiteral lit = (IntegerLiteral) p_expr;
				final long value;
				
				if ( unitLit == null ) {
					value = (long) lit.getScaledValue();
				}
				else {
					value = (long) lit.getScaledValue( unitLit );
				}
				
				return new IntVal( value );
			}
			
			if ( p_expr instanceof RealLiteral ) {
				final RealLiteral lit = (RealLiteral) p_expr;
				final double value;
				
				if ( unitLit == null ) {
					value = lit.getScaledValue();
				}
				else {
					value = lit.getScaledValue( unitLit );
				}
				
				return new RealVal( value );
			}
		}

		if ( p_expr instanceof RangeValue) {
			RangeValue range = (RangeValue) p_expr;
			
			return new RangeVal(
	 			AADLPropertyValueToValue( range.getMinimumValue(), p_unit ),
				AADLPropertyValueToValue( range.getMaximumValue(), p_unit ),
				AADLPropertyValueToValue( range.getDelta(), p_unit )
			);
		}
		
		if ( p_expr instanceof InstanceReferenceValue ) {
			final InstanceReferenceValue irv = (InstanceReferenceValue) p_expr;
			
			return new AADLVal( getReferencedObject( irv ) );
		}
		
		if ( p_expr instanceof ReferenceValue ) {
			final ReferenceValue refValue = (ReferenceValue) p_expr;
			
			return new AADLVal( getReferencedObject( refValue ) );
		}
		
		if ( p_expr instanceof ListValue ) {
			final List<Val> values = new ArrayList<Val>();
			
			for ( final PropertyExpression propExprIter : ( (ListValue) p_expr ).getOwnedListElements() ) {
				values.add( AADLPropertyValueToValue( propExprIter, p_unit ) );
			}
			
			return new SetVal( values );
		}

		throw new LuteException( "Unknown AADL property value " + p_expr );
	}
	
	private boolean isBoundTo( 	final NamedElement p_source,
								final NamedElement p_target ) {
		if ( p_target instanceof ComponentInstance ) {
			final ComponentCategory category = ( (ComponentInstance) p_target ).getCategory();
			
			if ( category == ComponentCategory.PROCESSOR ) {
				return checkBinding( p_source, DeploymentProperties._NAME, DeploymentProperties.ACTUAL_PROCESSOR_BINDING, p_target );
			} 
			
			if ( category == ComponentCategory.MEMORY ) {
				return checkBinding( p_source, DeploymentProperties._NAME, DeploymentProperties.ACTUAL_MEMORY_BINDING, p_target );
			}

			if ( category == ComponentCategory.BUS ) {
				return checkBinding( p_source, DeploymentProperties._NAME, DeploymentProperties.ACTUAL_CONNECTION_BINDING, p_target );
			}
		}
		else if ( p_target instanceof ComponentClassifier ) {
			if ( p_target instanceof Processor ) {
				return checkBinding( p_source, DeploymentProperties._NAME, DeploymentProperties.ACTUAL_PROCESSOR_BINDING, p_target );
			} 
			
			if ( p_target instanceof Memory ) {
				return checkBinding( p_source, DeploymentProperties._NAME, DeploymentProperties.ACTUAL_MEMORY_BINDING, p_target );
			}

			if ( p_target instanceof Bus ) {
				return checkBinding( p_source, DeploymentProperties._NAME, DeploymentProperties.ACTUAL_CONNECTION_BINDING, p_target );
			}
		}
		
		throw new LuteException( "Invalid arguments to Is_Bound_To" );
	}

	private boolean checkBinding(	final NamedElement p_source,
									final String p_propertySetName,
									final String p_bindingProperty,
									final NamedElement p_target ) {
		// There should be an easier way to check this, but OSATE 2 seems to
		// have several gaps in it's implementation. Thus we manually crawl up
		// the ownership chain for s looking for the matching property association.
//		while (true) {
//			for (PropertyAssociation pa : s.getOwnedPropertyAssociations()) {
//				if (pa.getProperty().getName().toLowerCase().equals( p_bindingProperty )) {
//					PropertyExpression propExpr = s.getSimplePropertyValue(pa.getProperty());
//					/*if (pa.getValue() instanceof ListPropertyValue) {
//						ListPropertyValue list = (ListPropertyValue) pa.getValue();
//						for (PropertyExpression pe : list.getValue()) {*/
//							if (propExpr instanceof InstanceReferenceValue) {
//								InstanceReferenceValue ref = (InstanceReferenceValue) propExpr;
//								if (t.equals(ref.getReferencedInstanceObject())) {
//									return true;
//								}
//							}
//		
//			if (s.getOwner() instanceof InstanceObject) {
//				s = (InstanceObject) s.getOwner();
//			} else {
//				break;
//			}
//	}
		final Property property = GetProperties.lookupPropertyDefinition( p_source, p_propertySetName, p_bindingProperty );
		final List<? extends PropertyExpression> propertyValues = p_source.getPropertyValueList( property );

		if ( p_source instanceof InstanceObject ) {
			for ( final PropertyExpression propertyExpression : propertyValues ){
				if ( p_target.equals( getReferencedObject( (InstanceReferenceValue) propertyExpression ) ) ) {
					return true;
				}
			}
			
			return false;
		}
		
		for ( final PropertyExpression propertyExpression : propertyValues ){
			if ( p_target.equals( getReferencedObject( (ReferenceValue) propertyExpression ) ) ) {
				return true;
			}
		}

		return false;
	}
	
	private NamedElement getReferencedObject( final InstanceReferenceValue p_propertyValue ) {
		return p_propertyValue.getReferencedInstanceObject() ;
	}
	
	private NamedElement getReferencedObject( final ReferenceValue p_propertyValue ) {
		return p_propertyValue.getContainmentPathElements().get( 0 ).getNamedElement();
	}
}