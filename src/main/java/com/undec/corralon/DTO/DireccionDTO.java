package com.undec.corralon.DTO;

import java.time.LocalDate;

public class DireccionDTO {
    private Integer id;
    private String calle;
    private String numerocalle;
    private String barrio;
    private String entrecalles;
    private String descripcion;
    private String ubicacion;
    private Integer habilitado;
    private LocalDate fechaalta;
    private LocalDate fechaactualizacion;
    private LocalDate fechabaja;
    private Integer clienteByFkclientesid;
    private Integer distritoByFkdistritosid;
    private Integer tipodireccionByFktipodireccionesid;

    public DireccionDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumerocalle() {
        return numerocalle;
    }

    public void setNumerocalle(String numerocalle) {
        this.numerocalle = numerocalle;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getEntrecalles() {
        return entrecalles;
    }

    public void setEntrecalles(String entrecalles) {
        this.entrecalles = entrecalles;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Integer habilitado) {
        this.habilitado = habilitado;
    }

    public LocalDate getFechaalta() {
        return fechaalta;
    }

    public void setFechaalta(LocalDate fechaalta) {
        this.fechaalta = fechaalta;
    }

    public LocalDate getFechaactualizacion() {
        return fechaactualizacion;
    }

    public void setFechaactualizacion(LocalDate fechaactualizacion) {
        this.fechaactualizacion = fechaactualizacion;
    }

    public LocalDate getFechabaja() {
        return fechabaja;
    }

    public void setFechabaja(LocalDate fechabaja) {
        this.fechabaja = fechabaja;
    }

    public Integer getClienteByFkclientesid() {
        return clienteByFkclientesid;
    }

    public void setClienteByFkclientesid(Integer clienteByFkclientesid) {
        this.clienteByFkclientesid = clienteByFkclientesid;
    }

    public Integer getDistritoByFkdistritosid() {
        return distritoByFkdistritosid;
    }

    public void setDistritoByFkdistritosid(Integer distritoByFkdistritosid) {
        this.distritoByFkdistritosid = distritoByFkdistritosid;
    }

    public Integer getTipodireccionByFktipodireccionesid() {
        return tipodireccionByFktipodireccionesid;
    }

    public void setTipodireccionByFktipodireccionesid(Integer tipodireccionByFktipodireccionesid) {
        this.tipodireccionByFktipodireccionesid = tipodireccionByFktipodireccionesid;
    }
}
