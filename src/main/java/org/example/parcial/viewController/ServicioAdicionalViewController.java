package org.example.parcial.viewController;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import org.example.parcial.App;
import org.example.parcial.controller.IAppControlable;
import org.example.parcial.model.ServicioAdicional;
import org.example.parcial.model.TipoServicioAdicional;

import java.io.IOException;

public class ServicioAdicionalViewController implements IAppControlable {

    private static final String OPCION_DISPONIBLE = "Disponible";
    private static final String OPCION_NO_DISPONIBLE = "No disponible";

    private App app;

    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextArea txtDescripcion;
    @FXML private TextField txtPrecio;
    @FXML private ComboBox<String> cbDisponibilidad;
    @FXML private ComboBox<TipoServicioAdicional> cbTipo;

    @FXML private Label lblMensajeServicio;

    @FXML private TableView<ServicioAdicional> tablaServicios;
    @FXML private TableColumn<ServicioAdicional, String> colCodigo;
    @FXML private TableColumn<ServicioAdicional, String> colNombre;
    @FXML private TableColumn<ServicioAdicional, String> colDescripcion;
    @FXML private TableColumn<ServicioAdicional, Double> colPrecio;
    @FXML private TableColumn<ServicioAdicional, String> colDisponibilidad;
    @FXML private TableColumn<ServicioAdicional, String> colTipo;

    private final ObservableList<ServicioAdicional> datosTabla = FXCollections.observableArrayList();

    @Override
    public void setApp(App app) {
        this.app = app;
        cargarServiciosEnTabla();
    }

    @FXML
    public void initialize() {
        cbDisponibilidad.setItems(FXCollections.observableArrayList(OPCION_DISPONIBLE, OPCION_NO_DISPONIBLE));
        cbDisponibilidad.getSelectionModel().selectFirst();

        cbTipo.setItems(FXCollections.observableArrayList(TipoServicioAdicional.values()));
        cbTipo.getSelectionModel().selectFirst();

        colCodigo.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getCodigo()));
        colNombre.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getNombre()));
        colDescripcion.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getDescripcion()));
        colPrecio.setCellValueFactory(d ->
                new SimpleDoubleProperty(d.getValue().getPrecio()).asObject());
        colDisponibilidad.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().isDisponible() ? OPCION_DISPONIBLE : OPCION_NO_DISPONIBLE));
        colTipo.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getTipo().toString()));

        tablaServicios.setItems(datosTabla);
    }

    private void cargarServiciosEnTabla() {
        // ojo: revisa que el metodo se llame asi en tu clase Gym
        datosTabla.setAll(app.getGym().getListServicioAdicional());
    }

    @FXML
    private void handleRegistrarServicio() {
        lblMensajeServicio.setText("");

        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        String precioTexto = txtPrecio.getText();
        String disponibilidadTexto = cbDisponibilidad.getValue();
        TipoServicioAdicional tipo = cbTipo.getValue();

        if (codigo == null || codigo.isBlank() ||
                nombre == null || nombre.isBlank() ||
                descripcion == null || descripcion.isBlank() ||
                precioTexto == null || precioTexto.isBlank()) {
            lblMensajeServicio.setText("Complete los campos obligatorios.");
            return;
        }

        if (tipo == null) {
            lblMensajeServicio.setText("Seleccione el tipo de servicio.");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioTexto);
            if (precio < 0) {
                lblMensajeServicio.setText("El precio no puede ser negativo.");
                return;
            }
        } catch (NumberFormatException e) {
            lblMensajeServicio.setText("El precio debe ser numerico.");
            return;
        }

        boolean disponibilidad = OPCION_DISPONIBLE.equals(disponibilidadTexto);

        // ojo: revisa el nombre real del metodo y el orden de parametros en Gym
        boolean exito = app.getGym().registrarServicioAdicional(codigo, nombre, descripcion, precio, disponibilidad, tipo);

        if (!exito) {
            lblMensajeServicio.setText("Ya existe un servicio con ese codigo.");
            return;
        }

        lblMensajeServicio.setStyle("-fx-text-fill: #2e7d32;");
        lblMensajeServicio.setText("Servicio registrado.");
        limpiarCampos();
        cargarServiciosEnTabla();
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtPrecio.clear();
        cbDisponibilidad.getSelectionModel().selectFirst();
        cbTipo.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleRegresar() throws IOException {
        app.getSceneManager().cambiarEscena(
                "/org/example/parcial/primerPantalla.fxml"
        );
    }
}