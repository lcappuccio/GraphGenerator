/**
 * @author leo
 * @date 20/04/2015 00:01
 */
package org.systemexception.graphgenerator.test;

import org.junit.Test;
import org.systemexception.graphgenerator.exception.NodeException;
import org.systemexception.graphgenerator.model.Node;

public class TestNode {

	private final String nodeIdAndDescr = "123";
	private Node sut;

	@Test(expected = NodeException.class)
	public void throwExceptionOnNullId() throws NodeException {
		String nodeDescr = nodeIdAndDescr;
		sut = new Node(null, nodeDescr);
	}

	@Test(expected = NodeException.class)
	public void throwExceptionOnNullDescr() throws NodeException {
		String nodeId = nodeIdAndDescr;
		sut = new Node(nodeId, null);
	}

	@Test(expected = NodeException.class)
	public void throwExceptionForNonAlphaNumericId() throws NodeException {
		String nodeId = "1-2-3";
		String nodeDescr = "A-B-C";
		sut = new Node(nodeId, nodeDescr);
	}

}
