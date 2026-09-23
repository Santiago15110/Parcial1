package org.example.parcial.model;

import java.time.LocalDate;

public class Inscripcion {
    private String id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double valorTotal;


    public Inscripcion(String id, LocalDate fechaInicio, LocalDate fechaFin, double valorTotal) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.valorTotal = valorTotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "id='" + id + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
