package com.undec.corralon.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovimientoArticuloDTO {

    @JsonProperty(value = "idMovimientoArticulo", required = false)
    private Integer idMovimientoArticulo;
    private String fecha;
    private Integer movimiento;
    private Integer devolucion;
    private Integer idArticulo;
    private Integer idDetalleAjuste;
    private Integer idDetallePedido;
    private Integer idDetalleRemito;
    private Integer idDetalleVenta;

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

    public Integer getIdDetalleAjuste() {
        return idDetalleAjuste;
    }

    public void setIdDetalleAjuste(Integer idDetalleAjuste) {
        this.idDetalleAjuste = idDetalleAjuste;
    }

    public Integer getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(Integer idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public Integer getIdDetalleRemito() {
        return idDetalleRemito;
    }

    public void setIdDetalleRemito(Integer idDetalleRemito) {
        this.idDetalleRemito = idDetalleRemito;
    }

    public Integer getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(Integer idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }
}
