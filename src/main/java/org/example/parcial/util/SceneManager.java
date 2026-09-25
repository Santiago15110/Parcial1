package org.example.parcial.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    private final Stage stage;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void cambiarEscena(String ruta) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(ruta)
        );

        Scene scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.show();
    }
}