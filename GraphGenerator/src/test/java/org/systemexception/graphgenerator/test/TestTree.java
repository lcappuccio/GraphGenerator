/**
 *
 * @author leo
 * @date 20/04/2015 20:32
 *
 */
package org.systemexception.graphgenerator.test;

import org.junit.Test;
import org.systemexception.graphgenerator.exception.EdgeException;
import org.systemexception.graphgenerator.exception.NodeException;
import org.systemexception.graphgenerator.model.Node;
import org.systemexception.graphgenerator.model.Tree;

public class TestTree {

	private Tree sut;

	@Test
	public void createSimpleTree() throws NodeException, EdgeException {
		sut = new Tree(3, 3);
		for (int i = 0; i < sut.getNodes().size(); i++) {
			System.out.println("Node in tree: " + sut.getNodes().get(i).getNodeId());
		}
		for (int i = 0; i < sut.getEdges().size(); i++) {
			System.out.println("Edge parent node: " + sut.getEdges().get(i).getParentNode().getNodeId());
			System.out.println("Edge child node: " + sut.getEdges().get(i).getChildNode().getNodeId());

		}
	}

}
