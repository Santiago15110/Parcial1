package org.example.parcial.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.parcial.App;
import org.example.parcial.controller.IAppControlable;

import java.io.IOException;

public class SceneManager {

    private final Stage stage;
    private App app; // referencia para poder inyectarla en cada controlador nuevo

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void cambiarEscena(String ruta) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
        Scene scene = new Scene(loader.load());

        Object controller = loader.getController();
        if (controller instanceof IAppControlable) {
            ((IAppControlable) controller).setApp(app);
        }

        stage.setScene(scene);
        stage.show();
    }
}