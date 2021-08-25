package com.undec.corralon.DTO;

public class BancoRequest  {
    private  Integer idBanco;
    private String nombre;
    private String abreviatuira;
    private Boolean habilitado;

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatuira() {
        return abreviatuira;
    }

    public void setAbreviatuira(String abreviatuira) {
        this.abreviatuira = abreviatuira;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }
}
