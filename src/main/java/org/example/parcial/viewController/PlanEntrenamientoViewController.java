package org.example.parcial.viewController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.layout.AnchorPane;
import org.example.parcial.App;
import org.example.parcial.controller.IAppControlable;
import org.example.parcial.model.*;

import java.io.IOException;

public class PlanEntrenamientoViewController implements IAppControlable {

    private App app;

    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextArea txtDescripcion;
    @FXML private TextField txtDuracionMeses;
    @FXML private TextField txtValorMensual;
    @FXML private ComboBox<TipoPlanEntrenamiento> cbTipoPlan;
    @FXML private ComboBox<EstadoPlan> cbEstado;

    @FXML private AnchorPane panelPersonalizado;
    @FXML private TextField txtCantidadSesiones;
    @FXML private TextField txtEspecialidadRequerida;
    @FXML private TextArea txtObjetivosCliente;
    @FXML private ComboBox<Entrenador> cbEntrenadorAsignado;

    @FXML private Label lblMensajePlan;

    @FXML private TableView<PlanEntrenamiento> tablaPlanes;
    @FXML private TableColumn<PlanEntrenamiento, String> colCodigo;
    @FXML private TableColumn<PlanEntrenamiento, String> colNombre;
    @FXML private TableColumn<PlanEntrenamiento, String> colTipo;
    @FXML private TableColumn<PlanEntrenamiento, String> colDuracion;
    @FXML private TableColumn<PlanEntrenamiento, String> colValor;
    @FXML private TableColumn<PlanEntrenamiento, String> colEstado;

    private final ObservableList<PlanEntrenamiento> datosTabla = FXCollections.observableArrayList();

    @Override
    public void setApp(App app) {
        this.app = app;
        cbEntrenadorAsignado.setItems(FXCollections.observableArrayList(app.getGym().getListEntrenador()));
        cargarPlanesEnTabla();
    }

    @FXML
    public void initialize() {
        cbTipoPlan.setItems(FXCollections.observableArrayList(TipoPlanEntrenamiento.values()));
        cbEstado.setItems(FXCollections.observableArrayList(EstadoPlan.values()));

        colCodigo.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCodigo()));
        colNombre.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombre()));
        colTipo.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getTipo().toString()));
        colDuracion.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDuracionMeses() + " meses"));
        colValor.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().calcularValorTotal())));
        colEstado.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getEstado().toString()));

        tablaPlanes.setItems(datosTabla);
    }

    @FXML
    private void handleCambioTipoPlan() {
        TipoPlanEntrenamiento seleccion = cbTipoPlan.getValue();
        boolean esPersonalizado = seleccion == TipoPlanEntrenamiento.PLAN_PERSONALIZADO;

        panelPersonalizado.setVisible(esPersonalizado);
        panelPersonalizado.setManaged(esPersonalizado);
    }

    private void cargarPlanesEnTabla() {
        datosTabla.setAll(app.getGym().getListPlanEntrenamiento());
    }

    @FXML
    private void handleRegistrarPlan() {
        lblMensajePlan.setText("");

        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        String duracionTexto = txtDuracionMeses.getText();
        String valorTexto = txtValorMensual.getText();
        TipoPlanEntrenamiento tipo = cbTipoPlan.getValue();
        EstadoPlan estado = cbEstado.getValue();

        if (codigo == null || codigo.isBlank() ||
                nombre == null || nombre.isBlank() ||
                duracionTexto == null || duracionTexto.isBlank() ||
                valorTexto == null || valorTexto.isBlank() ||
                tipo == null || estado == null) {
            lblMensajePlan.setText("Todos los campos son obligatorios.");
            return;
        }

        int duracionMeses;
        double valorMensual;
        try {
            duracionMeses = Integer.parseInt(duracionTexto);
            valorMensual = Double.parseDouble(valorTexto);
        } catch (NumberFormatException e) {
            lblMensajePlan.setText("Duracion y valor deben ser numericos.");
            return;
        }

        boolean exito;

        if (tipo == TipoPlanEntrenamiento.PLAN_PERSONALIZADO) {
            exito = registrarPlanPersonalizado(codigo, nombre, descripcion, duracionMeses, valorMensual, estado);
        } else {
            exito = app.getGym().crearPlanEntrenamiento(tipo, codigo, nombre, descripcion,
                    duracionMeses, valorMensual, estado);
        }

        if (!exito) {
            lblMensajePlan.setText("Ya existe un plan con ese codigo.");
            return;
        }

        lblMensajePlan.setStyle("-fx-text-fill: #2e7d32;");
        lblMensajePlan.setText("Plan registrado correctamente.");
        limpiarCampos();
        cargarPlanesEnTabla();
    }

    private boolean registrarPlanPersonalizado(String codigo, String nombre, String descripcion,
                                               int duracionMeses, double valorMensual, EstadoPlan estado) {
        String sesionesTexto = txtCantidadSesiones.getText();
        String especialidad = txtEspecialidadRequerida.getText();
        String objetivos = txtObjetivosCliente.getText();
        Entrenador entrenador = cbEntrenadorAsignado.getValue();

        if (sesionesTexto == null || sesionesTexto.isBlank() ||
                especialidad == null || especialidad.isBlank() ||
                entrenador == null) {
            lblMensajePlan.setText("Completa los datos del plan personalizado.");
            return false;
        }

        int cantidadSesiones;
        try {
            cantidadSesiones = Integer.parseInt(sesionesTexto);
        } catch (NumberFormatException e) {
            lblMensajePlan.setText("La cantidad de sesiones debe ser numerica.");
            return false;
        }

        PlanPersonalizado plan = new PlanPersonalizado.Builder()
                .conCodigo(codigo)
                .conNombre(nombre)
                .conDescripcion(descripcion)
                .conDuracion(duracionMeses)
                .conValorMensual(valorMensual)
                .conCantidadSesiones(cantidadSesiones)
                .conEspecialidad(especialidad)
                .conObjetivo(objetivos)
                .conEntrenador(entrenador)
                .build();

        return app.getGym().crearPlanPersonalizado(plan);
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtDuracionMeses.clear();
        txtValorMensual.clear();
        cbTipoPlan.setValue(null);
        cbEstado.setValue(null);

        txtCantidadSesiones.clear();
        txtEspecialidadRequerida.clear();
        txtObjetivosCliente.clear();
        cbEntrenadorAsignado.setValue(null);

        panelPersonalizado.setVisible(false);
        panelPersonalizado.setManaged(false);
    }

    @FXML
    private void handleRegresar() throws IOException {
        app.getSceneManager().cambiarEscena("/org/example/parcial/primerPantalla.fxml");
    }
}