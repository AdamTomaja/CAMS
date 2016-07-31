package com.cydercode.cams;

import javafx.scene.Parent;

/**
 * @author Adam Tomaja
 */
public class Node {

    private final Parent component;

    private final NodeController controller;

    public Node(Parent component, NodeController controller) {
        this.component = component;
        this.controller = controller;
    }

    public Parent getComponent() {
        return component;
    }

    public NodeController getController() {
        return controller;
    }
}
