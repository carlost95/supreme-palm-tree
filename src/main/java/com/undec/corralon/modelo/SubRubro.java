package com.undec.corralon.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "sub_rubro", schema = "santo_domingo_corralon")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SubRubro {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer habilitacion;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Date fechaBaja;
    private Rubro rubroId;

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

    @ManyToOne
    @JoinColumn(name = "rubro_id", referencedColumnName = "id")
    public Rubro getRubroId() {
        return rubroId;
    }

    public void setRubroId(Rubro rubroId) {
        this.rubroId = rubroId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubRubro subRubro = (SubRubro) o;
        return Objects.equals(id, subRubro.id) &&
                Objects.equals(nombre, subRubro.nombre) &&
                Objects.equals(descripcion, subRubro.descripcion) &&
                Objects.equals(habilitacion, subRubro.habilitacion) &&
                Objects.equals(fechaCreacion, subRubro.fechaCreacion) &&
                Objects.equals(fechaModificacion, subRubro.fechaModificacion) &&
                Objects.equals(fechaBaja, subRubro.fechaBaja) &&
                Objects.equals(rubroId, subRubro.rubroId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, habilitacion, fechaCreacion, fechaModificacion, fechaBaja, rubroId);
    }
}
