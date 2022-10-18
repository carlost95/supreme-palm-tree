package com.undec.corralon.DTO;

import com.undec.corralon.modelo.Cliente;
import com.undec.corralon.modelo.Direccion;
import com.undec.corralon.modelo.Empresa;

import java.util.Date;
import java.util.List;

public class RemitoConsultDTO {
    private Integer idRemito;
    private Long nroRemito;
    private Date fechaRemito;
    private Boolean entregado;
    private Direccion direccion;
    private Cliente cliente;
    private Empresa empresa;
    private List<ArticuloRemitoDTO> articulos;

    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

    public Long getNroRemito() {
        return nroRemito;
    }

    public void setNroRemito(Long nroRemito) {
        this.nroRemito = nroRemito;
    }

    public Date getFechaRemito() {
        return fechaRemito;
    }

    public void setFechaRemito(Date fechaRemito) {
        this.fechaRemito = fechaRemito;
    }

    public Boolean getEntregado() {
        return entregado;
    }

    public void setEntregado(Boolean entregado) {
        this.entregado = entregado;
    }

    public List<ArticuloRemitoDTO> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<ArticuloRemitoDTO> articulos) {
        this.articulos = articulos;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
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
}
