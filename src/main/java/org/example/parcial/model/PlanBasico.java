package org.example.parcial.model;

public class PlanBasico extends PlanEntrenamiento{




    public PlanBasico(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, EstadoPlan estado) {
        super(codigo, nombre, descripcion, duracionMeses, valorMensual, estado.ACTIVO);
    }

    /**
     * metodo de calcular valor total
     * @return valor total
     */
    @Override
    public double calcularValorTotal(){
        return duracionMeses * valorMensual;
    }

    /**
     * metodo para obtener el tipo de plan de entrenamiento
     * @return tipo de entrenamiento
     */
    @Override
    public TipoPlanEntrenamiento getTipo(){
        return TipoPlanEntrenamiento.PLAN_BASICO;
    }




}
