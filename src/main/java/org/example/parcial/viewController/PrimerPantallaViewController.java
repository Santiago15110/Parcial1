package org.example.parcial.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.example.parcial.App;
import org.example.parcial.controller.IAppControlable;

import java.io.IOException;

public class PrimerPantallaViewController implements IAppControlable {

    private App app;

    @FXML
    private ComboBox cbCliente;

    @FXML
    private ComboBox cbPlan;

    @FXML
    private DatePicker dpFechaInicio;

    @FXML
    private Label lblFechaFin;

    @FXML
    private ComboBox cbServicio;

    @FXML
    private Spinner spCantidad;

    @FXML
    private Button btnAgregarServicio;

    @FXML
    private TableView tablaServicios;

    @FXML
    private TableColumn colServicio;

    @FXML
    private TableColumn colCantidad;

    @FXML
    private TableColumn colSubtotal;

    @FXML
    private Label lblValorTotal;

    @FXML
    private Button btnGuardarInscripcion;

    @Override
    public void setApp(App app) {
        this.app = app;
        System.out.println("App recibida correctamente");
    }

    @FXML
    public void initialize() {
        System.out.println("Primer pantalla inicializada");
    }

    @FXML
    private void agregarServicio() {
        System.out.println("Agregando servicio...");
    }

    @FXML
    private void guardarInscripcion() {
        System.out.println("Guardando inscripción...");
    }

    @FXML
    private void irGestionClientes() throws IOException {
        app.getSceneManager().cambiarEscena("/org/example/parcial/cliente.fxml");
    }

    @FXML
    private void irGestionEntrenadores() throws IOException {
        app.getSceneManager().cambiarEscena("/org/example/parcial/entrenador.fxml");
    }

    @FXML
    private void irGestionPlanes() throws IOException {
        app.getSceneManager().cambiarEscena("/org/example/parcial/planEntrenamiento.fxml");
    }

    @FXML
    private void irGestionServicios() throws IOException {
        app.getSceneManager().cambiarEscena("/org/example/parcial/serviciosAdicionales.fxml");
    }

    @FXML
    private void irGestionInscripciones() throws IOException{
        app.getSceneManager().cambiarEscena("/org/example/parcial/inscripciones.fxml");
    }

    
}