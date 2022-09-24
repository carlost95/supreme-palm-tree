package com.undec.corralon.DTO;

public class ArticuloStockDTO {

    private Integer id;
    private String nombre;
    private String codigoArt;
    private Integer idProveedor;
    private Double stockActual;
    private Integer cantidad;

    public ArticuloStockDTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoArt() {
        return codigoArt;
    }

    public void setCodigoArt(String codigoArt) {
        this.codigoArt = codigoArt;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Double getStockActual() {
        return stockActual;
    }

    public void setStockActual(Double stockActual) {
        this.stockActual = stockActual;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
