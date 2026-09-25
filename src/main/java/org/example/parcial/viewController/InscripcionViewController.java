package org.example.parcial.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.example.parcial.App;
import org.example.parcial.controller.IAppControlable;
import org.example.parcial.model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class InscripcionViewController implements IAppControlable {

    private App app;

    @FXML private ComboBox<Cliente> cbCliente;
    @FXML private ComboBox<PlanEntrenamiento> cbPlan;
    @FXML private DatePicker dpFechaInicio;
    @FXML private DatePicker dpFechaFin;
    @FXML private ComboBox<ServicioAdicional> cbServicio;
    @FXML private Label lblMensajeInscripcion;

    @FXML private TableView<Inscripcion> tablaInscripciones;
    @FXML private TableColumn<Inscripcion, String> colCliente;
    @FXML private TableColumn<Inscripcion, String> colPlan;
    @FXML private TableColumn<Inscripcion, String> colFechaInicio;
    @FXML private TableColumn<Inscripcion, String> colFechaFin;
    @FXML private TableColumn<Inscripcion, String> colServicios;
    @FXML private TableColumn<Inscripcion, String> colTotal;

    private final ObservableList<Inscripcion> datosTabla = FXCollections.observableArrayList();

    @Override
    public void setApp(App app) {
        this.app = app;
        cbCliente.setItems(FXCollections.observableArrayList(app.getGym().getListClientes()));
        cbPlan.setItems(FXCollections.observableArrayList(app.getGym().getListPlanEntrenamiento()));
        cbServicio.setItems(FXCollections.observableArrayList(app.getGym().getListServicioAdicional()));
        cargarInscripcionesEnTabla();
    }

    @FXML
    public void initialize() {
        colCliente.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getCliente().getNombre()));
        colPlan.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getPlan().getNombre()));
        colFechaInicio.setCellValueFactory(d ->
                new SimpleStringProperty(String.valueOf(d.getValue().getFechaInicio())));
        colFechaFin.setCellValueFactory(d ->
                new SimpleStringProperty(String.valueOf(d.getValue().getFechaFin())));
        colServicios.setCellValueFactory(d ->
                new SimpleStringProperty(String.valueOf(d.getValue().getListServiciosAdicionales().size())));
        colTotal.setCellValueFactory(d ->
                new SimpleStringProperty(String.valueOf(d.getValue().getValorTotal())));

        tablaInscripciones.setItems(datosTabla);

        dpFechaFin.setDisable(true);
        cbPlan.valueProperty().addListener((obs, oldVal, newVal) -> actualizarFechaFinPreview());
        dpFechaInicio.valueProperty().addListener((obs, oldVal, newVal) -> actualizarFechaFinPreview());
    }

    private void actualizarFechaFinPreview() {
        PlanEntrenamiento plan = cbPlan.getValue();
        LocalDate fechaInicio = dpFechaInicio.getValue();

        if (plan != null && fechaInicio != null) {
            dpFechaFin.setValue(fechaInicio.plusMonths(plan.getDuracionMeses()));
        } else {
            dpFechaFin.setValue(null);
        }
    }

    private void cargarInscripcionesEnTabla() {
        datosTabla.setAll(app.getGym().getListIncripcion());
    }

    @FXML
    private void handleRegistrarInscripcion() {
        lblMensajeInscripcion.setText("");

        Cliente cliente = cbCliente.getValue();
        PlanEntrenamiento plan = cbPlan.getValue();
        LocalDate fecha = dpFechaInicio.getValue();

        if (cliente == null || plan == null || fecha == null) {
            lblMensajeInscripcion.setText("Cliente, plan y fecha son obligatorios.");
            return;
        }

        String id = "INS-" + UUID.randomUUID().toString().substring(0, 8);

        Inscripcion inscripcion = new Inscripcion(id, fecha, cliente, plan, plan.calcularValorTotal());

        ServicioAdicional servicio = cbServicio.getValue();
        if (servicio != null) {
            inscripcion.agregarServicioAdicional(servicio);
            inscripcion.setValorTotal(inscripcion.calcularValor());
        }

        boolean exito = app.getGym().getListIncripcion().add(inscripcion);

        if (!exito) {
            lblMensajeInscripcion.setText("No se pudo registrar la inscripcion.");
            return;
        }

        lblMensajeInscripcion.setStyle("-fx-text-fill: #2e7d32;");
        lblMensajeInscripcion.setText("Inscripcion registrada correctamente.");
        limpiarCampos();
        cargarInscripcionesEnTabla();
    }

    private void limpiarCampos() {
        cbCliente.setValue(null);
        cbPlan.setValue(null);
        dpFechaInicio.setValue(null);
        dpFechaFin.setValue(null);
        cbServicio.setValue(null);
    }

    @FXML
    private void handleRegresar() throws IOException {
        app.getSceneManager().cambiarEscena("/org/example/parcial/primerPantalla.fxml");
    }
}