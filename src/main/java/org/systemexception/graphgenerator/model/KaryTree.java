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
import org.systemexception.logger.api.Logger;
import org.systemexception.logger.impl.LoggerImpl;

import java.util.ArrayList;

public class KaryTree extends Tree {

	private static final Logger logger = LoggerImpl.getFor(KaryTree.class);
	private final int treeLevels, childPerNode;

	/**
	 * @param levels       the total levels of the tree
	 * @param childPerNode the amount of childs per node
	 * @throws NodeException
	 * @throws EdgeException
	 * @throws TreeException
	 */
	public KaryTree(int levels, int childPerNode) throws NodeException, EdgeException, TreeException {
		if (childPerNode > 10) {
			TreeException treeException = new TreeException(ErrorCodes.TREE_10_CHILDS_PER_NODE.toString());
			logger.error(treeException.getMessage(), treeException);
			throw treeException;
		}
		treeNodes = new ArrayList();
		treeEdges = new ArrayList();
		treeLevelsString = new ArrayList();
		this.treeLevels = levels;
		this.childPerNode = childPerNode;
		Node rootNode = new Node(Labels.ROOT_NODE_ID.toString(), Labels.ROOT_NODE_NAME.toString());
		treeNodes.add(rootNode);
		addTreeLevelForCsvOutput(Labels.ROOT_NODE_ID.toString(), Labels.ROOT_NODE_NAME.toString(), Labels.ROOT_PARENT_NODE
				.toString(), Labels
				.ROOT_LEVEL_NAME.toString());
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
			Edge edge = new Edge(parentNode, childNode);
			if (nodeExists(childNodeId)) {
				TreeException treeException = new TreeException(ErrorCodes.TREE_NODE_ALREADY_EXISTS.toString() +
						childNodeId);
				logger.error(treeException.getMessage(), treeException);
				throw treeException;
			}
			treeNodes.add(childNode);
			treeEdges.add(edge);
			logger.info("Added node " + childNodeDescr + " with parent " + parentNode.getNodeId());
			addTreeLevelForCsvOutput(childNodeId, childNodeDescr, parentNode.getNodeId(), Labels.LEVEL_NAME.toString
					() + String.valueOf(currentLevel));
			makeTree(childNode, currentLevel + 1);
		}
	}
}
