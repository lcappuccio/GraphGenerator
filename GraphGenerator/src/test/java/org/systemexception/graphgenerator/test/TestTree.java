/**
 *
 * @author leo
 * @date 20/04/2015 20:32
 *
 */
package org.systemexception.graphgenerator.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.systemexception.graphgenerator.exception.EdgeException;
import org.systemexception.graphgenerator.exception.NodeException;
import org.systemexception.graphgenerator.model.Tree;

public class TestTree {

	private Tree sut;

	@Test
	public void createSimpleTree() throws NodeException, EdgeException {
		int height = 1;
		int childPerNode = 3;
		double totalNodes = (Math.pow(childPerNode, height + 1) - 1) / (childPerNode - 1);
		sut = new Tree(height, childPerNode);
		assertTrue(totalNodes == sut.getNodes().size());
	}

	@Test
	public void createModerateTree() throws NodeException, EdgeException {
		int height = 5;
		int childPerNode = 10;
		double totalNodes = (Math.pow(childPerNode, height + 1) - 1)/(childPerNode - 1);
		sut = new Tree(height, childPerNode);
		assertTrue(totalNodes == sut.getNodes().size());
	}

	@Test
	public void createBigTree() throws NodeException, EdgeException {
		int height = 8;
		int childPerNode = 7;
		double totalNodes = (Math.pow(childPerNode, height + 1) - 1) / (childPerNode - 1);
		sut = new Tree(height, childPerNode);
		assertTrue(totalNodes == sut.getNodes().size());
	}
}
