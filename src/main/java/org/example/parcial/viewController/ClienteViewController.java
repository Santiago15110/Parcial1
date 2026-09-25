package org.example.parcial.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;

import org.example.parcial.App;
import org.example.parcial.controller.IAppControlable;
import org.example.parcial.model.Cliente;

import java.io.IOException;
import java.time.LocalDate;

public class ClienteViewController implements IAppControlable {

    private App app;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDocumento;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtEdad;

    @FXML
    private DatePicker dpFechaIngreso;

    @FXML
    private Label lblMensajeCliente;

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private TableColumn<Cliente, String> colDocumento;

    @FXML
    private TableColumn<Cliente, String> colTelefono;

    @FXML
    private TableColumn<Cliente, String> colCorreo;

    @FXML
    private TableColumn<Cliente, String> colEdad;

    @FXML
    private TableColumn<Cliente, String> colIngreso;

    private final ObservableList<Cliente> datosTabla = FXCollections.observableArrayList();

    @Override
    public void setApp(App app) {
        this.app = app;
        cargarClientesEnTabla();
    }

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getNombre()));
        colDocumento.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getIdentificacion()));
        colTelefono.setCellValueFactory(data ->
                new SimpleStringProperty(String.valueOf(data.getValue().getTelefono())));
        colCorreo.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getCorreoElectronico()));
        colEdad.setCellValueFactory(data ->
                new SimpleStringProperty(String.valueOf(data.getValue().getEdad())));
        colIngreso.setCellValueFactory(data ->
                new SimpleStringProperty(String.valueOf(data.getValue().getFechaIngreso())));

        tablaClientes.setItems(datosTabla);
    }

    private void cargarClientesEnTabla() {
        datosTabla.setAll(app.getGym().getListClientes());
    }

    @FXML
    private void handleRegistrarCliente() {
        lblMensajeCliente.setText("");

        String nombre = txtNombre.getText();
        String documento = txtDocumento.getText();
        String telefonoTexto = txtTelefono.getText();
        String correo = txtCorreo.getText();
        String edadTexto = txtEdad.getText();
        LocalDate fechaIngreso = dpFechaIngreso.getValue();

        if (nombre == null || nombre.isBlank() ||
                documento == null || documento.isBlank() ||
                telefonoTexto == null || telefonoTexto.isBlank() ||
                correo == null || correo.isBlank() ||
                edadTexto == null || edadTexto.isBlank() ||
                fechaIngreso == null) {
            lblMensajeCliente.setText("Todos los campos son obligatorios.");
            return;
        }

        long telefono;
        int edad;
        try {
            telefono = Long.parseLong(telefonoTexto);
            edad = Integer.parseInt(edadTexto);
        } catch (NumberFormatException e) {
            lblMensajeCliente.setText("Telefono y edad deben ser numericos.");
            return;
        }

        boolean exito = app.getGym().registrarCliente(
                nombre, documento, telefono, correo, edad, fechaIngreso);

        if (!exito) {
            lblMensajeCliente.setText("Ya existe un cliente con ese documento.");
            return;
        }

        lblMensajeCliente.setStyle("-fx-text-fill: #2e7d32;");
        lblMensajeCliente.setText("Cliente registrado correctamente.");
        limpiarCampos();
        cargarClientesEnTabla();
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtDocumento.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        txtEdad.clear();
        dpFechaIngreso.setValue(null);
    }

    @FXML
    private void handleRegresar() throws IOException {
        app.getSceneManager().cambiarEscena("/org/example/parcial/primerPantalla.fxml");
    }
}