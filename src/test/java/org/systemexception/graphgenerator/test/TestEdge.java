/**
 * @author leo
 * @date 20/04/2015 00:28
 */
package org.systemexception.graphgenerator.test;

import org.junit.Test;
import org.systemexception.graphgenerator.exception.EdgeException;
import org.systemexception.graphgenerator.exception.NodeException;
import org.systemexception.graphgenerator.model.Edge;
import org.systemexception.graphgenerator.model.Node;

public class TestEdge {

    private Edge sut;

    @Test(expected = org.systemexception.graphgenerator.exception.EdgeException.class)
    public void throwExceptionOnNullParentNode() throws NodeException, EdgeException {
        Node childNode = new Node("ABC", "ABC");
        sut = new Edge(null, childNode);
    }

    @Test(expected = org.systemexception.graphgenerator.exception.EdgeException.class)
    public void throwExceptionOnNullChildNode() throws NodeException, EdgeException {
        Node parentNode = new Node("ABC", "ABC");
        sut = new Edge(parentNode, null);
    }
}
