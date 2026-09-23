package org.example.parcial.model;

public class PlanPremium extends PlanEntrenamiento {

    public PlanPremium(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, EstadoPlan estado) {
        super(codigo, nombre, descripcion, duracionMeses, valorMensual, estado);
    }
}
