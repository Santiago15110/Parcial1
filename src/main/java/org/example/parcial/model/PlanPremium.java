package org.example.parcial.model;

public class PlanPremium extends PlanEntrenamiento {

    private static final double RECARGO= 1.5;

    public PlanPremium(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, EstadoPlan estado) {
        super(codigo, nombre, descripcion, duracionMeses, valorMensual, estado);
    }


    @Override
    public double calcularValorTotal(){

        return duracionMeses * valorMensual * RECARGO;

    }

    @Override
    public TipoPlanEntrenamiento getTipo(){
        return TipoPlanEntrenamiento.PLAN_PREMIUM;
    }
}
