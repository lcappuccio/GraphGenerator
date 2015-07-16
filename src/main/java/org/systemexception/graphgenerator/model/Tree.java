package org.systemexception.graphgenerator.model;

import org.systemexception.graphgenerator.enums.Labels;
import org.systemexception.graphgenerator.exception.NodeException;
import org.systemexception.logger.api.Logger;
import org.systemexception.logger.impl.LoggerImpl;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author leo
 * @date 16/07/15 22:46
 */
public class Tree {

	private static final Logger logger = LoggerImpl.getFor(Tree.class);
	private ArrayList<Node> treeNodes = new ArrayList<>();
	private ArrayList<Edge> treeEdges = new ArrayList<>();
	private HashMap<String, ArrayList<Node>> levelNodes = new HashMap<>();

	public Tree() throws NodeException {
		Node rootNode = new Node("1", Labels.ROOT_NODE_NAME.toString());
		treeNodes.add(rootNode);
	}
}
