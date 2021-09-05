package com.undec.corralon.DTO;

import java.util.List;

public class PedidoDTO {
    private int idPedidoDto;
    private String nombre;
    private String fecha;
    private String descripcion;
    List<DetalleTipoMovimientoDTO> detallesPedido;

    public int getIdPedidoDto() {
        return idPedidoDto;
    }

    public void setIdPedidoDto(int idPedidoDto) {
        this.idPedidoDto = idPedidoDto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<DetalleTipoMovimientoDTO> getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(List<DetalleTipoMovimientoDTO> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }
}
