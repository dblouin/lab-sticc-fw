package fr.labsticc.framework.core.object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class CustomComparatorList<E> extends ArrayList<E> {
	
	private static final long serialVersionUID = 8690532725564772895L;

	private final Comparator<E> comparator;

	/**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param   initialCapacity   the initial capacity of the list
     * @exception IllegalArgumentException if the specified initial capacity
     *            is negative
     */
    public CustomComparatorList( 	int pi_initialCapacity,
    								final Comparator<E> p_comparator ) {
    	super( pi_initialCapacity );
    	
    	comparator = p_comparator;
    }

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public CustomComparatorList( final Comparator<E> p_comparator ) {
    	super();
    	
    	comparator = p_comparator;
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public CustomComparatorList( 	final Collection<? extends E> p_elements, 
    								final Comparator<E> p_comparator ) {
    	super( p_elements );
    	
    	comparator = p_comparator;
    }
	
	@SuppressWarnings("unchecked")
	@Override
    public int indexOf( final Object p_object ) {
		int index = 0;

		for ( final E element : this ) {
			if ( comparator.compare( (E) p_object, element ) == 0 ) {
				return index;
			}
			
			index++;
		}

    	return -1;
	}
}
