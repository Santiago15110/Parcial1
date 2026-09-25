package org.example.parcial.model;

public class PlanFactory {


    public static  PlanEntrenamiento crearPlan(TipoPlanEntrenamiento tipo, String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, EstadoPlan estado){

        switch (tipo){

            case PLAN_BASICO :
                return new PlanBasico(codigo, nombre, descripcion, duracionMeses, valorMensual, estado);

            case PLAN_PREMIUM:

                return new PlanPremium(codigo, nombre, descripcion, duracionMeses, valorMensual, estado);

        }
    }
}
