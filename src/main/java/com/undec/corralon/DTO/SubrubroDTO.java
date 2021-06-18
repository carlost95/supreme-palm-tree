package com.undec.corralon.DTO;



public class SubrubroDTO {

    private Integer idSubRubro;
    private String nombre;
    private String descripcion;
    private Boolean habilitado;
    private Integer rubroId;

    public SubrubroDTO() {
    }

    public Integer getIdSubRubro() {
        return idSubRubro;
    }

    public void setIdSubRubro(Integer idSubRubro) {
        this.idSubRubro = idSubRubro;
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

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Integer getRubroId() {
        return rubroId;
    }

    public void setRubroId(Integer rubroId) {
        this.rubroId = rubroId;
    }
}
