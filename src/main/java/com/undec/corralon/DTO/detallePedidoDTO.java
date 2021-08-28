package com.undec.corralon.DTO;

public class detallePedidoDTO {
    private String codigoArticulo;
    private String nombreArticulo;
    private Double stockArticulo;
    private Double valorIngresado;

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

    public Double getStockArticulo() {
        return stockArticulo;
    }

    public void setStockArticulo(Double stockArticulo) {
        this.stockArticulo = stockArticulo;
    }

    public Double getValorIngresado() {
        return valorIngresado;
    }

    public void setValorIngresado(Double valorIngresado) {
        this.valorIngresado = valorIngresado;
    }
}
