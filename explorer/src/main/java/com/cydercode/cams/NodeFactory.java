package com.cydercode.cams;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * @author Adam Tomaja
 */
public class NodeFactory {


    public Node create() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/node.fxml"));
        return new Node((Parent) loader.load(), loader.getController());
    }
}
