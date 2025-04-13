package org.example.metalerp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for managing views in the application
 */
public class ViewManager {
    private static final Map<String, Node> viewCache = new HashMap<>();
    private static BorderPane mainPane;
    private static String currentView = null;
    
    /**
     * Set the main BorderPane container for the application
     * @param pane The main BorderPane
     */
    public static void setMainPane(BorderPane pane) {
        mainPane = pane;
        currentView = "Home.fxml";  // Set initial view
    }

    /**
     * Load and display a view in the center pane
     * @param fxmlFile The FXML file to load
     * @throws IOException If the file cannot be loaded
     */
    public static void loadView(String fxmlFile) throws IOException {
        if (mainPane == null) {
            throw new IllegalStateException("Main pane not set. Call setMainPane first.");
        }
        
        // Special case for Home.fxml - just reset the view
        if (fxmlFile.equals("Home.fxml") && "Home.fxml".equals(currentView)) {
            System.out.println("Already on Home view, not reloading.");
            return;
        }

        try {
            // Clear current content
            mainPane.setCenter(null);
            
            // Handle the special case for Home.fxml
            if (fxmlFile.equals("Home.fxml") || fxmlFile.equals("hello-view.fxml")) {
                if (viewCache.containsKey("centerContent")) {
                    // Use the cached center content
                    mainPane.setCenter(viewCache.get("centerContent"));
                    System.out.println("Using cached Home center content");
                } else {
                    // Load a fresh copy of the home view
                    FXMLLoader homeLoader = new FXMLLoader(ViewManager.class.getResource("hello-view.fxml"));
                    BorderPane homeView = homeLoader.load();
                    
                    // Extract and cache the center content
                    Node centerContent = homeView.getCenter();
                    if (centerContent != null) {
                        viewCache.put("centerContent", centerContent);
                        mainPane.setCenter(centerContent);
                        System.out.println("Cached and set Home center content");
                    } else {
                        System.err.println("Failed to get center content from Home view");
                    }
                }
                
                currentView = "Home.fxml";
                return;
            }
            
            // Normal view loading with cache
            if (!viewCache.containsKey(fxmlFile)) {
                FXMLLoader loader = new FXMLLoader(ViewManager.class.getResource(fxmlFile));
                Node view = loader.load();
                
                // Set the maximum size to make the view fill the available space
                if (view instanceof Pane) {
                    ((Pane) view).setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                }
                
                viewCache.put(fxmlFile, view);
                System.out.println("Loaded and cached view: " + fxmlFile);
            } else {
                System.out.println("Using cached view: " + fxmlFile);
            }

            // Get view from cache
            Node view = viewCache.get(fxmlFile);
            
            // Set in center
            mainPane.setCenter(view);
            
            // Force layout update
            mainPane.layout();
            
            // Update current view
            currentView = fxmlFile;
            
            System.out.println("View set in center: " + fxmlFile);
        } catch (Exception e) {
            System.err.println("Error loading view: " + fxmlFile);
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * Get the current view name
     * @return The current view filename
     */
    public static String getCurrentView() {
        return currentView;
    }
    
    /**
     * Clear the view cache
     */
    public static void clearCache() {
        viewCache.clear();
    }
} 