/**
 * @author leo
 * @date 05/06/15 18:21
 */
package org.systemexception.graphgenerator.enums;

public enum Labels {

    NODE_NAME("Node_"),
    LEVEL_NAME("Level"),
	NAME_SEPARATOR("_");

    private final String label;

	Labels(String label) {
		this.label = label;
	}

    @Override
    public String toString() {
        return label;
    }
}
