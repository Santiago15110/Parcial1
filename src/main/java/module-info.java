module org.example.parcial {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.parcial to javafx.fxml;
    exports org.example.parcial;
}