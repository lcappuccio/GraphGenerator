/**
 * @author leo
 * @date 20/04/2015 00:09
 */
package org.systemexception.graphgenerator.model;

import org.systemexception.graphgenerator.exception.EdgeException;
import org.systemexception.logger.api.Logger;
import org.systemexception.logger.impl.LoggerImpl;

public class Edge {

	private static final Logger logger = LoggerImpl.getFor(Edge.class);
	private final Node parentNode, childNode;

    public Edge(Node parentNode, Node childNode) throws EdgeException {
        if (parentNode == null || childNode == null) {
	        EdgeException edgeException = new EdgeException("Null nodes not allowed");
	        logger.error(edgeException.getMessage(), edgeException);
            throw edgeException;
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
