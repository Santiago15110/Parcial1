package org.example.parcial.model;

public class ServicioAdicional {
    private String  codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean disponicilidad;
    private TipoServicioAdicional tipo;

    public ServicioAdicional(String codigo, String nombre, String descripcion, double precio, boolean disponicilidad, TipoServicioAdicional tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponicilidad = disponicilidad;
        this.tipo = tipo;
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

    public boolean isDisponicilidad() {
        return disponicilidad;
    }

    public void setDisponicilidad(boolean disponicilidad) {
        this.disponicilidad = disponicilidad;
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
                ", disponicilidad=" + disponicilidad +
                ", tipo=" + tipo +
                '}';
    }
}
