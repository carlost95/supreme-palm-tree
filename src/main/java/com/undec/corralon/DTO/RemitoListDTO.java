package com.undec.corralon.DTO;

import com.undec.corralon.modelo.Cliente;
import com.undec.corralon.modelo.Direccion;
import com.undec.corralon.modelo.Venta;

import java.util.Date;

public class RemitoListDTO {
    private Integer idRemito;
    private Long nroRemito;
    private Date fechaRemito;
    private Boolean entregado;
    private Direccion direccion;
    private Cliente cliente;

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
}