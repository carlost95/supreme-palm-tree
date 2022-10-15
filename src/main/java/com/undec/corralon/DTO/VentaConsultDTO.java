package com.undec.corralon.DTO;

import com.undec.corralon.modelo.Cliente;
import com.undec.corralon.modelo.Direccion;
import com.undec.corralon.modelo.Empresa;

import java.util.Date;
import java.util.List;

public class VentaConsultDTO {
    private Integer idVenta;
    private Cliente cliente;
    private Empresa empresa;
    private Direccion direccion;
    private Date fechaVenta;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
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

    public List<ArticuloVentaDTO> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<ArticuloVentaDTO> articulos) {
        this.articulos = articulos;
    }
}
