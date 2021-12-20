package com.undec.corralon.DTO;

import java.util.Date;
import java.util.List;

public class VentaDTO {
    private int idVentaDTO;
    private Date fecha;
    private Integer idCliente;
    private Double descuento;
    private Double recargo;
    private Double totalPagar;
    List<DetalleTipoMovimientoDTO> detalleVenta;

    public int getIdVentaDTO() {
        return idVentaDTO;
    }

    public void setIdVentaDTO(int idVentaDTO) {
        this.idVentaDTO = idVentaDTO;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getRecargo() {
        return recargo;
    }

    public void setRecargo(Double recargo) {
        this.recargo = recargo;
    }

    public Double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public List<DetalleTipoMovimientoDTO> getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(List<DetalleTipoMovimientoDTO> detalleVenta) {
        this.detalleVenta = detalleVenta;
    }
}
