package com.undec.corralon.DTO;

import java.util.Date;
import java.util.List;

public class AjusteDTO {
    private int id;
    private String nombre;
    private Date fecha;
    private String descripcion;
    private Integer idProveedor;
    private List<ArticuloStockDTO> articulos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public List<ArticuloStockDTO> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<ArticuloStockDTO> articulos) {
        this.articulos = articulos;
    }
}
