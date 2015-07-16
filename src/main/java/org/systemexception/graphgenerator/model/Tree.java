package org.systemexception.graphgenerator.model;

import org.systemexception.logger.api.Logger;
import org.systemexception.logger.impl.LoggerImpl;

import java.util.ArrayList;

/**
 * @author leo
 * @date 16/07/15 22:46
 */
public class Tree {

	private static final Logger logger = LoggerImpl.getFor(Tree.class);
	private ArrayList<Node> treeNodes = new ArrayList<>();
	private ArrayList<Edge> treeEdges = new ArrayList<>();

	public Tree() {

	}
}
