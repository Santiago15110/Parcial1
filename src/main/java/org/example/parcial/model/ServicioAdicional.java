package org.example.parcial.model;

public class ServicioAdicional implements IaCalculable {
    private String  codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean disponibilidad;
    private TipoServicioAdicional tipo;


    /**
     * Metodo constructor
     * @param codigo codigo unico del servicio
     * @param nombre nombre del servicio
     * @param descripcion descripcion del servicio
     * @param precio precio del servicio
     * @param disponibilidad indica si el servicio esta disponibilidad para solicitarse
     * @param tipo tipo de servicio adicional
     */
    public ServicioAdicional(String codigo, String nombre, String descripcion, double precio, boolean disponibilidad, TipoServicioAdicional tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
        this.tipo = tipo;
    }


    /**
     * Indica si el servicio adicional esta disponible para ser solicitado.
     * @return true si el servicio esta disponible
     */
    public boolean isDisponible() {
        return disponibilidad;
    }

    /**
     * Calcula el valor monetario que aporta este servicio adicional al
     * total de una inscripcion.
     * @return precio del servicio
     */
    @Override
    public double calcularValor() {
        return precio;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public TipoServicioAdicional getTipo() {
        return tipo;
    }

    public void setTipo(TipoServicioAdicional tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "ServicioAdicional{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", disponicilidad=" + disponibilidad +
                ", tipo=" + tipo +
                '}';
    }
}
