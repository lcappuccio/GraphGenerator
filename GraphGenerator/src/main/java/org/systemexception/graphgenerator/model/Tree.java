/**
 *
 * @author leo
 * @date 20/04/2015 19:28
 *
 */
package org.systemexception.graphgenerator.model;

import java.util.ArrayList;
import java.util.List;
import org.systemexception.graphgenerator.exception.EdgeException;
import org.systemexception.graphgenerator.exception.NodeException;

public class Tree {

	private List<Node> nodes;
	private List<Edge> edges;
	private final int levels, childPerNode;

	public Tree(int levels, int childPerNode) throws NodeException, EdgeException {
		nodes = new ArrayList();
		edges = new ArrayList();
		this.levels = levels;
		this.childPerNode = childPerNode;
		Node root = new Node("root", "RootNode");
		nodes.add(root);
		makeTree(root, 0);
	}

	/**
	 * Generate the tree recursively
	 *
	 * @param rootNode
	 * @param nodes
	 */
	private void makeTree(Node node, int counter) throws NodeException, EdgeException {
		for (int i = counter; counter < childPerNode; ++i) {
			Node childNode = new Node("Node" + String.valueOf(i), "Level" + String.valueOf(i));
			System.out.println("Adding node " + childNode.getNodeId());
			nodes.add(childNode);
			Edge edge = new Edge(node, childNode);
			edges.add(edge);
			makeTree(childNode, ++counter);
		}
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public List<Edge> getEdges() {
		return edges;
	}

}
