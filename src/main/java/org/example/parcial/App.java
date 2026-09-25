package org.example.parcial;

import javafx.stage.Stage;
import org.example.parcial.model.Gym;
import org.example.parcial.util.SceneManager;

public class App {

    private final Gym gym;
    private final Stage stagePrincipal;
    private final SceneManager sceneManager;

    public App(Stage stagePrincipal) {
        this.gym = Gym.getInstance("Luxury", "123456", "calle 15", "3138761284", "luxury@hotmail.com", "luxury.com");
        this.stagePrincipal = stagePrincipal;
        this.sceneManager = new SceneManager(stagePrincipal);
    }

    public Gym getGym() {
        return gym;
    }

    public Stage getStagePrincipal() {
        return stagePrincipal;
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }
}