package com.cydercode.cams;

import com.cydercode.cams.datamodel.AppStatus;
import com.cydercode.cams.nodeclient.NodeClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Adam Tomaja
 */
public class NodeController {

    @FXML
    private TextField urlField;

    @FXML
    private Label instanceNameLabel;

    @FXML
    private Label healthLabel;
    @FXML
    private Label metadataLabel;

    @FXML
    private Button removeButton;

    public void reload() {
        try {
            NodeClient client = new NodeClient(getUrl());
            AppStatus status = client.getStatus();

            Platform.runLater(() -> {
                instanceNameLabel.setText(status.getInstanceName());
                healthLabel.setText(status.getHealth().toString());
                metadataLabel.setText(status.getMetadata().toString());
            });

        } catch (Exception e) {
            Platform.runLater(() -> healthLabel.setText("Connection error: " + e.getMessage()));
            e.printStackTrace();
        }
    }

    public void setUrl(String url) {
        urlField.setText(url);
    }


    public String getUrl() {
        return urlField.getText();
    }

    public void addRemoveHandler(EventHandler<ActionEvent> eventHandler) {
        removeButton.setOnAction(eventHandler);
    }
}
