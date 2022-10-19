package com.undec.corralon.DTO;

public class VentaPorMes {
    private Integer mes;
    private Integer ventas;

    public VentaPorMes() {
    }

    public VentaPorMes(Integer mes, Integer ventas) {
        this.mes = mes;
        this.ventas = ventas;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getVentas() {
        return ventas;
    }

    public void setVentas(Integer ventas) {
        this.ventas = ventas;
    }
}
