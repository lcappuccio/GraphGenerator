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

	private final ArrayList<Node> nodes;
	private final ArrayList<Edge> edges;
	private final int levels, childPerNode;

	public Tree(int levels, int childPerNode) throws NodeException, EdgeException {
		nodes = new ArrayList();
		edges = new ArrayList();
		this.levels = levels;
		this.childPerNode = childPerNode;
		Node rootNode = new Node("root", "RootNode");
		nodes.add(rootNode);
		makeTree(rootNode);
	}

	/**
	 * Generate the tree recursively
	 *
	 * @param rootNode
	 * @param nodes
	 */
	private void makeTree(Node node) throws NodeException, EdgeException {
		for (Node tmpNode : nodes) {
			for (int i = 0; i < childPerNode; i++) {
				Node childNode = new Node("Node" + String.valueOf(i), "Level" + String.valueOf(i));
				System.out.println("Adding node " + childNode.getNodeId());
				nodes.add(childNode);
				Edge edge = new Edge(tmpNode, childNode);
				edges.add(edge);
			}
		}
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public List<Edge> getEdges() {
		return edges;
	}

}
