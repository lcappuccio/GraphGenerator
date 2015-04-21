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
import org.systemexception.graphgenerator.exception.TreeException;
import org.systemexception.graphgenerator.model.Tree;

public class TestTree {

	private Tree sut;
	double totalNodes;

	@Test
	public void createSimpleTree() throws NodeException, EdgeException, TreeException {
		generateTree(1,3);
		assertTrue(totalNodes == sut.getNodes().size());
	}

	@Test
	public void createTwoLevelTree() throws NodeException, EdgeException, TreeException {
		generateTree(2,2);
		assertTrue(totalNodes == sut.getNodes().size());
	}

	@Test
	public void createModerateTree() throws NodeException, EdgeException, TreeException {
		generateTree(5,10);
		assertTrue(totalNodes == sut.getNodes().size());
	}

	@Test
	public void createBigTree() throws NodeException, EdgeException, TreeException {
		generateTree(8,7);
		assertTrue(totalNodes == sut.getNodes().size());
	}

	/**
	 * 
	 * @param height
	 * @param childPerNode
	 * @throws NodeException
	 * @throws EdgeException
	 * @throws TreeException 
	 */
	private void generateTree(int height, int childPerNode) throws NodeException, EdgeException, TreeException {
		int treeHeight = height;
		int treeChildPerNode = childPerNode;
		totalNodes = (Math.pow(treeChildPerNode, treeHeight + 1) - 1) / (treeChildPerNode - 1);
		sut = new Tree(treeHeight, treeChildPerNode);
	}
}
