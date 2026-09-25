module org.example.parcial {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.parcial to javafx.fxml;
    exports org.example.parcial;
    opens org.example.parcial.viewController to javafx.fxml;
    opens org.example.parcial.model to javafx.fxml;
}