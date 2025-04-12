package org.example.metalerp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        
        // Get the primary screen's dimensions
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        
        // Set minimum window size
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        
        stage.setTitle("Metal-ERP");
        //Image icon = new Image(HelloApplication.class.getResourceAsStream("Img/Title-gears-solid.png"));
        // stage.getIcons().add(icon);
        stage.setScene(scene);
        
        // Make the window maximized by default
        stage.setMaximized(true);
        
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}