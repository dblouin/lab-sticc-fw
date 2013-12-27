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
package fr.labsticc.framework.core.object;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import fr.labsticc.framework.core.util.ICloneable;

public interface ISmartObjectList<Type> extends List<Type>, ICloneable {

	String MUST_BE_A = "Must be a ";
	char NULL_WILDCARD = ' ';

	void addAll( 	Type[] p_dataObjects, 
					boolean pb_removeOld );

	/**
	 * Removes from this list elements whose field value equals the specified
	 * value according to this list default comparator.
	 * 
	 * @param p_propertyName
	 *            The string identifying the elements value to be compared.
	 * @param p_propertyValue
	 *            The comparison value.
	 * @return A data object list of the removed elements.
	 */
	ISmartObjectList<Type> removeElementsWithPropertyValue( 	String p_propertyName, 
															Object p_propertyValue );

	/**
	 * Returns the first element of this list whose field name value equals to
	 * the specified value according to this list's default comparator.
	 * 
	 * @param p_propertyName
	 *            The field name.
	 * @param p_propertyValue
	 *            The value to compare
	 * @return The first element found.
	 */
	Type getElementWithPropertyValue(	String p_propertyName, 
										Object p_propertyValue );

	/**
	 * Returns the first element of this list whose field name value equals to
	 * the specified value according to the specified comparator.
	 * 
	 * @param p_propertyName
	 *            The field name.
	 * @param p_propertyValue
	 *            The value to compare
	 * @param p_comparator
	 *            The comparator to be used to compare values.
	 * @return The first element found.
	 */
	Type getElementWithPropertyValue(	String p_propertyName, 
										Object p_propertyValue,
										final Comparator<Object> p_comparator );

	/**
	 * Removes from this list elements whose field value equals the specified
	 * value according to the specified comparator.
	 * 
	 * @param p_propertyName
	 *            The string identifying the elements value to be compared.
	 * @param p_propertyValue
	 *            The comparison value.
	 * @param p_comparator
	 *            The comparator to be used.
	 * @return A data object list of the removed elements.
	 */
	ISmartObjectList<Type> removeElementsWithPropertyValue(	String p_propertyName,
															Object p_propertyValue, 
															final Comparator<Object> p_comparator);

	void removeUnvalidElements( final List<Type> p_listOfRemovedElements );

	/**
	 * Removes from this list the first element whose field value equals the
	 * specified value according to the default comparator.
	 * 
	 * @param p_propertyName
	 *            The string identifying the elements value to be compared.
	 * @param p_propertyValue
	 *            The comparison value.
	 * @return The removed element. If no element was found, returns null.
	 */
	Type removeElementWithPropertyValue(	String p_propertyName, 
											Object p_propertyValue );

	/**
	 * Removes from this list the first element whose field value equals the
	 * specified value according to the specified comparator.
	 * 
	 * @param p_propertyName
	 *            The string identifying the elements value to be compared.
	 * @param p_propertyValue
	 *            The comparison value.
	 * @return The removed element. If no element was found, returns null.
	 */
	Type removeElementWithPropertyValue(	String p_propertyName, 
											Object p_propertyValue,
											final Comparator<Object> p_comparator );

	boolean replace( 	Type p_elementToReplace, 
						Type p_newElement );
	
	/**
	 * Retains in this list elements whose field value equals the specified
	 * value according to this list default comparator.
	 * 
	 * @param p_propertyName
	 *            The string identifying the elements value to be compared.
	 * @param p_propertyValue
	 *            The comparison value.
	 * @return A data object list of the removed elements.
	 */
	ISmartObjectList<Type> retainElementsWithPropertyValue(	String p_propertyName,
															Object p_propertyValue );

	/**
	 * Retains from this list elements whose field value equals the specified
	 * value according to the specified comparator.
	 * 
	 * @param p_propertyName
	 *            The string identifying the elements value to be compared.
	 * @param p_propertyValue
	 *            The comparison value.
	 * @param p_comparator
	 *            The comparator to be used.
	 * @return A data object list of the removed elements.
	 */
	ISmartObjectList<Type> retainElementsWithPropertyValue(	String p_propertyName,
															Object p_propertyValue, 
															final Comparator<Object> p_comparator );

	/**
	 * Returns the index of the first element whose field name value equals the
	 * specified value according to this list's default comparator.
	 * 
	 * @param p_propertyName
	 *            The field name.
	 * @param p_propertyValue
	 *            The value to compare
	 * @return The index of the first element.
	 */
	int getIndexOfElementWithPropertyValue( String p_propertyName, 
											Object p_propertyValue );

	/**
	 * Return the index of the first element whose field name value equals the
	 * specified value according to the specified comparator.
	 * 
	 * @param p_propertyName
	 *            The field name.
	 * @param p_propertyValue
	 *            The value to compare
	 * @return The index of the first element.
	 */
	int getIndexOfElementWithPropertyValue(	String p_propertyName, 
											Object p_propertyValue,
											final Comparator<Object> p_comparator );

	/**
	 * Returns elements of this list whose field name values equals to the
	 * specified value according to this list default comparator.
	 * 
	 * @param p_propertyName
	 *            The field name.
	 * @param p_propertyValue
	 *            The value to compare
	 * @return A data list the elements found.
	 */
	ISmartObjectList<Type> getElementsWithPropertyValue(	String p_propertyName,
														Object p_propertyValue );

