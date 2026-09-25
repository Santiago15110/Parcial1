package org.example.parcial.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Inscripcion implements IaCalculable {
    private String id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Cliente cliente;
    private PlanEntrenamiento plan;
    private List<ServicioAdicional> listServiciosAdicionales;
    private double valorTotal;


    /**
     * Crea una inscripcion de un cliente a un plan de entrenamiento, sin
     * servicios adicionales ni descuento inicial.
     * @param id codigo unico de la inscripcion
     * @param fechaInicio fecha en la que se realiza la inscripcion
     * @param cliente cliente que se inscribe
     * @param plan plan de entrenamiento adquirido
     *
     */
    public Inscripcion(String id, LocalDate fechaInicio, Cliente cliente, PlanEntrenamiento plan,  double valorTotal) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaInicio.plusMonths(plan.getDuracionMeses());;
        this.cliente = cliente;
        this.plan = plan;
        this.listServiciosAdicionales = new ArrayList<>();
        this.valorTotal = valorTotal;

    }


        /**
         * Asocia un servicio adicional solicitado por el cliente a esta
         * inscripcion (y por lo tanto a su plan), siempre que el servicio
         * este disponible y no haya sido agregado antes.
         * @param servicio servicio adicional a agregar
         * @return true si el servicio fue agregado correctamente
         */
        public boolean agregarServicioAdicional(ServicioAdicional servicio) {
            if (servicio == null || !servicio.isDisponible()) {
                return false;
            }
            if (listServiciosAdicionales.contains(servicio)) {
                return false;
            }
            listServiciosAdicionales.add(servicio);
            return true;
        }

        /**
         * Retira un servicio adicional previamente asociado a esta inscripcion.
         * @param servicio servicio adicional a remover
         * @return true si el servicio fue removido correctamente
         */
        public boolean removerServicioAdicional(ServicioAdicional servicio) {
            return listServiciosAdicionales.remove(servicio);
        }

        /**
         * Calcula el valor total de la inscripcion: el valor del plan (segun
         * su tipo y duracion) mas los servicios adicionales solicitados,
         * menos el descuento aplicado por el gimnasio.
         * @return valor total de la inscripcion, nunca negativo
         */
        @Override
        public double calcularValor() {
            double total = plan.calcularValorTotal();
            for (ServicioAdicional servicio : listServiciosAdicionales) {
                total += servicio.calcularValor();
            }

            return total;
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
