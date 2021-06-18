package com.undec.corralon.DTO;

public class PedidoDTO {
    private int idPedidoDto;
    private String nombre;
    private String fecha;
    private Integer idProveedor;
    private String razonSocial;
    private String descripcion;
    private Integer habilitado;

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

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Integer habilitado) {
        this.habilitado = habilitado;
    }
}
