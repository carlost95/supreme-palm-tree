package com.undec.corralon.DTO;

public class DetallePedidoDTO {
    private String codigoArticulo;
    private String nombreArticulo;
    private Double stockArticulo;
    private Integer valorIngresado;

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

    public Integer getValorIngresado() {
        return valorIngresado;
    }

    public void setValorIngresado(Integer valorIngresado) {
        this.valorIngresado = valorIngresado;
    }
}
