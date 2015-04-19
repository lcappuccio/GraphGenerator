/**
 *
 * @author leo
 * @date 20/04/2015 00:14
 *
 */
package org.systemexception.graphgenerator.exception;

public class NodeException extends Exception {

	private static final long serialVersionUID = 5463734201020235826L;

	/**
	 * Creates a new instance of <code>NodeException</code> without detail message.
	 */
	public NodeException() {
	}

	/**
	 * Constructs an instance of <code>NodeException</code> with the specified detail message.
	 *
	 * @param msg the detail message.
	 */
	public NodeException(String msg) {
		super(msg);
	}
}
