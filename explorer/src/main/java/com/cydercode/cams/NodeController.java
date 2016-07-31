package com.cydercode.cams;

import com.cydercode.cams.datamodel.AppStatus;
import com.cydercode.cams.nodeclient.NodeClient;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * @author Adam Tomaja
 */
public class NodeController {

    @FXML
    private TextField urlField;

    @FXML
    private TextField statusField;

    public void reload() {
        NodeClient client = new NodeClient(getUrl());
        AppStatus status = client.getStatus();
        setStatus(status.toString());
    }

    public void setUrl(String url) {
        urlField.setText(url);
    }


    public String getUrl() {
        return urlField.getText();
    }

    public void setStatus(String status) {
        statusField.setText(status);
    }
}
