package org.example.metalerp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloApplication extends Application {
    private ExecutorService executorService;
    
    @Override
    public void start(Stage stage) throws IOException {
        // Create thread pool for background tasks
        executorService = Executors.newFixedThreadPool(2);
        
        // Set JavaFX thread priority
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        
        // Enable hardware acceleration and performance optimizations
        System.setProperty("prism.order", "d3d,sw");
        System.setProperty("prism.vsync", "false");
        System.setProperty("prism.forceGPU", "true");
        System.setProperty("javafx.animation.pulse", "60");
        System.setProperty("javafx.animation.fullspeed", "true");
        
        // Preload fonts to avoid lag when rendering text
        try {
            Font.loadFont(getClass().getResourceAsStream("/org/example/metalerp/fonts/Roboto-Regular.ttf"), 13);
        } catch (Exception e) {
            System.err.println("Warning: Could not load font: " + e.getMessage());
        }
        
        // Load the main UI
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        BorderPane root = fxmlLoader.load();
        
        // Set the main pane in the ViewManager
        ViewManager.setMainPane(root);
        
        Scene scene = new Scene(root);
        
        // Get controller
        HelloController controller = fxmlLoader.getController();
        
        // Get the primary screen's dimensions
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        
        // Set minimum window size
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        
        stage.setTitle("Metal-ERP");
        //Image icon = new Image(HelloApplication.class.getResourceAsStream("Img/Title-gears-solid.png"));
        //stage.getIcons().add(icon);
        stage.setScene(scene);
        
        // Make the window maximized by default
        stage.setMaximized(true);
        
        stage.show();
        
        // Pre-load FXML files in background for better responsiveness using executor service
        executorService.submit(() -> {
            try {
                // Pre-cache views
                String[] views = {
                    "Customer.fxml",
                    "Orders.fxml",
                    "Inventory.fxml",
                    "Purchase.fxml"
                };
                
                for (String view : views) {
                    try {
                        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(view));
                        loader.load();
                        System.out.println("Pre-loaded: " + view);
                    } catch (Exception e) {
                        System.err.println("Failed to pre-load: " + view);
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    @Override
    public void stop() {
        // Shutdown executor service when application closes
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}