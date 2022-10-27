package com.undec.corralon.DTO;

public class RecaudacionDTO {
    String codigoArticulo;
    String nombreArticulo;
    String nombreMarca;
    Double cantidadVendida;
    Double stockArticulo;
    Double recaudacion;

    public RecaudacionDTO(String codigoArticulo, String nombreArticulo, String nombreMarca, Double cantidadVendida, Double stockArticulo, Double recaudacion) {
        this.codigoArticulo = codigoArticulo;
        this.nombreArticulo = nombreArticulo;
        this.nombreMarca = nombreMarca;
        this.cantidadVendida = cantidadVendida;
        this.stockArticulo = stockArticulo;
        this.recaudacion = recaudacion;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public Double getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(Double cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public Double getStockArticulo() {
        return stockArticulo;
    }

    public void setStockArticulo(Double stockArticulo) {
        this.stockArticulo = stockArticulo;
    }

    public Double getRecaudacion() {
        return recaudacion;
    }

    public void setRecaudacion(Double recaudacion) {
        this.recaudacion = recaudacion;
    }

}
