package org.example.parcial.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.example.parcial.App;
import org.example.parcial.controller.IAppControlable;
import org.example.parcial.model.Entrenador;

import java.io.IOException;

public class EntrenadorViewController implements IAppControlable {

    private App app;

    @FXML private TextField txtIdentificacion;
    @FXML private TextField txtNombre;
    @FXML private TextField txtEspecialidad;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtTarifa;

    @FXML private Label lblMensajeEntrenador;

    @FXML private TableView<Entrenador> tablaEntrenadores;
    @FXML private TableColumn<Entrenador, String> colNombre;
    @FXML private TableColumn<Entrenador, String> colIdentificacion;
    @FXML private TableColumn<Entrenador, String> colEspecialidad;
    @FXML private TableColumn<Entrenador, String> colTelefono;
    @FXML private TableColumn<Entrenador, Double> colTarifa;

    @Override
    public void setApp(App app) {
        this.app = app;

    }

    @FXML
    public void initialize() {
        // Configuración de la tabla si la necesitas
    }

    @FXML
    private void handleRegistrarEntrenador() {

        String id = txtIdentificacion.getText();
        String nombre = txtNombre.getText();
        String especialidad = txtEspecialidad.getText();
        String telefono = txtTelefono.getText();
        String tarifa = txtTarifa.getText();

        if (id.isEmpty() || nombre.isEmpty()) {
            lblMensajeEntrenador.setText("Complete los campos obligatorios.");
            return;
        }

        lblMensajeEntrenador.setText("Entrenador registrado.");
    }

    @FXML
    private void handleRegresar() throws IOException {

        app.getSceneManager().cambiarEscena(
                "/org/example/parcial/primerPantalla.fxml"
        );
    }
}