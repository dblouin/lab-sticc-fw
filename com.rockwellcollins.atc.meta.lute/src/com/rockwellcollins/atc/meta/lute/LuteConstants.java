package com.rockwellcollins.atc.meta.lute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;

import org.osate.aadl2.ComponentCategory;

public class LuteConstants {

	// Property
	public static final String PROPERTY = "Property";
	public static final String PROPERTY_CONSTANT = "Property_Constant";
	public static final String PROPERTY_EXISTS = "Property_Exists";

	public static final String SOURCE = "Source";
	public static final String DESTINATION = "Destination";

	public static final String OWNER = "Owner";
	public static final String IS_SUBCOMPONENT_OF = "Is_Subcomponent_Of";
	public static final String IS_OF_TYPE = "Is_Of_Type";
	public static final String IS_BOUND_TO = "Is_Bound_To";

	// Sets
	public static final String CONNECTION_SET = "Connection_Set";
	public static final String MEMBER = "Member";
	public static final String MAX = "Max";
	public static final String MIN = "Min";
	public static final String SUM = "Sum";
	public static final String CARDINAL = "Cardinal";
	public static final String FIRST = "First";
	public static final String FOR = "for";
	public static final String AT = "At";
	public static final String LAST = "Last";
	public static final String LOWER = "Lower";
	public static final String UPPER = "Upper";
	
	public static final String CONCAT = "Concat";
	public static final String SUBSTRING = "Substring";
	public static final String INDEX_OF = "Index_Of";
	public static final String LENGTH = "Length";
	
	public static final String EQUALS = "=";
	public static final String NOT_EQUALS = "!=";
	public static final String GREATER_THAN = ">";
	public static final String LESS_THAN = "<";
	public static final String GREATER_THAN_OR_EQUALS = ">=";
	public static final String LESS_THAN_OR_EQUALS = "<=";

	public static final String PLUS = "+";
	public static final String MINUS = "-";
	public static final String MULTIPLY = "*";
	public static final String DIVIDE = "/";
	public static final String POWER = "^";
	
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final String NOT = "not";
	public static final String SELF = "Self";
	public static final String THEOREM = "theorem";
	public static final String END = "end";
	public static final String FOREACH = "foreach";
	public static final String IN = "in";
	public static final String DO = "do";
	public static final String CHECK = "check";
	public static final String PRINT = "print";
	public static final String IF = "if";
	public static final String THEN = "then";
	public static final String ELSE = "else";
	public static final String AND = "and";
	public static final String OR = "or";

    private static final String[] BASIC_LUTE_KEYWORDS = {
    	AND,
    	CONNECTION_SET,
    	CHECK,
    	DO,
    	ELSE,
    	END,
    	FALSE,
    	FOR,
    	FOREACH,
    	IF,
    	IN,
    	NOT,
    	OR,
    	PRINT,
    	THEN,
    	SELF,
    	THEOREM,
    	TRUE,
    };
    
    public static final String[] LUTE_KEYWORDS;
    
    static {
    	final Collection<String> keyWords = new ArrayList<String>( Arrays.asList( BASIC_LUTE_KEYWORDS ) );
    	
		for ( final ComponentCategory category : ComponentCategory.VALUES ) {
			keyWords.add( luteSetName( category.getName() ) );
		}
		
		LUTE_KEYWORDS = keyWords.toArray( new String[ keyWords.size() ] );
    }
	
    public static String luteSetName( 	final String p_categoryName ) {
		return capitalizeName( p_categoryName, "_", "Set" );
	}

	public static String capitalizeName( 	final String p_categoryName,
											final String p_replacementStr,
											final String p_suffix ) {
		final StringTokenizer tokenizer = new StringTokenizer( p_categoryName, "\\ " );
		final StringBuilder luteSetNameBdr = new StringBuilder();
		
		while ( tokenizer.hasMoreTokens() ) {
			final String token = tokenizer.nextToken();
			luteSetNameBdr.append( token.substring( 0, 1 ).toUpperCase() );
			luteSetNameBdr.append( token.substring( 1 ) );
			luteSetNameBdr.append( p_replacementStr );
		}
		
		luteSetNameBdr.append( p_suffix );
		
		return luteSetNameBdr.toString();
	}
}
