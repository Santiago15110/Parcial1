package org.example.parcial.model;

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


    public


    /**
     * metodo para encontrar un codigo de plan ya existente
     * @param codigo
     * @return
     */
    public boolean existePlanConCodigo(String codigo){
        return listPlanEntrenamiento.stream().anyMatch(p -> p.getCodigo().equals(codigo));
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
