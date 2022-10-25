package com.undec.corralon.DTO;

public class DataReporte {
    Integer acciones;
    String fecha;

    public DataReporte(Integer acciones, String fecha) {
        this.acciones = acciones;
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getAcciones() {
        return acciones;
    }

    public void setAcciones(Integer accion) {
        this.acciones = accion;
    }
}
