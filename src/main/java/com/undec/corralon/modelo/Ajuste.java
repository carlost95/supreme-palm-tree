package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.UserDateAudit;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Ajuste  extends UserDateAudit {
    private Integer idAjuste;
    private String nombre;
    private String descripcion;
    private Boolean habilitado;
    private String fecha;

    @Id
    @Column(name = "id_ajuste")
    public Integer getIdAjuste() {
        return idAjuste;
    }

    public void setIdAjuste(Integer idAjuste) {
        this.idAjuste = idAjuste;
    }

    @Basic
    @Column(name = "nombre")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "habilitado")
    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Basic
    @Column(name = "fecha")
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ajuste ajuste = (Ajuste) o;
        return Objects.equals(idAjuste, ajuste.idAjuste) &&
                Objects.equals(nombre, ajuste.nombre) &&
                Objects.equals(descripcion, ajuste.descripcion) &&
                Objects.equals(habilitado, ajuste.habilitado) &&
                Objects.equals(fecha, ajuste.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAjuste, nombre, descripcion, habilitado, fecha);
    }
}
