package org.example.parcial.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Gym {
    private String nombre;
    private String nit;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private String paginaWeb;
    private ArrayList<Cliente> listClientes;
    private ArrayList<Entrenador> listEntrenador;
    private ArrayList<PlanEntrenamiento> listPlanEntrenamiento;
    private ArrayList<ServicioAdicional> listServicioAdicional;
    private ArrayList<Inscripcion> listIncripcion;


    /**
     * Metodo constructor
     * @param nombre
     * @param nit
     * @param direccion
     * @param telefono
     * @param correoElectronico
     * @param paginaWeb
     * @param listClientes
     * @param listEntrenador
     * @param listPlanEntrenamiento
     * @param listServicioAdicional
     * @param listIncripcion
     */
    public Gym(String nombre, String nit, String direccion, String telefono, String correoElectronico, String paginaWeb, ArrayList<Cliente> listClientes, ArrayList<Entrenador> listEntrenador, ArrayList<PlanEntrenamiento> listPlanEntrenamiento, ArrayList<ServicioAdicional> listServicioAdicional, ArrayList<Inscripcion> listIncripcion) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.paginaWeb = paginaWeb;
        this.listClientes = new ArrayList<>();
        this.listEntrenador = new ArrayList<>();
        this.listPlanEntrenamiento = new ArrayList<>();
        this.listServicioAdicional = new ArrayList<>();
        this.listIncripcion = new ArrayList<>();
    }


    /**
     * metodo para crear un cliente
     * @param nombre
     * @param identificacion
     * @param telefono
     * @param correoElectronico
     * @param edad
     * @param fechaIngreso
     * @return
     */
    public boolean registrarCliente(String nombre, String identificacion, long telefono, String correoElectronico, int edad, LocalDate fechaIngreso){

        Cliente newCliente = new Cliente(nombre, identificacion, telefono, correoElectronico, edad, fechaIngreso );

        for(Cliente c: listClientes){

            if(c.getIdentificacion().equals(identificacion));
            return false;
        }

        if(listClientes == null){
            return false;
        }

        listClientes.add(newCliente);
        return true;

    }

    /**
     *metodo para buscar un cliente por identificacion
     * @param identificacion
     * @return cliente
     */
    public Cliente buscarClienteByIdentificacion(String identificacion){

        for(Cliente c: listClientes){
            if(c.getIdentificacion().equals(identificacion)){
                return c;
            }
        }
        return null;
    }

    /**
     * metodo para registrar un entrenador
     * @param cedula
     * @param nombre
     * @param telefono
     * @param tarifa
     * @return
     */
    public boolean registrarEntrenador(String cedula, String nombre, String telefono, double tarifa){

        Entrenador newEntrenador = new Entrenador(cedula, nombre, telefono, tarifa);

        if(listEntrenador == null){
            return false;

        }

        for(Entrenador e: listEntrenador){
            if(e.getCedula().equals(cedula)){
                return false;
            }
        }

        return listEntrenador.add(newEntrenador);
    }





    /**
     * Metodo para crear el plan basico y premium en gimnasio
     * @param tipo
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     * @param estado
     * @return
     */
    public boolean crearPlanEntrenamiento(TipoPlanEntrenamiento tipo, String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, EstadoPlan estado ){

        if(existePlanConCodigo(codigo)){
            return false;
        }
        PlanEntrenamiento plan = PlanFactory.crearPlan(tipo, codigo, nombre, descripcion, duracionMeses, valorMensual, estado);

        return listPlanEntrenamiento.add(plan);
    }

    /**
     * Metodo para registrar un plan personalizado
     * @param plan
     * @return true
     */
    public boolean crearPlanPersonalizado(PlanPersonalizado plan){

        return listPlanEntrenamiento.add(plan);
    }


    /**
     * metodo para encontrar un codigo de plan ya existente
     * @param codigo
     * @return
     */
    public boolean existePlanConCodigo(String codigo){
        return listPlanEntrenamiento.stream().anyMatch(p -> p.getCodigo().equals(codigo));
    }


    /**
     * Determina si un numero es un numero perfecto, es decir, si la suma
     * de sus divisores propios es igual al mismo numero. Por ejemplo, 6
     * es perfecto porque sus divisores propios (1, 2 y 3) suman 6.
     * Se usa junto con buscarClienteByTelefono para resolver la consulta
     * "el telefono de este cliente, es un numero perfecto?".
     * @param numero numero a evaluar
     * @return true si el numero es perfecto
     */
    public boolean esNumeroPerfecto(long numero) {
        if (numero <= 0) {
            return false;
        }
        long suma = 0;
        for (long i = 1; i < numero; i++) {
            if (numero % i == 0) {
                suma += i;
            }
        }
        return suma == numero;
    }




    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public ArrayList<Cliente> getListClientes() {
        return listClientes;
    }

    public void setListClientes(ArrayList<Cliente> listClientes) {
        this.listClientes = listClientes;
    }

    public ArrayList<Entrenador> getListEntrenador() {
        return listEntrenador;
    }

    public void setListEntrenador(ArrayList<Entrenador> listEntrenador) {
        this.listEntrenador = listEntrenador;
    }

    public ArrayList<PlanEntrenamiento> getListPlanEntrenamiento() {
        return listPlanEntrenamiento;
    }

    public void setListPlanEntrenamiento(ArrayList<PlanEntrenamiento> listPlanEntrenamiento) {
        this.listPlanEntrenamiento = listPlanEntrenamiento;
    }

    public ArrayList<ServicioAdicional> getListServicioAdicional() {
        return listServicioAdicional;
    }

    public void setListServicioAdicional(ArrayList<ServicioAdicional> listServicioAdicional) {
        this.listServicioAdicional = listServicioAdicional;
    }

    public ArrayList<Inscripcion> getListIncripcion() {
        return listIncripcion;
    }

    public void setListIncripcion(ArrayList<Inscripcion> listIncripcion) {
        this.listIncripcion = listIncripcion;
    }

    @Override
    public String toString() {
        return "Gym{" +
                "nombre='" + nombre + '\'' +
                ", nit='" + nit + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", paginaWeb='" + paginaWeb + '\'' +
                ", listClientes=" + listClientes +
                ", listEntrenador=" + listEntrenador +
                ", listPlanEntrenamiento=" + listPlanEntrenamiento +
                ", listServicioAdicional=" + listServicioAdicional +
                ", listIncripcion=" + listIncripcion +
                '}';
    }
}
