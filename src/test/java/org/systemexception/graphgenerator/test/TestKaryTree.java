/**
 * @author leo
 * @date 20/04/2015 20:32
 */
package org.systemexception.graphgenerator.test;

import org.junit.Test;
import org.systemexception.graphgenerator.enums.Labels;
import org.systemexception.graphgenerator.exception.EdgeException;
import org.systemexception.graphgenerator.exception.NodeException;
import org.systemexception.graphgenerator.exception.TreeException;
import org.systemexception.graphgenerator.model.tree.KaryTree;
import org.systemexception.graphgenerator.model.Node;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("JavaDoc")
public class TestKaryTree {

	private KaryTree sut;
	private double totalNodes;

	@Test
	public void createSimpleTree() throws NodeException, EdgeException, TreeException {
		generateTree(1, 3);
		assertTrue(totalNodes == sut.getNodes().size());
	}

	@Test
	public void createTwoLevelTree() throws NodeException, EdgeException, TreeException {
		generateTree(2, 2);
		assertTrue(totalNodes == sut.getNodes().size());
	}

	@Test
	public void createFourLevelTree() throws NodeException, EdgeException, TreeException {
		generateTree(2, 4);
		assertTrue(totalNodes == sut.getNodes().size());
	}

	@Test
	public void checkIfRootHasChilds() throws NodeException, EdgeException, TreeException {
		generateTree(1, 3);
		ArrayList<Node> childNodes = sut.getChildNodes(sut.getNodes().get(0));
		assertFalse(childNodes.isEmpty());
	}

	@Test
	public void dontRemoveNodeWithChilds() throws TreeException, NodeException, EdgeException {
		generateTree(1, 3);
		Node rootNode = sut.getNodes().get(0);
		sut.removeNode(rootNode);
		assertTrue(sut.getNodes().contains(rootNode));
	}

	@Test
	public void removeLeaveNode() throws NodeException, TreeException, EdgeException {
		generateTree(2, 2);
		List<Node> nodeList = sut.getNodes();
		for (Node node : nodeList) {
			if (sut.getChildNodes(node).isEmpty()) {
				sut.removeNode(node);
			}
		}
		assert (1 == sut.getNodes().size());
		assert (sut.getNodeById(Labels.ROOT_NODE_ID.toString()).getNodeDescr()
				.equals(Labels.ROOT_NODE_NAME.toString()));
	}

	@Test
	public void emptyTree() throws TreeException, NodeException, EdgeException {
		generateTree(3, 3);
		while (sut.getNodes().size() > 0) {
			for (int i = 0; i < sut.getNodes().size(); i++) {
				Node nodeToRemove = sut.getNodes().get(i);
				sut.removeNode(nodeToRemove);
			}
		}
		assertTrue(sut.getNodes().size() == 0);
		assertTrue(sut.getEdges().size() == 0);
	}

	@Test
	public void emptyTreeInternalMethod() throws TreeException, NodeException, EdgeException {
		generateTree(3, 3);
		sut.emptyTree();
		assertTrue(sut.getNodes().size() == 0);
		assertTrue(sut.getEdges().size() == 0);
	}

	@Test
	public void getParentNode() throws TreeException, NodeException, EdgeException {
		generateTree(1, 1);
		Node childNode = sut.getNodes().get(sut.getNodes().size() - 1);
		Node parentNode = sut.getNodes().get(0);
		assertEquals(sut.getParentNode(childNode), parentNode);
		assertEquals(sut.getParentNode(parentNode), null);
	}

	@Test(expected = TreeException.class)
	public void refuseMoreThan10ChildPerNode() throws TreeException, NodeException, EdgeException {
		generateTree(1, 11);	}

	/**
	 * Generates a tree
	 *
	 * @param height       the height of the tree level we're on
	 * @param childPerNode how many childs per node
	 */
	private void generateTree(int height, int childPerNode) throws TreeException, NodeException, EdgeException {
		totalNodes = (Math.pow(childPerNode, height + 1) - 1) / (childPerNode - 1);
		sut = new KaryTree(height, childPerNode);
	}
}
