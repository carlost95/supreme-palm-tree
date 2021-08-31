package com.undec.corralon.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class PedidoDTO {
    private int idPedidoDto;
    private String nombre;
    private String fecha;
    private String descripcion;
    List<DetallePedidoDTO> detallesPedido;

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

    public List<DetallePedidoDTO> getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(List<DetallePedidoDTO> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }
}
