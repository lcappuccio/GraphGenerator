/**
 * @author leo
 * @date 20/04/2015 19:28
 */
package org.systemexception.graphgenerator.model;

import org.systemexception.graphgenerator.enums.ErrorCodes;
import org.systemexception.graphgenerator.enums.Labels;
import org.systemexception.graphgenerator.exception.EdgeException;
import org.systemexception.graphgenerator.exception.NodeException;
import org.systemexception.graphgenerator.exception.TreeException;

import java.util.ArrayList;
import java.util.HashMap;

public class KaryTree extends Tree {

	private final int treeLevels, childPerNode;

	/**
	 * @param levels       the total levels of the tree
	 * @param childPerNode the amount of childs per node
	 */
	public KaryTree(int levels, int childPerNode) throws TreeException, NodeException, EdgeException {
		super();
		if (childPerNode > 10) {
			TreeException treeException = new TreeException(ErrorCodes.TREE_10_CHILDS_PER_NODE.toString());
			logger.error(treeException.getMessage(), treeException);
			throw treeException;
		}
		treeNodes = new HashMap<>();
		treeEdges = new ArrayList();
		treeLevelsString = new ArrayList();
		this.treeLevels = levels;
		this.childPerNode = childPerNode;
		Node rootNode = null;
		rootNode = new Node(Labels.ROOT_NODE_ID.toString(), Labels.ROOT_NODE_NAME.toString());
		treeNodes.put(Labels.ROOT_NODE_ID.toString(), rootNode);
		addTreeLevelForCsvOutput(Labels.ROOT_NODE_ID.toString(), Labels.ROOT_NODE_NAME.toString(),
				Labels.ROOT_PARENT_NODE.toString(), Labels.ROOT_LEVEL_NAME.toString());
		logger.info("Starting K-ary Tree: " + treeLevels + " levels, " + childPerNode + " children per node");
		makeTree(rootNode, 0);
	}

	/**
	 * Generate the tree recursively
	 *
	 * @param parentNode   the parent node, initially a root node, recursively the parent node
	 * @param currentLevel the current level of the tree
	 * @throws NodeException
	 * @throws EdgeException
	 * @throws TreeException
	 */
	private void makeTree(Node parentNode, int currentLevel) throws NodeException, EdgeException, TreeException {
		if (currentLevel == treeLevels) {
			return;
		}
		for (int i = 0; i < childPerNode; i++) {
			String childNodeId = Labels.NODE_NAME.toString() + parentNode.getNodeId().replace(Labels.NODE_NAME
					.toString(), "") + String.valueOf(i);
			String childNodeDescr = childNodeId + Labels.NAME_SEPARATOR + Labels.LEVEL_NAME.toString() + String
					.valueOf(currentLevel);
			Node childNode = new Node(childNodeId, childNodeDescr);
			addNode(childNode, parentNode);
			logger.info("Added node " + childNodeDescr + " with parent " + parentNode.getNodeId());
			addTreeLevelForCsvOutput(childNodeId, childNodeDescr, parentNode.getNodeId(), Labels.LEVEL_NAME.toString
					() + String.valueOf(currentLevel));
			makeTree(childNode, currentLevel + 1);
		}
	}
}
