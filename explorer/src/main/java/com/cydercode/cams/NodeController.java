package com.cydercode.cams;

import com.cydercode.cams.datamodel.AppStatus;
import com.cydercode.cams.nodeclient.NodeClient;
import javafx.fxml.FXML;
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
    private Label appUptimeLabel;

    @FXML
    private Label systemUptimeLabel;

    @FXML Label healthLabel;

    public void reload() {
        NodeClient client = new NodeClient(getUrl());
        AppStatus status = client.getStatus();
        instanceNameLabel.setText(status.getInstanceName());
        appUptimeLabel.setText(String.valueOf(status.getAppUptime()));
        systemUptimeLabel.setText(String.valueOf(status.getSystemUptime()));
        healthLabel.setText(status.getHealth().toString());
    }

    public void setUrl(String url) {
        urlField.setText(url);
    }


    public String getUrl() {
        return urlField.getText();
    }
}
