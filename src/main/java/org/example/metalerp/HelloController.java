package org.example.metalerp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private BorderPane mainPane;
    
    // Store button references for disabling active button
    @FXML
    private Button dashboardButton;
    
    @FXML 
    private Button customersButton;
    
    @FXML
    private Button ordersButton;
    
    @FXML
    private Button inventoryButton;
    
    @FXML
    private Button purchasesButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the main pane in the ViewManager
        if (mainPane != null) {
            ViewManager.setMainPane(mainPane);
            System.out.println("Main pane set in ViewManager");
        } else {
            System.err.println("Main pane is null - navigation will not work");
        }
    }

    @FXML
    private void Home() throws IOException {
        System.out.println("Loading Dashboard view...");
        // We don't actually navigate here because we're already on home
        // Just reload the current content
        reloadMainContent();
    }

    @FXML
    private void Customers() throws IOException {
        System.out.println("Loading Customers view...");
        ViewManager.loadView("Customer.fxml");
    }

    @FXML
    private void Orders() throws IOException {
        System.out.println("Loading Orders view...");
        ViewManager.loadView("Orders.fxml");
    }

    @FXML
    private void Inventory() throws IOException {
        System.out.println("Loading Inventory view...");
        ViewManager.loadView("Inventory.fxml");
    }

    @FXML
    private void Purchase() throws IOException {
        System.out.println("Loading Purchase view...");
        ViewManager.loadView("Purchase.fxml");
    }
    
    // Reload the main content (used by Home button)
    private void reloadMainContent() {
        // Get the default home content and set it
        try {
            // Get the view from hello-view.fxml's center
            if (mainPane != null && mainPane.getCenter() == null) {
                // If center is null, reload the default content
                ViewManager.loadView("hello-view.fxml");
            } else {
                System.out.println("Already on home view, content already loaded");
            }
        } catch (Exception e) {
            System.err.println("Error reloading main content: " + e.getMessage());
            e.printStackTrace();
        }
    }
}