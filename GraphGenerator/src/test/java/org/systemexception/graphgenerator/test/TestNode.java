/**
 *
 * @author leo
 * @date 20/04/2015 00:01
 *
 */
package org.systemexception.graphgenerator.test;

import org.junit.Test;
import org.systemexception.graphgenerator.exception.NodeException;
import org.systemexception.graphgenerator.model.Node;

public class TestNode {

	private Node sut;

	@Test(expected = org.systemexception.graphgenerator.exception.NodeException.class)
	public void throwExceptionOnNullId() throws NodeException {
		String nodeId = null;
		String nodeDescr = "123";
		sut = new Node(nodeId, nodeDescr);
	}

}
