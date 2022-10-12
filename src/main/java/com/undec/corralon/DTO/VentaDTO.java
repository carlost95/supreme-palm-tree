package com.undec.corralon.DTO;

import java.util.Date;
import java.util.List;

public class VentaDTO {
    private Integer idVenta;
    private Integer idCliente;
    private String nombreCliente;
    private Integer idEmpresa;
    private Integer idDireccion;
    private Date fecha;
    private Double descuento;
    private Double total;
    private Long nroVenta;
    List<ArticuloVentaDTO> articulos;

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getNroVenta() {
        return nroVenta;
    }

    public void setNroVenta(Long nroVenta) {
        this.nroVenta = nroVenta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public List<ArticuloVentaDTO> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<ArticuloVentaDTO> articulos) {
        this.articulos = articulos;
    }
}