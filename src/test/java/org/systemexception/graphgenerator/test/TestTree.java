package org.systemexception.graphgenerator.test;

import org.junit.Test;
import org.systemexception.graphgenerator.enums.Labels;
import org.systemexception.graphgenerator.exception.EdgeException;
import org.systemexception.graphgenerator.exception.NodeException;
import org.systemexception.graphgenerator.exception.TreeException;
import org.systemexception.graphgenerator.model.Node;
import org.systemexception.graphgenerator.model.Tree;

/**
 * @author leo
 * @date 16/07/15 22:51
 */
public class TestTree {

	private Tree sut;

	@Test
	public void treeHasRootNode() {
		sut = new Tree();
		assert (sut.nodeExists(Labels.ROOT_NODE_ID.toString()));
	}

	@Test(expected = TreeException.class)
	public void exceptionOnNonExistingParentNode() throws NodeException, EdgeException, TreeException {
		sut = new Tree();
		Node testNode = new Node("999", "testNode");
		Node nonExistingNode = new Node("XXX", "nonExistingNode");
		sut.addNode(testNode, nonExistingNode);
	}
}