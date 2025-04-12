module org.example.metalerp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.example.metalerp to javafx.fxml;
    exports org.example.metalerp;
}