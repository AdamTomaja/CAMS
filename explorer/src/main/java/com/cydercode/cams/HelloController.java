package com.cydercode.cams;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    private final NodeFactory nodeFactory = new NodeFactory();

    private final List<Node> nodes = new ArrayList<>();

    @FXML
    public VBox nodesContainer;

    @FXML
    public void initialize() throws IOException {
        log.info("Inited");
        addNewNode();
    }

    @FXML
    public void addNewNode() throws IOException {
        Node node = nodeFactory.create();
        node.getController().setUrl("http://cydercode.pl:8083/cams-node/");
        nodes.add(node);
        nodesContainer.getChildren().add(node.getComponent());
    }

    @FXML
    public void reloadAll() {
        for (Node node : nodes) {
            node.getController().reload();
        }
    }

    @FXML
    public void getAllUrls() {
        for (Node node : nodes) {
            node.getController().setStatus("Statu!!!");
        }
    }
}