	/**
	 * Returns elements of this list whose field name values equals to the
	 * specified value according to the specified comparator.
	 * 
	 * @param p_propertyName
	 *            The field name.
	 * @param p_propertyValue
	 *            The value to compare
	 * @param p_comparator
	 *            The comparator to be used to compare values.
	 * @return A data list the elements found.
	 */
	ISmartObjectList<Type> getElementsWithPropertyValue(	String p_propertyName,
														Object p_propertyValue, 
														final Comparator<Object> p_comparator);

	int getNextNumberForProperty( String p_propertyName );

	int getNextNumberFlaggedForProperty( 	final ISmartObjectList<Type> p_listOfRemovedData,
											String p_propertyName );
	
	/**
	 * Returns the greatest Double value for a specific field, considering null
	 * values as 0
	 * 
	 * @param ps_propertyName
	 * @return The greatest double value
	 */
	Double getDoubleGreatest( String p_propertyName );

	/**
	 * Returns the greatest double value for a specific field.
	 * 
	 * @param p_propertyName
	 * @param pb_nullIsZero
	 *            is <code>true</code> if null values should be considered as
	 *            0
	 * @return The greatest double value
	 */
	Double getDoubleGreatest( 	String p_propertyName, 
								boolean pb_nullIsZero );

	/**
	 * Returns the greatest integer value for a specific field, considering
	 * non-existent values as 0
	 * 
	 * @param ps_propertyName
	 * @return The greatest integer value
	 */
	Integer getIntGreatest( String p_propertyName );

	/**
	 * Returns the greatest integer value for a specific field.
	 * 
	 * @param p_propertyName
	 * @param pb_nullIsZero
	 *            is <code>true</code> if non-existent values should be
	 *            considered as 0
	 * @return The greatest Integer value
	 */
	Integer getIntGreatest(	String p_propertyName, 
							boolean pb_nullIsZero);

	/**
	 * Returns the first element that has a null return value for the provided
	 * field name
	 * 
	 * @param p_propertyName
	 *            The field name
	 * @return the found element, null if not found
	 */
	Type getFirstElementWithNullPropertyValue( String p_propertyName );

	/**
	 * Returns the last element that has a null return value for the provided
	 * field name
	 * 
	 * @param p_propertyName
	 *            The field name
	 * @return the found element, null if not found
	 */
	Type getLastElementWithNullPropertyValue( String p_propertyName );

	/**
	 * Returns the sum of an Double field. Invalid elements are not taken in
	 * account
	 * 
	 * @param p_propertyName
	 *            The field name
	 * @return the sum of all values for the provided field
	 */
	double getDoubleSum( String p_propertyName );

	/**
	 * Returns the sum of an Integer field. Invalid elements are not taken in
	 * account
	 * 
	 * @param p_propertyName
	 *            The field name
	 * @return the sum of all values for the provided field
	 */
	int getIntSum( String p_propertyName );

	/**
	 * Returns the last elements in the list that contains a value that equals
	 * to the provided value for the provided field. Invalid elements are not
	 * taken in account
	 * 
	 * @param p_propertyName
	 *            the specified field
	 * @param p_value
	 *            The searched value
	 * @return null if nothing has been found. The value if it has.
	 */
	Type getLastElementWithPropertyValue(	String p_propertyName, 
											Object p_propertyValue );

	/**
	 * Returns the default object comparator used by this list.
	 * 
	 * @return The default object comparator.
	 */
	Comparator<Object> getDefaultComparator();

	void setDefaultComparator(Comparator<Object> p_comparator);

	void setElementsWithPropertyValues(	String p_propertyName, 
										Object p_propertyValue);

	int size( boolean pb_countInvalidElements );

	boolean isEmpty( boolean pb_countInvalidElements );

	Type getTailElement();

	Object[] getPropertyValues(	String p_propertyName, 
								int[] pi_indexes );

	Object getPropertyValueForElement(	String p_outPropertyName,
										String p_inPropertyName, 
										Object p_inPropertyValue,
										final Comparator<Object> p_comparator );
	
	Object getPropertyValueForElement( 	String p_outPropertyName,
										String p_inPropertyName, 
										Object p_inPropertyValue );

	Integer[] getIntegerPropertyValues(	String p_propertyName, 
										int[] pi_indexes);

	Double[] getDoublePropertyValues(	String p_propertyName, 
										int[] pi_indexes);

	Boolean[] getBooleanPropertyValues(	String p_propertyName, 
										int[] pi_indexes);

	String[] getStringPropertyValues(	String p_propertyName, 
										int[] pi_indexes);

	Date[] getDatePropertyValues(	String p_propertyName, 
									int[] pi_indexes);

	Long[] getLongPropertyValues(	String p_propertyName, 
									int[] pi_indexes);

	BigDecimal[] getBigDecimalPropertyValues(	String p_propertyName,
												int[] pi_indexes);

	ISmartObjectList<Type> clone();

	ISmartObjectList<Type> shallowClone();

	//void sort(String p_propertyName);
}
