package com.undec.corralon.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class MovimientoArticuloDTO {

    @JsonProperty(value = "id", required = false)
    private Integer id;
    private String fecha;
    private Integer movimiento;
    private Integer articuloId;
    private Integer ajusteId;
    private Integer pedidoId;

    public MovimientoArticuloDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Integer movimiento) {
        this.movimiento = movimiento;
    }

    public Integer getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(Integer articuloId) {
        this.articuloId = articuloId;
    }

    public Integer getAjusteId() {
        return ajusteId;
    }

    public void setAjusteId(Integer ajusteId) {
        this.ajusteId = ajusteId;
    }

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }
}
