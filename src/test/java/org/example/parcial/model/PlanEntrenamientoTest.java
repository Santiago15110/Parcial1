package org.example.parcial.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PlanEntrenamientoTest {

    /**
     * Subclase mínima de prueba, solo para poder instanciar la clase abstracta
     * y controlar exactamente lo que devuelven los métodos abstractos.
     */
    private static class PlanDePrueba extends PlanEntrenamiento {

        private final TipoPlanEntrenamiento tipoFijo;
        private final double valorTotalFijo;

        PlanDePrueba(String codigo, String nombre, String descripcion, int duracionMeses,
                     double valorMensual, EstadoPlan estado,
                     TipoPlanEntrenamiento tipoFijo, double valorTotalFijo) {
            super(codigo, nombre, descripcion, duracionMeses, valorMensual, estado);
            this.tipoFijo = tipoFijo;
            this.valorTotalFijo = valorTotalFijo;
        }

        @Override
        public double calcularValorTotal() {
            return valorTotalFijo;
        }

        @Override
        public TipoPlanEntrenamiento getTipo() {
            return tipoFijo;
        }
    }


    // Constructor

    @Nested
    @DisplayName("Constructor")
    class ConstructorTests {

        @Test
        @DisplayName("Asigna correctamente todos los campos recibidos")
        void asignaCamposCorrectamente() {
            PlanEntrenamiento plan = new PlanDePrueba(
                    "COD1", "Plan de prueba", "Descripcion de prueba",
                    3, 90000, EstadoPlan.ACTIVO,
                    TipoPlanEntrenamiento.PLAN_BASICO, 270000
            );

            assertEquals("COD1", plan.getCodigo());
            assertEquals("Plan de prueba", plan.getNombre());
            assertEquals("Descripcion de prueba", plan.getDescripcion());
            assertEquals(3, plan.getDuracionMeses());
            assertEquals(90000, plan.getValorMensual());
            assertEquals(EstadoPlan.ACTIVO, plan.getEstado());
        }

        @Test
        @DisplayName("Permite crear planes con distintos estados (SUSPENDIDO, FINALIZADO)")
        void permiteDistintosEstados() {
            PlanEntrenamiento planSuspendido = new PlanDePrueba(
                    "COD2", "Plan suspendido", "Desc", 1, 50000,
                    EstadoPlan.SUSPENDIDO, TipoPlanEntrenamiento.PLAN_PREMIUM, 50000
            );
            PlanEntrenamiento planFinalizado = new PlanDePrueba(
                    "COD3", "Plan finalizado", "Desc", 6, 60000,
                    EstadoPlan.FINALIZADO, TipoPlanEntrenamiento.PLAN_BASICO, 360000
            );

            assertEquals(EstadoPlan.SUSPENDIDO, planSuspendido.getEstado());
            assertEquals(EstadoPlan.FINALIZADO, planFinalizado.getEstado());
        }
    }


    // Contrato abstracto / polimorfismo

    @Nested
    @DisplayName("Contrato abstracto / polimorfismo")
    class PolimorfismoTests {

        @Test
        @DisplayName("calcularValorTotal() y getTipo() se despachan a la implementación de la subclase")
        void despachaMetodosAbstractosASubclase() {
            PlanEntrenamiento plan = new PlanDePrueba(
                    "COD4", "Plan X", "Desc", 2, 100000,
                    EstadoPlan.ACTIVO, TipoPlanEntrenamiento.PLAN_PREMIUM, 200000
            );

            assertEquals(TipoPlanEntrenamiento.PLAN_PREMIUM, plan.getTipo());
            assertEquals(200000, plan.calcularValorTotal());
        }
    }


    // Comportamiento heredado con una subclase real: PlanPersonalizado

    @Nested
    @DisplayName("Comportamiento heredado con una subclase real (PlanPersonalizado)")
    class ConSubclaseRealTests {

        private PlanPersonalizado construirPlan(Entrenador entrenador) {
            PlanPersonalizado.Builder builder = new PlanPersonalizado.Builder()
                    .conCodigo("PP-1")
                    .conNombre("Plan personalizado de prueba")
                    .conDescripcion("Descripcion")
                    .conDuracion(2)
                    .conValorMensual(150000)
                    .conCantidadSesiones(10)
                    .conEspecialidad("Fuerza")
                    .conObjetivo("Ganar masa muscular");

            if (entrenador != null) {
                builder.conEntrenador(entrenador);
            }
            return builder.build();
        }

        @Test
        @DisplayName("getEstado() siempre es ACTIVO en un PlanPersonalizado (queda fijo en el constructor)")
        void estadoSiempreActivo() {
            PlanEntrenamiento plan = construirPlan(null);

            assertEquals(EstadoPlan.ACTIVO, plan.getEstado());
        }

        @Test
        @DisplayName("calcularValorTotal() suma el costo de sesiones cuando hay entrenador asignado")
        void calcularValorTotalConEntrenador() {
            Entrenador entrenador = new Entrenador("111", "Carlos Gomez", "3001112222", 20000);
            PlanEntrenamiento plan = construirPlan(entrenador);

            // base = valorMensual * duracionMeses = 150000 * 2 = 300000
            // sesiones = cantidadSesiones * tarifaSesion = 10 * 20000 = 200000
            double esperado = 300000 + 200000;

            assertEquals(esperado, plan.calcularValorTotal());
        }

        @Test
        @DisplayName("calcularValorTotal() no suma costo de sesiones si no hay entrenador asignado")
        void calcularValorTotalSinEntrenador() {
            PlanEntrenamiento plan = construirPlan(null);

            double esperado = 150000 * 2; // solo la base, sin costo de sesiones

            assertEquals(esperado, plan.calcularValorTotal());
        }

        @Test
        @DisplayName("getTipo() devuelve PLAN_PERSONALIZADO a través de la referencia PlanEntrenamiento")
        void getTipoPersonalizado() {
            PlanEntrenamiento plan = construirPlan(null);

            assertEquals(TipoPlanEntrenamiento.PLAN_PERSONALIZADO, plan.getTipo());
        }
    }
}