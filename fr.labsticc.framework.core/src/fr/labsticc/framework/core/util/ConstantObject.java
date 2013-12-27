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

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**  
 * Defines a class of objects that are constant and thus immutable.
 * 
 * <P>
 * Objects descending from this class cannot change unless there is a code change. That
 * makes them uneditable by the user and thus constant. Naturally the descendant classes
 * shouldn't have public constructors.
 * </p>
 *
 * @author <a href="mailto:worderisor@yahoo.com">Robert Simmons jr.</a>
 * @version $Revision: 1.13 $
 *
 * @see DataModelObjectTest
 */
public abstract class ConstantObject implements Serializable {

	private static final long serialVersionUID = -8898698211026172634L;

	/**
	 * Holds the grand constants index map.  Contains references to all constants indexes
	 * that are instantiated indexed by constant name.
	 */
	private static final Map<Class<? extends ConstantObject>, Map<String, ConstantObject>> CONSTANTS_MASTER_INDEX = new HashMap<Class<? extends ConstantObject>, Map<String, ConstantObject>>();

	/**
	 * Error message when user passes a name that is already defined for <tt>this</tt>
	 * class.
	 */
	private static final String ERR_DUP_NAME =
		"ERROR: Constants of the same type have duplicate names.";  //$NON-NLS-1$

	/** Holds value of property name. */
	private final String name;

	/** 
	 * Creates a new instance of ConstantObject.
	 *
	 * @param name The name for the constant object.
	 *
	 * @throws NullPointerException If null is given for the constant name.
	 * @throws IllegalArgumentException If the user passes a null name.
	 */
	protected ConstantObject(final String name) {
		if (name == null) {
			throw new NullPointerException("The name may not be null.");  //$NON-NLS-1$
		}
		Map<String, ConstantObject> constMap = CONSTANTS_MASTER_INDEX.get(getClass());
		if (constMap == null) {
			constMap = new HashMap<String, ConstantObject>();
			CONSTANTS_MASTER_INDEX.put(getClass(), constMap);
		}
		if (constMap.containsKey(name)) {
			throw new IllegalArgumentException(ERR_DUP_NAME);
		}
		this.name = name;
		constMap.put(name, this);
	}

	/** 
	 * Get the constant for the given class and id.
	 *
	 * @param dataType The class for which to retrieve the index.
	 * @param name The name of the constant.
	 *
	 * @return The constant object for the given class and name.
	 */
	public static final ConstantObject lookup(	final Class<? extends ConstantObject> dataType, 
												final String name) {
		return CONSTANTS_MASTER_INDEX.get(dataType).get(name);
	}

	/** 
	 * Get the constants index for the given class.
	 *
	 * @param dataType The class for which to retrieve the index.
	 *
	 * @return An unmodifiable map of constants for the given class.
	 */
	public static final Map<String, ConstantObject> lookup(final Class<? extends ConstantObject> dataType) {
		return Collections.unmodifiableMap( CONSTANTS_MASTER_INDEX.get(dataType) );
	}

	/** 
	 * Getter for property name.
	 *
	 * @return Value of property name.
	 */
	public final String getName() {
		return this.name;
	}

	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getClass()
		           .getName() + "." + this.name;
	}

	/** 
	 * Resolve a read in object.
	 *
	 * @return The resolved object read in.
	 *
	 * @throws ObjectStreamException if there is a problem reading the object.
	 * @throws RuntimeException If the read object doesnt exist.
	 */
	protected Object readResolve() {
		Object result = lookup(getClass(), getName());
		if (result == null) {
			throw new RuntimeException("Constant not found for name: " + getName());  //$NON-NLS-1$
		}
		return result;
	}

  public int compare(  ConstantObject p_const_1, 
                       ConstantObject p_const_2 ) {
    return - 1;                         
  }
}
/* ########## End of File ########## */
