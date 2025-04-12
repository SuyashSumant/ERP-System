package org.example.metalerp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class HelloController {
    @FXML
    private BorderPane vb;

    @FXML
    private BorderPane mainPane;

    @FXML
    private void   Orders() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Orders.fxml"));
        AnchorPane view = loader.load();
        
        // Ensure the view fits properly in the center area
        AnchorPane.setTopAnchor(view, 0.0);
        AnchorPane.setBottomAnchor(view, 0.0);
        AnchorPane.setLeftAnchor(view, 0.0);
        AnchorPane.setRightAnchor(view, 0.0);
        
        mainPane.setCenter(view);
    }

    private void loadScreen(String fxmlFile) throws IOException {
        AnchorPane newScreen = FXMLLoader.load(getClass().getResource(fxmlFile));
        vb.getChildren().setAll(newScreen);
    }
}