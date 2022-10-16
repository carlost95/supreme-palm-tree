package com.undec.corralon.DTO;

public class Parada {

    private String latitud;
    private String longitud;
    private String cliente;
    private String direccion;
    private String barrio;

    public Parada() {

    }
    public Parada(String latitud, String longitud, String cliente, String direccion, String barrio) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.cliente = cliente;
        this.direccion = direccion;
        this.barrio = barrio;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }
}
