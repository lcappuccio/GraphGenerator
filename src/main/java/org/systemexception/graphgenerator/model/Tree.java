/**
 * @author leo
 * @date 20/04/2015 19:28
 */
package org.systemexception.graphgenerator.model;

import org.systemexception.graphgenerator.enums.Labels;
import org.systemexception.graphgenerator.exception.EdgeException;
import org.systemexception.graphgenerator.exception.NodeException;
import org.systemexception.graphgenerator.exception.TreeException;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    private final ArrayList<Node> treeNodes;
    private final ArrayList<Edge> treeEdges;
    private final ArrayList<ArrayList<String>> treeLevelsString;
    private final int treeLevels, childPerNode;

    /**
     * @param levels       the total levels of the tree
     * @param childPerNode the amount of childs per node
     * @throws NodeException
     * @throws EdgeException
     * @throws TreeException
     */
    public Tree(int levels, int childPerNode) throws NodeException, EdgeException, TreeException {
        treeNodes = new ArrayList();
        treeEdges = new ArrayList();
        treeLevelsString = new ArrayList();
        this.treeLevels = levels;
        this.childPerNode = childPerNode;
        Node rootNode = new Node("1", "RootNode");
        treeNodes.add(rootNode);
        addTreeLevelForCsvOutput("1", "RootNode", "0", "RootLevel");
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
            String childNodeDescr = childNodeId + Labels.LEVEL_NAME.toString() + String.valueOf(currentLevel);
            Node childNode = new Node(childNodeId, childNodeDescr);
            Edge edge = new Edge(parentNode, childNode);
            if (nodeExists(childNode.getNodeId())) {
                throw new TreeException("NodeId " + childNode.getNodeId() + " already exists");
            }
            treeNodes.add(childNode);
            treeEdges.add(edge);
            addTreeLevelForCsvOutput(childNodeId, childNodeDescr, parentNode.getNodeId(), Labels.LEVEL_NAME.toString
                    ().replace("_", "") + String.valueOf(currentLevel));
            makeTree(childNode, currentLevel + 1);
        }
    }

    /**
     * Creates a String representation of a tree level
     *
     * @param childNodeId
     * @param childNodeDescr
     * @param nodeId
     * @param e
     */
    private void addTreeLevelForCsvOutput(String childNodeId, String childNodeDescr, String nodeId, String e) {
        ArrayList<String> treeLevelString = new ArrayList();
        treeLevelString.add(childNodeId);
        treeLevelString.add(nodeId);
        treeLevelString.add(childNodeDescr);
        treeLevelString.add(e);
        treeLevelsString.add(treeLevelString);
    }

    /**
     * Removes a node from the tree
     *
     * @param node the node to remove
     * @throws TreeException
     */
    public void removeNode(Node node) throws TreeException {
        ArrayList<Node> childNodes = getChildNodes(node);
        if (getChildNodes(node).size() > 0) {
            for (Node childNode : childNodes) {
                System.out.println("Found child node: " + childNode.getNodeId() + " for node " + node.getNodeId());
            }
        } else {
            if (treeNodes.contains(node)) {
                System.out.println("Remove node: " + node.getNodeId());
                treeNodes.remove(node);
            }
            removeIncomingEdgeTo(node);
        }
    }

    /**
     * Removes an incoming edge to this node
     *
     * @param node
     */
    private void removeIncomingEdgeTo(Node node) {
        List<Edge> edgesToRemove = new ArrayList<>();
        for (Edge edge : treeEdges) {
            if (edge.getChildNode().equals(node)) {
                edgesToRemove.add(edge);
            }
        }
        for (Edge edgeToRemove : edgesToRemove) {
            treeEdges.remove(edgeToRemove);
        }
    }

    /**
     * Returns the child nodes of a given node
     *
     * @param node the node to check
     * @return
     */
    public ArrayList<Node> getChildNodes(Node node) {
        ArrayList<Node> childNodes = new ArrayList<>();
        for (Edge edge : treeEdges) {
            if (edge.getParentNode().equals(node)) {
                childNodes.add(edge.getChildNode());
            }
        }
        return childNodes;
    }

    /**
     * Verify if nodeId already exists in tree
     *
     * @param nodeId the node id to check
     * @return the boolean verification value
     */
    private boolean nodeExists(String nodeId) {
        for (Node treeNode : treeNodes) {
            if (treeNode.getNodeId().equals(nodeId)) {
                return true;
            }
        }
        return false;
    }

    public List<Node> getNodes() {
        return treeNodes;
    }

    public List<Edge> getEdges() {
        return treeEdges;
    }

    public ArrayList<ArrayList<String>> getTreeLevelsString() {
        return treeLevelsString;
    }
}
