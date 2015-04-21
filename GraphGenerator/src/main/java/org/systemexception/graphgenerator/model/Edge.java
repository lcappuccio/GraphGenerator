/**
 *
 * @author leo
 * @date 20/04/2015 00:09
 *
 */
package org.systemexception.graphgenerator.model;

import org.systemexception.graphgenerator.exception.EdgeException;

public class Edge {

	private final Node parentNode, childNode;

	public Edge(Node parentNode, Node childNode) throws EdgeException {
		if (parentNode == null || childNode == null) {
			throw new EdgeException("Null nodes not allowed");
		}
		this.parentNode = parentNode;
		this.childNode = childNode;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public Node getChildNode() {
		return childNode;
	}

}
