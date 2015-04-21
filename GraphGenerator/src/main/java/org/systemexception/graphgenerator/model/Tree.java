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
import org.systemexception.graphgenerator.exception.TreeException;

public class Tree {

	private final ArrayList<Node> treeNodes;
	private final ArrayList<Edge> treeEdges;
	private final int treeLevels, childPerNode;

	/**
	 * 
	 * @param levels
	 * @param childPerNode
	 * @throws NodeException
	 * @throws EdgeException
	 * @throws TreeException 
	 */
	public Tree(int levels, int childPerNode) throws NodeException, EdgeException, TreeException {
		treeNodes = new ArrayList();
		treeEdges = new ArrayList();
		this.treeLevels = levels;
		this.childPerNode = childPerNode;
		Node rootNode = new Node("0", "RootNode");
		treeNodes.add(rootNode);
		makeTree(rootNode, 0);
	}

	/**
	 * Generate the tree recursively
	 *
	 * @param rootNode
	 * @param nodes
	 */
	private void makeTree(Node node, int currentLevel) throws NodeException, EdgeException, TreeException {
		if (currentLevel == treeLevels) {
			return;
		}
		for (int i = 0; i < childPerNode; i++) {
			Node childNode = new Node("Node_" + node.getNodeId().replace("Node", "").replace("_", "") + String.valueOf(i),
					"Node" + String.valueOf(currentLevel) + "_" + String.valueOf(i) + "Level" + String.valueOf(currentLevel));
			Edge edge = new Edge(node, childNode);
			if (nodeExists(childNode.getNodeId())) {
				throw new TreeException("NodeId "+ childNode.getNodeId() + " already exists");
			}
			treeNodes.add(childNode);
			treeEdges.add(edge);
			makeTree(childNode, currentLevel + 1);
		}
	}
	
	/**
	 * Verify if nodeId already exists in tree
	 *
	 * @param nodeId
	 * @return
	 */
	private boolean nodeExists(String nodeId) {
		for (Node treeNode : treeNodes) {
			if (treeNode.getNodeId().equals(nodeId)) {
				return true;
			}
		}
		return false;
	}

	public List<Node> getNodes() {
		return treeNodes;
	}

	public List<Edge> getEdges() {
		return treeEdges;
	}
}
