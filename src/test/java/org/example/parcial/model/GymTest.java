package org.example.parcial.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class GymTest {

    private Gym gym;

    @BeforeEach
    void setUp() {
        gym = Gym.getInstance(
                "PowerGym", "900123456-1", "Calle 10 # 5-20",
                "3001234567", "contacto@powergym.com", "www.powergym.com");
    }


    // Constructor

    @Nested
    @DisplayName("Constructor")
    class ConstructorTests {

        @Test
        @DisplayName("Inicializa todas las listas vacías, sin importar lo que se le pase")
        void constructorIgnoraListasRecibidasYCreaVacias() {
            ArrayList<Cliente> clientesConDatos = new ArrayList<>();
            clientesConDatos.add(new Cliente("Ana", "111", 3000000000L, "ana@mail.com", 25, LocalDate.now()));

            Gym gymConDatosPrevios = Gym.getInstance(
                    "OtroGym", "900999999-1", "Calle 1", "3000000000",
                    "otro@mail.com", "www.otro.com"
            );

            // el constructor no asigna las listas recibidas,
            // siempre crea ArrayList nuevos y vacíos.
            assertTrue(gymConDatosPrevios.getListClientes().isEmpty());
            assertTrue(gymConDatosPrevios.getListEntrenador().isEmpty());
            assertTrue(gymConDatosPrevios.getListPlanEntrenamiento().isEmpty());
            assertTrue(gymConDatosPrevios.getListServicioAdicional().isEmpty());
            assertTrue(gymConDatosPrevios.getListIncripcion().isEmpty());
        }

        @Test
        @DisplayName("Los campos simples sí se asignan correctamente")
        void constructorAsignaCamposSimples() {
            assertEquals("PowerGym", gym.getNombre());
            assertEquals("900123456-1", gym.getNit());
            assertEquals("Calle 10 # 5-20", gym.getDireccion());
            assertEquals("3001234567", gym.getTelefono());
            assertEquals("contacto@powergym.com", gym.getCorreoElectronico());
            assertEquals("www.powergym.com", gym.getPaginaWeb());
        }
    }


    // registrarCliente / buscarClienteByIdentificacion

    @Nested
    @DisplayName("registrarCliente")
    class RegistrarClienteTests {

        @Test
        @DisplayName("Registra correctamente el primer cliente (lista vacía)")
        void registraPrimerClienteExitosamente() {
            boolean resultado = gym.registrarCliente(
                    "Juan Perez", "123", 3001112222L, "juan@mail.com", 30, LocalDate.of(2024, 1, 10)
            );

            assertTrue(resultado);
            assertEquals(1, gym.getListClientes().size());
            assertEquals("Juan Perez", gym.getListClientes().get(0).getNombre());
        }

        @Test
        @DisplayName("BUG: tras el primer registro, cualquier registro posterior devuelve false " +
                "aunque el id sea distinto, por el ';' de más en el if del for")
        void segundoRegistroSiempreFallaPorBug() {
            gym.registrarCliente("Juan Perez", "123", 3001112222L, "juan@mail.com", 30, LocalDate.now());

            boolean resultadoClienteNuevo = gym.registrarCliente(
                    "Maria Lopez", "456", 3002223333L, "maria@mail.com", 28, LocalDate.now()
            );

            assertFalse(resultadoClienteNuevo, "Se esperaba false por el bug del ';' en el for");
            assertEquals(1, gym.getListClientes().size(), "El cliente nuevo no debería haberse agregado");
        }

        @Test
        @DisplayName("buscarClienteByIdentificacion encuentra un cliente existente")
        void buscarClienteExistente() {
            // Se agregan directamente a la lista para no depender del bug de registrarCliente
            Cliente c1 = new Cliente("Juan Perez", "123", 3001112222L, "juan@mail.com", 30, LocalDate.now());
            Cliente c2 = new Cliente("Maria Lopez", "456", 3002223333L, "maria@mail.com", 28, LocalDate.now());
            gym.getListClientes().add(c1);
            gym.getListClientes().add(c2);

            Cliente encontrado = gym.buscarClienteByIdentificacion("456");

            assertNotNull(encontrado);
            assertEquals("Maria Lopez", encontrado.getNombre());
        }

        @Test
        @DisplayName("buscarClienteByIdentificacion devuelve null si no existe")
        void buscarClienteInexistente() {
            gym.getListClientes().add(new Cliente("Juan Perez", "123", 3001112222L, "juan@mail.com", 30, LocalDate.now()));

            Cliente encontrado = gym.buscarClienteByIdentificacion("999");

            assertNull(encontrado);
        }

        @Test
        @DisplayName("buscarClienteByIdentificacion devuelve null si la lista está vacía")
        void buscarClienteListaVacia() {
            assertNull(gym.buscarClienteByIdentificacion("cualquiera"));
        }
    }


    // registrarEntrenador

    @Nested
    @DisplayName("registrarEntrenador")
    class RegistrarEntrenadorTests {

        @Test
        @DisplayName("Registra correctamente un entrenador nuevo")
        void registraEntrenadorExitosamente() {
            boolean resultado = gym.registrarEntrenador("111", "Carlos Gomez", "3001112222", 50000);

            assertTrue(resultado);
            assertEquals(1, gym.getListEntrenador().size());
            assertEquals("Carlos Gomez", gym.getListEntrenador().get(0).getNombre());
        }

        @Test
        @DisplayName("Rechaza un entrenador con cédula duplicada y no lo agrega")
        void rechazaEntrenadorConCedulaDuplicada() {
            gym.registrarEntrenador("111", "Carlos Gomez", "3001112222", 50000);

            boolean resultado = gym.registrarEntrenador("111", "Otro Nombre", "3009998888", 70000);

            assertFalse(resultado);
            assertEquals(1, gym.getListEntrenador().size());
        }

        @Test
        @DisplayName("Permite registrar varios entrenadores con cédulas distintas")
        void registraVariosEntrenadoresDistintos() {
            assertTrue(gym.registrarEntrenador("111", "Carlos Gomez", "3001112222", 50000));
            assertTrue(gym.registrarEntrenador("222", "Laura Diaz", "3003334444", 60000));

            assertEquals(2, gym.getListEntrenador().size());
        }
    }


    // crearPlanEntrenamiento / existePlanConCodigo

    @Nested
    @DisplayName("crearPlanEntrenamiento")
    class CrearPlanEntrenamientoTests {

        @Test
        @DisplayName("Crea correctamente un PLAN_BASICO con código único")
        void creaPlanBasicoExitosamente() {
            boolean resultado = gym.crearPlanEntrenamiento(
                    TipoPlanEntrenamiento.PLAN_BASICO, "B1", "Basico Mensual",
                    "Descripcion basico", 1, 80000, EstadoPlan.ACTIVO
            );

            assertTrue(resultado);
            assertEquals(1, gym.getListPlanEntrenamiento().size());
            assertEquals(TipoPlanEntrenamiento.PLAN_BASICO, gym.getListPlanEntrenamiento().get(0).getTipo());
        }

        @Test
        @DisplayName("Crea correctamente un PLAN_PREMIUM con código único")
        void creaPlanPremiumExitosamente() {
            boolean resultado = gym.crearPlanEntrenamiento(
                    TipoPlanEntrenamiento.PLAN_PREMIUM, "P1", "Premium Mensual",
                    "Descripcion premium", 1, 150000, EstadoPlan.ACTIVO
            );

            assertTrue(resultado);
            assertEquals(TipoPlanEntrenamiento.PLAN_PREMIUM, gym.getListPlanEntrenamiento().get(0).getTipo());
        }

        @Test
        @DisplayName("Rechaza crear un plan con un código ya existente")
        void rechazaPlanConCodigoDuplicado() {
            gym.crearPlanEntrenamiento(TipoPlanEntrenamiento.PLAN_BASICO, "B1", "Basico",
                    "Desc", 1, 80000, EstadoPlan.ACTIVO);

            boolean resultado = gym.crearPlanEntrenamiento(TipoPlanEntrenamiento.PLAN_PREMIUM, "B1",
                    "Otro nombre", "Otra desc", 2, 120000, EstadoPlan.ACTIVO);

            assertFalse(resultado);
            assertEquals(1, gym.getListPlanEntrenamiento().size());
        }

        @Test
        @DisplayName("Lanza IllegalArgumentException al intentar crear PLAN_PERSONALIZADO por esta vía")
        void lanzaExcepcionParaPlanPersonalizado() {
            assertThrows(IllegalArgumentException.class, () ->
                    gym.crearPlanEntrenamiento(TipoPlanEntrenamiento.PLAN_PERSONALIZADO, "PP1",
                            "Personalizado", "Desc", 1, 100000, EstadoPlan.ACTIVO)
            );

            // Como la excepción se lanza antes de agregar, la lista debe seguir vacía
            assertTrue(gym.getListPlanEntrenamiento().isEmpty());
        }

        @Test
        @DisplayName("existePlanConCodigo devuelve true si el código existe")
        void existePlanConCodigoTrue() {
            gym.crearPlanEntrenamiento(TipoPlanEntrenamiento.PLAN_BASICO, "B1", "Basico",
                    "Desc", 1, 80000, EstadoPlan.ACTIVO);

            assertTrue(gym.existePlanConCodigo("B1"));
        }

        @Test
        @DisplayName("existePlanConCodigo devuelve false si el código no existe")
        void existePlanConCodigoFalse() {
            assertFalse(gym.existePlanConCodigo("NOEXISTE"));
        }
    }


    // crearPlanPersonalizado

    @Nested
    @DisplayName("crearPlanPersonalizado")
    class CrearPlanPersonalizadoTests {

        private PlanPersonalizado construirPlanPersonalizado(String codigo) {
            Entrenador entrenador = new Entrenador("111", "Carlos Gomez", "3001112222", 20000);
            return new PlanPersonalizado.Builder()
                    .conCodigo(codigo)
                    .conNombre("Plan a la medida")
                    .conDescripcion("Entrenamiento personalizado")
                    .conDuracion(2)
                    .conValorMensual(200000)
                    .conCantidadSesiones(8)
                    .conEspecialidad("Fuerza")
                    .conObjetivo("Ganar masa muscular")
                    .conEntrenador(entrenador)
                    .build();
        }

        @Test
        @DisplayName("Agrega correctamente un plan personalizado")
        void agregaPlanPersonalizadoExitosamente() {
            PlanPersonalizado plan = construirPlanPersonalizado("PP1");

            boolean resultado = gym.crearPlanPersonalizado(plan);

            assertTrue(resultado);
            assertEquals(1, gym.getListPlanEntrenamiento().size());
            assertEquals(TipoPlanEntrenamiento.PLAN_PERSONALIZADO, gym.getListPlanEntrenamiento().get(0).getTipo());
        }

        @Test
        @DisplayName("NO valida códigos duplicados (a diferencia de crearPlanEntrenamiento)")
        void noValidaCodigoDuplicado() {
            gym.crearPlanEntrenamiento(TipoPlanEntrenamiento.PLAN_BASICO, "DUP", "Basico",
                    "Desc", 1, 80000, EstadoPlan.ACTIVO);

            PlanPersonalizado planDuplicado = construirPlanPersonalizado("DUP");
            boolean resultado = gym.crearPlanPersonalizado(planDuplicado);

            // BUG/comportamiento: se agrega igual, aunque el código "DUP" ya exista
            assertTrue(resultado);
            assertEquals(2, gym.getListPlanEntrenamiento().size());
        }
    }


    // esNumeroPerfecto

    @Nested
    @DisplayName("esNumeroPerfecto")
    class EsNumeroPerfectoTests {

        @Test
        @DisplayName("6 es un número perfecto (1+2+3=6)")
        void seisEsPerfecto() {
            assertTrue(gym.esNumeroPerfecto(6));
        }

        @Test
        @DisplayName("28 es un número perfecto (1+2+4+7+14=28)")
        void veintiochoEsPerfecto() {
            assertTrue(gym.esNumeroPerfecto(28));
        }

        @Test
        @DisplayName("12 no es un número perfecto")
        void doceNoEsPerfecto() {
            assertFalse(gym.esNumeroPerfecto(12));
        }

        @Test
        @DisplayName("1 no es un número perfecto")
        void unoNoEsPerfecto() {
            assertFalse(gym.esNumeroPerfecto(1));
        }

        @Test
        @DisplayName("0 no es un número perfecto")
        void ceroNoEsPerfecto() {
            assertFalse(gym.esNumeroPerfecto(0));
        }

        @Test
        @DisplayName("Números negativos no son perfectos")
        void negativoNoEsPerfecto() {
            assertFalse(gym.esNumeroPerfecto(-6));
        }
    }
}