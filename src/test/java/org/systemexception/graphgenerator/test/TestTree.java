package org.systemexception.graphgenerator.test;

import org.junit.Test;
import org.systemexception.graphgenerator.enums.Labels;
import org.systemexception.graphgenerator.exception.NodeException;
import org.systemexception.graphgenerator.model.Tree;

/**
 * @author leo
 * @date 16/07/15 22:51
 */
public class TestTree {

	private Tree sut;

	@Test
	public void treeHasRootNode() throws NodeException {
		sut = new Tree();
		assert(sut.nodeExists(Labels.ROOT_NODE_ID.toString()));
	}

}