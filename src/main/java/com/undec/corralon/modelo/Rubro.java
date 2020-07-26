package com.undec.corralon.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Rubro {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer habilitacion;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Date fechaBaja;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "habilitacion")
    public Integer getHabilitacion() {
        return habilitacion;
    }

    public void setHabilitacion(Integer habilitacion) {
        this.habilitacion = habilitacion;
    }

    @Basic
    @Column(name = "fecha_creacion")
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Basic
    @Column(name = "fecha_modificacion")
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Basic
    @Column(name = "fecha_baja")
    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rubro rubro = (Rubro) o;
        return Objects.equals(id, rubro.id) &&
                Objects.equals(nombre, rubro.nombre) &&
                Objects.equals(descripcion, rubro.descripcion) &&
                Objects.equals(habilitacion, rubro.habilitacion) &&
                Objects.equals(fechaCreacion, rubro.fechaCreacion) &&
                Objects.equals(fechaModificacion, rubro.fechaModificacion) &&
                Objects.equals(fechaBaja, rubro.fechaBaja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, habilitacion, fechaCreacion, fechaModificacion, fechaBaja);
    }
}
