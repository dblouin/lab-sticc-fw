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
package fr.labsticc.framework.core.util;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.math.BigDecimal;
import java.math.BigInteger;


public class NumberUtil {
	
	private NumberUtil() {
	}
	
	public static <T extends Number> boolean equals( 	T p_num1,
														T p_num2 ) {
		if ( p_num1 == null && p_num2 == null ) {
			return true;
		}

		if ( p_num1 == null || p_num2 == null ) {
			return false;
		}
		
		return p_num1.equals( p_num2 );
	}

	@SuppressWarnings("unchecked")
	public static <T extends Number> boolean isGreaterThan( T p_num1,
															T p_num2 ) {
		if ( p_num1 == null && p_num2 == null ) {
			return false;
		}
		
		if ( p_num1 == null ) {
			return p_num2.floatValue() < 0.0;
		}
		
		if ( p_num2 == null ) {
			return p_num1.floatValue() > 0.0;
		}

		if ( p_num1 instanceof Double ) {
			return (Double) p_num1 > (Double) p_num2;
		}
		
		if ( p_num1 instanceof Integer ) {
			return (Integer) p_num1 > (Integer) p_num2;
		}
		
		if ( p_num1 instanceof Float ) {
			return (Float) p_num1 > (Float) p_num2;
		}

		if ( p_num1 instanceof Long ) {
			return (Long) p_num1 > (Long) p_num2;
		}
		
		if ( p_num1 instanceof BigInteger ) {
			return (T)( (BigInteger) p_num1 ).max( (BigInteger) p_num2 ) == p_num1;
		}
		if ( p_num1 instanceof BigDecimal ) {
			return (T)( (BigDecimal) p_num1 ).max( (BigDecimal) p_num2 ) == p_num1;
		}
		
		throw new IllegalArgumentException( p_num1.getClass().getSimpleName() + " not supported." );
	}

	public static <T extends Number> boolean isGreaterThanOrEqual( 	final T p_num1,
																	final T p_num2 ) {
		return max( p_num1, p_num2 ) == p_num1 && min( p_num1, p_num2 ) == p_num2;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Number> boolean isLessThan( 	final T p_num1,
															final T p_num2 ) {
		if ( p_num1 == null && p_num2 == null ) {
			return false;
		}
		
		if ( p_num1 == null ) {
			return p_num2.floatValue() < 0.0;
		}
		
		if ( p_num2 == null ) {
			return p_num1.floatValue() < 0.0;
		}

		if ( p_num1 instanceof Double ) {
			return (Double) p_num1 < (Double) p_num2;
		}
		
		if ( p_num1 instanceof Integer ) {
			return (Integer) p_num1 < (Integer) p_num2;
		}
		
		if ( p_num1 instanceof Float ) {
			return (Float) p_num1 < (Float) p_num2;
		}

		if ( p_num1 instanceof Long ) {
			return (Long) p_num1 < (Long) p_num2;
		}
		
		if ( p_num1 instanceof BigInteger ) {
			return (T)( (BigInteger) p_num1 ).min( (BigInteger) p_num2 ) == p_num1;
		}
		if ( p_num1 instanceof BigDecimal ) {
			return (T)( (BigDecimal) p_num1 ).min( (BigDecimal) p_num2 ) == p_num1;
		}
		
		throw new IllegalArgumentException( p_num1.getClass().getSimpleName() + " not supported yet." );
	}

	public static <T extends Number> T max( final T p_num1,
											final T p_num2 ) {
		if ( p_num1 == null ) {
			return p_num2;
		}
		
		if ( p_num2 == null ) {
			return p_num1;
		}
		
		if ( isGreaterThan( p_num1, p_num2 ) ) {
			return p_num1;
		}
		
		return p_num2;
	}

	public static <T extends Number> T min( final T p_num1,
											final T p_num2 ) {
		if ( p_num1 == null ) {
			return p_num2;
		}
		
		if ( p_num2 == null ) {
			return p_num1;
		}
		
		if ( isLessThan( p_num1, p_num2 ) ) {
			return p_num1;
		}
		
		return p_num2;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Number> T add( final T p_num1,
											final T p_num2 ) {
		if ( p_num1 == null ) {
			return p_num2;
		}
		
		if ( p_num2 == null ) {
			return p_num1;
		}
		
		if ( p_num1 instanceof Double ) {
			return (T) new Double( (Double) p_num1 + (Double) p_num2 );
		}
		
		if ( p_num1 instanceof Integer ) {
			return (T) new Integer( (Integer) p_num1 + (Integer) p_num2 );
		}
		if ( p_num1 instanceof Float ) {
			return (T) new Float( (Float) p_num1 + (Float) p_num2 );
		}
		if ( p_num1 instanceof Long ) {
			return (T) new Long( (Long) p_num1 + (Long) p_num2 );
		}
		if ( p_num1 instanceof BigInteger ) {
			return (T) ( (BigInteger) p_num1 ).add( (BigInteger) p_num2 );
		}
		if ( p_num1 instanceof BigDecimal ) {
			return (T) ( (BigDecimal) p_num1 ).add( (BigDecimal) p_num2 );
		}
		
		throw new IllegalArgumentException( p_num1.getClass().getSimpleName() + " not supported yet." );
	}
	
	public static <T extends Number> Double average( 	final T p_num1,
														final T p_num2 ) {
		if ( p_num1 == null ) {
			return p_num2 == null ? null : p_num2.doubleValue();
		}
		
		if ( p_num2 == null ) {
			return p_num1.doubleValue();
		}
		
		return ( p_num1.doubleValue() + p_num2.doubleValue() ) / 2.0;
	}
	
	public static Integer convertToInteger( final Number p_number ) {
		if ( p_number == null ) {
			return null;
		}
		
		return p_number.intValue();
	}
	
	public static int compare( 	final Integer p_value1,
								final Integer p_value2 ) {
		if ( p_value1 == null ) {
			return p_value2 == null ? 0 : 1;
		}

		if ( p_value2 == null ) {
			return p_value1 == null ? 0 : - 1;
		}
		
		return p_value1.compareTo( p_value2 );
	}
	
	public static int compare( 	final Double p_value1,
								final Double p_value2 ) {
		if ( p_value1 == null ) {
			return p_value2 == null ? 0 : 1;
		}

		if ( p_value2 == null ) {
			return p_value1 == null ? 0 : - 1;
		}
		
		return p_value1.compareTo( p_value2 );
	}
    
	public static Rectangle calculateBounds(	final Dimension p_occuppiedSize,
												final Dimension p_totalSize,
												final double pd_xProrataPosition,
												final double pd_yProrataPosition ) {
		return new Rectangle( 	( int )( ( p_totalSize.width - p_occuppiedSize.width ) / pd_xProrataPosition ),
								( int )( ( p_totalSize.height - p_occuppiedSize.height ) / pd_yProrataPosition ),
								p_occuppiedSize.width,
								p_occuppiedSize.height );
	}
}
