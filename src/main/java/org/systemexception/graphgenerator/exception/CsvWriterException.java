/**
 * $Id$
 *
 * @author lcappuccio
 * @date 21/04/2015 14:53
 *
 */
package org.systemexception.graphgenerator.exception;

public class CsvWriterException extends Exception {
	
	private static final long serialVersionUID = 6003776784196930310L;

    /**
     * Constructs an instance of <code>CsvWriterException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public CsvWriterException(String msg) {
        super(msg);
    }
}
