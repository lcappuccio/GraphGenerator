/**
 * @author leo
 * @date 20/04/2015 00:09
 */
package org.systemexception.graphgenerator.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.systemexception.graphgenerator.enums.ErrorCodes;
import org.systemexception.graphgenerator.exception.NodeException;

public class Node {

	private static final Logger logger = LogManager.getLogger(Node.class);
	private final String nodeId, nodeDescr;

	public Node(String nodeId, String nodeDescr) throws NodeException {
		if (nodeId == null || nodeDescr == null) {
			NodeException nodeException = new NodeException(ErrorCodes.NODE_NULL_VALUE.toString());
			logger.error(nodeException.getMessage(), nodeException);
			throw nodeException;
		}
		if (!isNamingConventionCompliant(nodeId)) {
			NodeException nodeException = new NodeException(ErrorCodes.NODE_INVALID_CHARS.toString());
			logger.error(nodeException.getMessage(), nodeException);
			throw nodeException;
		}
		this.nodeId = nodeId;
		this.nodeDescr = nodeDescr;
	}

	public String getNodeId() {
		return nodeId;
	}

	public String getNodeDescr() {
		return nodeDescr;
	}

	/**
	 * Test for strictly naming convention id (NodeLevel_ID)
	 *
	 * @param nodeId the node id
	 * @return the boolean for name validation
	 */
	private boolean isNamingConventionCompliant(String nodeId) {
		String pattern = "^[a-zA-Z0-9_]*$";
		return nodeId.matches(pattern);
	}
}
