package org.example.parcial.model;

public class PlanPersonalizado extends PlanEntrenamiento{

    public PlanPersonalizado(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, EstadoPlan estado) {
        super(codigo, nombre, descripcion, duracionMeses, valorMensual, estado);
    }
}
