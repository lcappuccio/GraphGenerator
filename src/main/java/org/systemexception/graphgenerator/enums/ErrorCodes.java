package org.systemexception.graphgenerator.enums;

/**
 * @author leo
 * @date 13/06/15 11:42
 */
public enum ErrorCodes {

	NODE_NULL_VALUE("Null values for nodes"),
	NODE_INVALID_CHARS("Invalid chars on nodeId"),
	EDGE_NULL_NODE("Null nodes not allowed in edge"),
	TREE_10_CHILDS_PER_NODE("Trees with more than 10 childs per node are not " +
			"supported"),
	TREE_NODE_ALREADY_EXISTS("NodeId already exists: "),
	NODE_NOT_EXISTS("Node does not exist");

	private final String errorCode;

	ErrorCodes(String errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return errorCode;
	}
}
