package org.example.parcial.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.example.parcial.App;
import org.example.parcial.controller.IAppControlable;
import org.example.parcial.model.Cliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaViewController implements IAppControlable {

    private App app;

    @FXML private Button btnRegresar;
    @FXML private TextField txtTelefono;
    @FXML private Button btnBuscar;
    @FXML private Label lblMensajeConsulta;

    @FXML private Label lblNombreCliente;
    @FXML private Label lblTelefono;
    @FXML private Label lblEsPerfecto;
    @FXML private Label lblDivisores;
    @FXML private Label lblSumaDivisores;

    @Override
    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    public void initialize() {
        limpiarResultado();
    }

    @FXML
    private void handleBuscarCliente() {
        lblMensajeConsulta.setText("");

        String telefonoTexto = txtTelefono.getText();
        if (telefonoTexto == null || telefonoTexto.isBlank()) {
            lblMensajeConsulta.setText("Ingresa un numero de telefono.");
            limpiarResultado();
            return;
        }
        telefonoTexto = telefonoTexto.trim();

        long telefono;
        try {
            telefono = Long.parseLong(telefonoTexto);
        } catch (NumberFormatException e) {
            lblMensajeConsulta.setText("El telefono debe contener solo digitos.");
            limpiarResultado();
            return;
        }

        // ojo: Gym no tiene un metodo de busqueda por telefono, se filtra aqui directamente
        Cliente cliente = app.getGym().getListClientes().stream()
                .filter(c -> c.getTelefono() == telefono)
                .findFirst()
                .orElse(null);

        if (cliente == null) {
            lblMensajeConsulta.setText("No se encontro ningun cliente con ese telefono.");
            limpiarResultado();
            return;
        }

        boolean esPerfecto = app.getGym().esNumeroPerfecto(telefono);
        List<Long> divisores = calcularDivisoresPropios(telefono);
        long suma = divisores.stream().mapToLong(Long::longValue).sum();

        lblNombreCliente.setText(cliente.getNombre());
        lblTelefono.setText(String.valueOf(cliente.getTelefono()));
        lblEsPerfecto.setText(esPerfecto ? "Si" : "No");
        lblDivisores.setText(divisores.isEmpty() ? "Ninguno" : formatearDivisores(divisores));
        lblSumaDivisores.setText(String.valueOf(suma));
    }

    /**
     * Calcula los divisores propios de un numero, solo para mostrarlos en pantalla.
     * La logica de "es numero perfecto" en si la resuelve Gym.esNumeroPerfecto(long).
     */
    private List<Long> calcularDivisoresPropios(long numero) {
        List<Long> divisores = new ArrayList<>();
        if (numero <= 1) {
            return divisores;
        }
        for (long i = 1; i < numero; i++) {
            if (numero % i == 0) {
                divisores.add(i);
            }
        }
        return divisores;
    }

    private String formatearDivisores(List<Long> divisores) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < divisores.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(divisores.get(i));
        }
        return sb.toString();
    }

    private void limpiarResultado() {
        lblNombreCliente.setText("-");
        lblTelefono.setText("-");
        lblEsPerfecto.setText("-");
        lblDivisores.setText("-");
        lblSumaDivisores.setText("-");
    }

    @FXML
    private void handleRegresar() throws IOException {
        app.getSceneManager().cambiarEscena("/org/example/parcial/primerPantalla.fxml");
    }
}