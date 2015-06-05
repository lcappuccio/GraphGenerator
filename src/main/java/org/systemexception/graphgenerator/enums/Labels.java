package org.systemexception.graphgenerator.enums;

/**
 * @author leo
 * @date 05/06/15 18:21
 */
public enum Labels {

    NODE_NAME("Node_"),
    LEVEL_NAME("_Level");

    private final String label;

    Labels(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
