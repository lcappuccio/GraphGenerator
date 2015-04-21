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
	private ArrayList<String> treeLevelString;
	private final ArrayList<ArrayList<String>> treeLevelsString;
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
		treeLevelsString = new ArrayList();
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
	private void makeTree(Node parentNode, int currentLevel) throws NodeException, EdgeException, TreeException {
		if (currentLevel == treeLevels) {
			return;
		}
		for (int i = 0; i < childPerNode; i++) {
			String childNodeId = "Node_" + parentNode.getNodeId().replace("Node", "").replace("_", "") + String.valueOf(i);
			String childNodeDescr = "Node" + String.valueOf(currentLevel) + "_" + String.valueOf(i) + "Level" + String.valueOf(currentLevel);
			Node childNode = new Node(childNodeId,childNodeDescr);
			Edge edge = new Edge(parentNode, childNode);
			if (nodeExists(childNode.getNodeId())) {
				throw new TreeException("NodeId " + childNode.getNodeId() + " already exists");
			}
			treeNodes.add(childNode);
			treeEdges.add(edge);
			treeLevelString = new ArrayList();
			treeLevelString.add(childNodeId);
			treeLevelString.add(parentNode.getNodeId());
			treeLevelString.add(childNodeDescr);
			treeLevelString.add("Level" + String.valueOf(currentLevel));
			treeLevelsString.add(treeLevelString);
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

	public ArrayList<ArrayList<String>> getTreeLevelsString() {
		return treeLevelsString;
	}
}
