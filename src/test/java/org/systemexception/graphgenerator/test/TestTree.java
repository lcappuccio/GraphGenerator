package org.systemexception.graphgenerator.test;

import org.junit.Test;
import org.systemexception.graphgenerator.enums.Labels;
import org.systemexception.graphgenerator.exception.EdgeException;
import org.systemexception.graphgenerator.exception.NodeException;
import org.systemexception.graphgenerator.exception.TreeException;
import org.systemexception.graphgenerator.model.Node;
import org.systemexception.graphgenerator.model.tree.Tree;

/**
 * @author leo
 * @date 16/07/15 22:51
 */
public class TestTree {

	private final String testNodeDescr = "testNode";
	private Tree sut;

	@Test
	public void treeHasRootNode() throws NodeException {
		sut = new Tree();
		assert (sut.nodeExists(Labels.ROOT_NODE_ID.toString()));
	}

	@Test(expected = TreeException.class)
	public void exceptionOnNonExistingParentNode() throws NodeException, EdgeException, TreeException {
		sut = new Tree();
		Node testNode = new Node("999", testNodeDescr);
		Node nonExistingNode = new Node("XXX", "nonExistingNode");
		sut.addNode(testNode, nonExistingNode);
	}

	@Test(expected = TreeException.class)
	public void exceptionOnAddExistingNode() throws EdgeException, TreeException, NodeException {
		sut = new Tree();
		Node testNode = new Node("2", testNodeDescr);
		sut.addNode(testNode, sut.getNodeById("1"));
		sut.addNode(testNode, sut.getNodeById("1"));
	}
}