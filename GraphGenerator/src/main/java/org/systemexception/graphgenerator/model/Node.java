/**
 *
 * @author leo
 * @date 20/04/2015 00:09
 *
 */
package org.systemexception.graphgenerator.model;

import org.systemexception.graphgenerator.exception.NodeException;

public class Node {

	private final String nodeId, nodeDescr;

	public Node(String nodeId, String nodeDescr) throws NodeException {
		if (nodeId == null || nodeDescr == null) {
			throw new NodeException("Null value for nodes");
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
}
