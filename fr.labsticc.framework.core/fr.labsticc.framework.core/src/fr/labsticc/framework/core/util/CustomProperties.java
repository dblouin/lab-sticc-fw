package fr.labsticc.framework.core.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Redefines the way properties are stored to have them in a specified order.
 * 
 * @author dblouin
 */
public class CustomProperties extends Properties {

	private static final long serialVersionUID = 5791239766759199359L;

    public void store( 	final OutputStream p_outputStr,
    					final String p_comments,
    					final boolean pb_sortKeys )
    throws IOException {
        store0( new BufferedWriter( new OutputStreamWriter( p_outputStr, "8859_1" ) ),
        		p_comments,
        		true,
        		pb_sortKeys );
    }
    
    public void store(	final Writer p_writer,
    					final String p_comments,
    					final boolean pb_sortKeys )
    throws IOException {
    	store0( ( p_writer instanceof BufferedWriter ) ? (BufferedWriter) p_writer : new BufferedWriter( p_writer ),
    			p_comments,
    			false,
    			pb_sortKeys );
    }
    
	protected void store0( 	final BufferedWriter p_bufferWriter,
    						final String p_comments,
    						final boolean pb_escUnicode,
    						final boolean pb_sortKeys )
    throws IOException {
        if ( p_comments != null ) {
            writeComments( p_bufferWriter, p_comments );
        }
        
        p_bufferWriter.write("#" + new Date().toString());
        p_bufferWriter.newLine();
        
        synchronized (this) {
        	final Collection<String> keys;
        	
    		if ( pb_sortKeys ) {
    			keys = new ArrayList<String>( stringPropertyNames() );
    			Collections.sort( (List<String>) keys );
    		}
    		else {
    			keys = stringPropertyNames();
    		}
    		
            //for (Enumeration e = keys(); e.hasMoreElements();) {
    		for ( String key : keys ) {
                //String key = (String)e.nextElement();
                String val = (String)get(key);
                key = saveConvert(key, true, pb_escUnicode );
				/* No need to escape embedded and trailing spaces for value, hence
				 * pass false to flag.
				 */
				val = saveConvert(val, false, pb_escUnicode );
				p_bufferWriter.write(key + "=" + val);
				p_bufferWriter.newLine();
		    }
        }
        
        p_bufferWriter.flush();
    }

    /*
     * Converts unicodes to encoded &#92;uxxxx and escapes
     * special characters with a preceding slash
     */
    private String saveConvert(String theString,
			       boolean escapeSpace,
			       boolean escapeUnicode) {
        int len = theString.length();
        int bufLen = len * 2;
        if (bufLen < 0) {
            bufLen = Integer.MAX_VALUE;
        }
        StringBuffer outBuffer = new StringBuffer(bufLen);

        for(int x=0; x<len; x++) {
            char aChar = theString.charAt(x);
            // Handle common case first, selecting largest block that
            // avoids the specials below
            if ((aChar > 61) && (aChar < 127)) {
                if (aChar == '\\') {
                    outBuffer.append('\\'); outBuffer.append('\\');
                    continue;
                }
                outBuffer.append(aChar);
                continue;
            }
            switch(aChar) {
		case ' ':
		    if (x == 0 || escapeSpace) 
			outBuffer.append('\\');
		    outBuffer.append(' ');
		    break;
                case '\t':outBuffer.append('\\'); outBuffer.append('t');
                          break;
                case '\n':outBuffer.append('\\'); outBuffer.append('n');
                          break;
                case '\r':outBuffer.append('\\'); outBuffer.append('r');
                          break;
                case '\f':outBuffer.append('\\'); outBuffer.append('f');
                          break;
                case '=': // Fall through
                case ':': // Fall through
                case '#': // Fall through
                case '!':
                    outBuffer.append('\\'); outBuffer.append(aChar);
                    break;
                default:
                    if (((aChar < 0x0020) || (aChar > 0x007e)) & escapeUnicode ) {
                        outBuffer.append('\\');
                        outBuffer.append('u');
                        outBuffer.append(toHex((aChar >> 12) & 0xF));
                        outBuffer.append(toHex((aChar >>  8) & 0xF));
                        outBuffer.append(toHex((aChar >>  4) & 0xF));
                        outBuffer.append(toHex( aChar        & 0xF));
                    } else {
                        outBuffer.append(aChar);
                    }
            }
        }
        return outBuffer.toString();
    }

    private static void writeComments(BufferedWriter bw, String comments) 
        throws IOException {
        bw.write("#");
        int len = comments.length();  
        int current = 0;
        int last = 0;
        char[] uu = new char[6];
        uu[0] = '\\';
        uu[1] = 'u';
        while (current < len) {
            char c = comments.charAt(current);
	    if (c > '\u00ff' || c == '\n' || c == '\r') {
	        if (last != current) 
                    bw.write(comments.substring(last, current));
                if (c > '\u00ff') {
                    uu[2] = toHex((c >> 12) & 0xf);
                    uu[3] = toHex((c >>  8) & 0xf);
                    uu[4] = toHex((c >>  4) & 0xf);
                    uu[5] = toHex( c        & 0xf);
                    bw.write(new String(uu));
                } else {
                    bw.newLine();
                    if (c == '\r' && 
			current != len - 1 && 
			comments.charAt(current + 1) == '\n') {
                        current++;
                    }
                    if (current == len - 1 ||
                        (comments.charAt(current + 1) != '#' &&
			comments.charAt(current + 1) != '!'))
                        bw.write("#");
                }
                last = current + 1;
	    } 
            current++;
	}
        if (last != current) 
            bw.write(comments.substring(last, current));
        bw.newLine();
    }
    /**
     * Convert a nibble to a hex character
     * @param	nibble	the nibble to convert.
     */
    private static char toHex(int nibble) {
	return hexDigit[(nibble & 0xF)];
    }

    /** A table of hex digits */
    private static final char[] hexDigit = {
	'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'
    };
}
