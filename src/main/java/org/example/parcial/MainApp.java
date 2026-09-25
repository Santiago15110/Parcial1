package org.example.parcial;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.parcial.viewController.PrimerPantallaViewController;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        App app = new App(stage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/parcial/primerPantalla.fxml"));
        Parent root = loader.load();

        PrimerPantallaViewController controller = loader.getController();
        controller.setApp(app); // ← ESTA LÍNEA es la que probablemente falta

        stage.setScene(new Scene(root));
        stage.setTitle("SmartGym");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}