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

	private final ArrayList<Node> treeNodes;
	private final ArrayList<Edge> treeEdges;
	private final int treeLevels, childPerNode;

	public Tree(int levels, int childPerNode) throws NodeException, EdgeException {
		treeNodes = new ArrayList();
		treeEdges = new ArrayList();
		this.treeLevels = levels;
		this.childPerNode = childPerNode;
		Node rootNode = new Node("root", "RootNode");
		treeNodes.add(rootNode);
		makeTree(rootNode, 0);
	}

	/**
	 * Generate the tree recursively
	 *
	 * @param rootNode
	 * @param nodes
	 */
	private void makeTree(Node node, int currentLevel) throws NodeException, EdgeException {
		if (currentLevel == treeLevels) {
			return;
		}
		for (int i = 0; i < childPerNode; i++) {
			Node childNode = new Node("Node" + String.valueOf(currentLevel) + "_" + String.valueOf(i), "Level" + String.valueOf(i));
			Edge edge = new Edge(node, childNode);
			treeEdges.add(edge);
			makeTree(childNode, currentLevel + 1);
		}
	}

	public List<Node> getNodes() {
		return treeNodes;
	}

	public List<Edge> getEdges() {
		return treeEdges;
	}

}
