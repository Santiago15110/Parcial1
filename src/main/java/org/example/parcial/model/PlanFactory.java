package org.example.parcial.model;

public class PlanFactory {

    private PlanFactory(){

    }

    /**
     * metodo para crear los  planes dependiendo de su tipo
     * @param tipo
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     * @param estado
     * @return  un plan de entrenamiento
     */
    public static  PlanEntrenamiento crearPlan(TipoPlanEntrenamiento tipo, String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, EstadoPlan estado){

        switch (tipo){

            case PLAN_BASICO :
                return new PlanBasico(codigo, nombre, descripcion, duracionMeses, valorMensual, estado);

            case PLAN_PREMIUM:

                return new PlanPremium(codigo, nombre, descripcion, duracionMeses, valorMensual, estado);

            case PLAN_PERSONALIZADO:
                throw new IllegalArgumentException(
                        "Plan personalizado requiere PlanPersonalizado.Builder, no esta fábrica");
            default:
                throw new IllegalArgumentException("Tipo de plan no soportado: " + tipo);


        }
    }
}
