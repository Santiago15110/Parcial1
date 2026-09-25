package org.example.parcial.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    @FXML private TextField txtTelefono;
    @FXML private TextField txtTarifa;

    @FXML private Label lblMensajeEntrenador;

    @FXML private TableView<Entrenador> tablaEntrenadores;
    @FXML private TableColumn<Entrenador, String> colNombre;
    @FXML private TableColumn<Entrenador, String> colIdentificacion;
    @FXML private TableColumn<Entrenador, String> colTelefono;
    @FXML private TableColumn<Entrenador, Double> colTarifa;

    private final ObservableList<Entrenador> datosTabla = FXCollections.observableArrayList();

    @Override
    public void setApp(App app) {
        this.app = app;
        cargarEntrenadoresEnTabla();
    }

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getNombre()));
        colIdentificacion.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getCedula()));
        colTelefono.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getTelefono()));
        colTarifa.setCellValueFactory(d ->
                new SimpleDoubleProperty(d.getValue().getTarifaSesion()).asObject());

        tablaEntrenadores.setItems(datosTabla);
    }

    private void cargarEntrenadoresEnTabla() {
        datosTabla.setAll(app.getGym().getListEntrenador());
    }

    @FXML
    private void handleRegistrarEntrenador() {
        lblMensajeEntrenador.setText("");

        String id = txtIdentificacion.getText();
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String tarifaTexto = txtTarifa.getText();

        if (id == null || id.isBlank() ||
                nombre == null || nombre.isBlank() ||
                telefono == null || telefono.isBlank() ||
                tarifaTexto == null || tarifaTexto.isBlank()) {
            lblMensajeEntrenador.setText("Complete los campos obligatorios.");
            return;
        }

        double tarifa;
        try {
            tarifa = Double.parseDouble(tarifaTexto);
        } catch (NumberFormatException e) {
            lblMensajeEntrenador.setText("La tarifa debe ser numerica.");
            return;
        }

        boolean exito = app.getGym().registrarEntrenador(id, nombre, telefono, tarifa);
        // ojo: revisa el orden real de parametros de tu Gym.registrarEntrenador

        if (!exito) {
            lblMensajeEntrenador.setText("Ya existe un entrenador con esa identificacion.");
            return;
        }

        lblMensajeEntrenador.setStyle("-fx-text-fill: #2e7d32;");
        lblMensajeEntrenador.setText("Entrenador registrado.");
        limpiarCampos();
        cargarEntrenadoresEnTabla();
    }

    private void limpiarCampos() {
        txtIdentificacion.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtTarifa.clear();
    }

    @FXML
    private void handleRegresar() throws IOException {
        app.getSceneManager().cambiarEscena(
                "/org/example/parcial/primerPantalla.fxml"
        );
    }
}