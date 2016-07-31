package com.cydercode.cams;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    public static final String URLS_PREF_KEY = "urls";
    public static final String URLS_SEPARATOR = "\n";
    public static final String DEFAULT_URL = "http://cydercode.pl:8083/cams-node/";

    private final NodeFactory nodeFactory = new NodeFactory();
    private final List<Node> nodes = new ArrayList<>();
    private final Preferences prefs = Preferences.userRoot().node(HelloController.class.getName());

    @FXML
    public VBox nodesContainer;

    @FXML
    public void initialize() throws IOException {
        loadUrlsFromPrefs();
    }

    private void loadUrlsFromPrefs() throws IOException {
        String[] urls = prefs.get(URLS_PREF_KEY, "").split(URLS_SEPARATOR);
        for(String url : urls) {
            addNewNode(url);
        }
        System.out.printf(URLS_PREF_KEY);
    }

    @FXML
    public void addNewNode() throws IOException {
        addNewNode(DEFAULT_URL);
    }

    public void addNewNode(String url) throws IOException {
        Node node = nodeFactory.create();
        node.getController().setUrl(url);
        nodes.add(node);
        nodesContainer.getChildren().add(node.getComponent());
    }

    @FXML
    public void save() throws BackingStoreException {
        String urls = nodes.stream()
                .map(n -> n.getController().getUrl())
                .collect(Collectors.joining(URLS_SEPARATOR));
        prefs.put(URLS_PREF_KEY, urls);
        prefs.sync();
    }


    @FXML
    public void reloadAll() {
        for (Node node : nodes) {
            node.getController().reload();
        }
    }
}
