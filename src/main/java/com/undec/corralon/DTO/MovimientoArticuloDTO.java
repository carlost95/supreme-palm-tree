package com.undec.corralon.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class MovimientoArticuloDTO {

    @JsonProperty(value = "idMovimientoArticulo", required = false)
    private Integer idMovimientoArticulo;
    private String fecha;
    private Integer movimiento;
    private Integer devolucion;
    private Integer idArticulo;
    private Integer idAjuste;
    private Integer idPedido;
    private Integer idRemito;
    private Integer idVenta;

    public MovimientoArticuloDTO() {
    }

    public Integer getIdMovimientoArticulo() {
        return idMovimientoArticulo;
    }

    public void setIdMovimientoArticulo(Integer idMovimientoArticulo) {
        this.idMovimientoArticulo = idMovimientoArticulo;
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

    public Integer getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(Integer devolucion) {
        this.devolucion = devolucion;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Integer getIdAjuste() {
        return idAjuste;
    }

    public void setIdAjuste(Integer idAjuste) {
        this.idAjuste = idAjuste;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }
}
