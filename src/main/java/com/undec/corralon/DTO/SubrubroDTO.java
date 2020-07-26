package com.undec.corralon.DTO;



public class SubrubroDTO {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer habilitacion;
    private Integer rubroId;

    public SubrubroDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getHabilitacion() {
        return habilitacion;
    }

    public void setHabilitacion(Integer habilitacion) {
        this.habilitacion = habilitacion;
    }

    public Integer getRubroId() {
        return rubroId;
    }

    public void setRubroId(Integer rubroId) {
        this.rubroId = rubroId;
    }
}
