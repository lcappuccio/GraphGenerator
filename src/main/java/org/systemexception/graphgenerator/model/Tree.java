package org.systemexception.graphgenerator.model;

import org.systemexception.graphgenerator.enums.ErrorCodes;
import org.systemexception.graphgenerator.enums.Labels;
import org.systemexception.graphgenerator.exception.EdgeException;
import org.systemexception.graphgenerator.exception.NodeException;
import org.systemexception.graphgenerator.exception.TreeException;
import org.systemexception.logger.api.Logger;
import org.systemexception.logger.impl.LoggerImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author leo
 * @date 16/07/15 22:46
 */
public class Tree {

	private static final Logger logger = LoggerImpl.getFor(Tree.class);
	protected ArrayList<Node> treeNodes = new ArrayList<>();
	protected ArrayList<Edge> treeEdges = new ArrayList<>();
	private HashMap<String, ArrayList<Node>> levelNodes = new HashMap<>();
	protected ArrayList<ArrayList<String>> treeLevelsString;

	public Tree() throws NodeException {
		Node rootNode = new Node(Labels.ROOT_NODE_ID.toString(), Labels.ROOT_NODE_NAME.toString());
		treeNodes.add(rootNode);
	}

	public void addNode(Node node, Node parentNode) throws EdgeException, TreeException {
		if (nodeExists(node.getNodeId())) {
			TreeException treeException = new TreeException(ErrorCodes.TREE_NODE_ALREADY_EXISTS.toString() +
					node);
			logger.error(treeException.getMessage(), treeException);
			throw treeException;
		}
		Edge edge = new Edge(parentNode, node);
		treeNodes.add(node);
		treeEdges.add(edge);
	}
	/**
	 * Removes a node from the tree
	 *
	 * @param node the node to remove
	 */
	public void removeNode(Node node) {
		ArrayList<Node> childNodes = getChildNodes(node);
		if (getChildNodes(node).size() > 0) {
			logger.info("Can't remove node " + node.getNodeId() + " because it has childs");
			for (Node childNode : childNodes) {
				logger.info("Found child node: " + childNode.getNodeId() + " for node " + node.getNodeId());
			}
		} else {
			if (treeNodes.contains(node)) {
				logger.info("Remove node: " + node.getNodeId());
				treeNodes.remove(node);
			}
			removeIncomingEdgeTo(node);
		}
	}

	/**
	 * Removes an incoming edge to this node
	 *
	 * @param node the target node
	 */
	protected void removeIncomingEdgeTo(Node node) {
		List<Edge> edgesToRemove = new ArrayList<>();
		for (Edge edge : treeEdges) {
			if (edge.getChildNode().equals(node)) {
				edgesToRemove.add(edge);
			}
		}
		for (Edge edgeToRemove : edgesToRemove) {
			treeEdges.remove(edgeToRemove);
		}
	}

	/**
	 * Returns the child nodes of a given node
	 *
	 * @param node the node to check
	 * @return the children node list
	 */
	public ArrayList<Node> getChildNodes(Node node) {
		ArrayList<Node> childNodes = new ArrayList<>();
		for (Edge edge : treeEdges) {
			if (edge.getParentNode().equals(node)) {
				childNodes.add(edge.getChildNode());
			}
		}
		return childNodes;
	}

	/**
	 * Returns the parent node of a given node
	 *
	 * @param node the node to check
	 * @return the parent node
	 */
	public Node getParentNode(Node node) {
		for (Edge edge : treeEdges) {
			if (edge.getChildNode().equals(node)) {
				return edge.getParentNode();
			}
		}
		return null;
	}

	/**
	 * Verify if nodeId already exists in tree
	 *
	 * @param nodeId the node id to check
	 * @return the boolean verification value
	 */
	public boolean nodeExists(String nodeId) {
		for (Node treeNode : treeNodes) {
			if (treeNode.getNodeId().equals(nodeId)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Empties a tree
	 */
	public void emptyTree() {
		logger.info("Emptying tree");
		treeEdges.clear();
		treeNodes.clear();
	}

	/**
	 * Creates a String representation of a tree level
	 *
	 * @param nodeId       the node id
	 * @param nodeDescr    the node description
	 * @param parentNodeId the parent node id
	 * @param levelDescr   the level description
	 */
	protected void addTreeLevelForCsvOutput(String nodeId, String nodeDescr, String parentNodeId, String
			levelDescr) {
		ArrayList<String> treeLevelString = new ArrayList();
		treeLevelString.add(parentNodeId);
		treeLevelString.add(nodeId);
		treeLevelString.add(nodeDescr);
		treeLevelString.add(levelDescr);
		treeLevelsString.add(treeLevelString);
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
