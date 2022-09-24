package com.undec.corralon.DTO;

import java.util.Date;
import java.util.List;

public class PedidoDTO {
    private int idPedido;
    private String nombre;
    private Date fecha;
    private String descripcion;
    private Integer idProveedor;
    private List<ArticuloStockDTO> articulos;

    @Deprecated
    List<DetalleTipoMovimientoDTO> detallesPedido;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Deprecated
    public List<DetalleTipoMovimientoDTO> getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(List<DetalleTipoMovimientoDTO> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public List<ArticuloStockDTO> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<ArticuloStockDTO> articulos) {
        this.articulos = articulos;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }
}
