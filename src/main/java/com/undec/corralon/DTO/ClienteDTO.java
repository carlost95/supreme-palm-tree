package com.undec.corralon.DTO;

import java.util.List;

public class ClienteDTO {

    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private Boolean habilitacion;
    private List<DireccionDTO> direcciones;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Boolean getHabilitacion() {
        return habilitacion;
    }

    public void setHabilitacion(Boolean habilitacion) {
        this.habilitacion = habilitacion;
    }

    public List<DireccionDTO> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionDTO> direcciones) {
        this.direcciones = direcciones;
    }
}
