package org.example.parcial.model;

import java.util.PrimitiveIterator;

public class Entrenador {
    private String cedula;
    private String nombre;
    private String telefono;
    private double tarifaSesion;

    public Entrenador(String cedula, String nombre, String telefono, double tarifaSesion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tarifaSesion = tarifaSesion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getTarifaSesion() {
        return tarifaSesion;
    }

    public void setTarifaSesion(double tarifaSesion) {
        this.tarifaSesion = tarifaSesion;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", tarifaSesion=" + tarifaSesion +
                '}';
    }
}
