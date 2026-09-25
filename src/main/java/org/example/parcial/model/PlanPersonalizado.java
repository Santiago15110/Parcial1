package org.example.parcial.model;

public class PlanPersonalizado extends PlanEntrenamiento{


        private int cantidadSesiones;
        private String especialidadRequerida;
        private String objetivosCliente;
        private Entrenador entrenadorAsignado;


        private PlanPersonalizado(Builder builder) {
            super(builder.codigo, builder.nombre, builder.descripcion, builder.duracionMeses, builder.valorMensual, EstadoPlan.ACTIVO);
            this.cantidadSesiones = builder.cantidadSesiones;
            this.especialidadRequerida = builder.especialidadRequerida;
            this.objetivosCliente = builder.objetivosCliente;
            this.entrenadorAsignado = builder.entrenadorAsignado;
        }

        @Override
        public double calcularValorTotal() {
            double base = valorMensual * duracionMeses;
            double costoSesiones = (entrenadorAsignado != null)
                    ? cantidadSesiones * entrenadorAsignado.getTarifaSesion()
                    : 0;
            return base + costoSesiones;
        }

        public int getCantidadSesiones() {
            return cantidadSesiones; }
        public String getEspecialidadRequerida() {
            return especialidadRequerida; }
        public String getObjetivosCliente() {
            return objetivosCliente; }
        public Entrenador getEntrenadorAsignado() {
            return entrenadorAsignado; }

    /**
     * clase del builder anidada
     */
    public static class Builder {

            private String codigo;
            private String nombre;
            private String descripcion;
            private int duracionMeses;
            private double valorMensual;
            private int cantidadSesiones;
            private String especialidadRequerida;
            private String objetivosCliente;
            private Entrenador entrenadorAsignado;

            public Builder conCodigo(String codigo) {
                this.codigo = codigo;
                return this;
            }

            public Builder conNombre(String nombre) {
                this.nombre = nombre;
                return this;
            }

            public Builder conDescripcion(String descripcion) {
                this.descripcion = descripcion;
                return this;
            }

            public Builder conDuracion(int duracionMeses) {
                this.duracionMeses = duracionMeses;
                return this;
            }

            public Builder conValorMensual(double valorMensual) {
                this.valorMensual = valorMensual;
                return this;
            }

            public Builder conCantidadSesiones(int cantidadSesiones) {
                this.cantidadSesiones = cantidadSesiones;
                return this;
            }

            public Builder conEspecialidad(String especialidadRequerida) {
                this.especialidadRequerida = especialidadRequerida;
                return this;
            }

            public Builder conObjetivo(String objetivosCliente) {
                this.objetivosCliente = objetivosCliente;
                return this;
            }

            public Builder conEntrenador(Entrenador entrenador) {
                this.entrenadorAsignado = entrenador;
                return this;
            }

            public PlanPersonalizado build() {

                if (codigo == null || codigo.isEmpty()) {
                    throw new IllegalStateException("El código es obligatorio");
                }
                if (cantidadSesiones <= 0) {
                    throw new IllegalStateException("La cantidad de sesiones debe ser mayor a 0");
                }
                return new PlanPersonalizado(this);
            }
        }
    }