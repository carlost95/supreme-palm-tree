package com.undec.corralon.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "forma_pago", schema = "santo_domingo_corralon")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FormaPago {
    private int id;
    private double aumento;
    private String descripcion;
    private String nombre;
    private int habilitacion;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Date fechaBaja;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "aumento")
    public double getAumento() {
        return aumento;
    }

    public void setAumento(double aumento) {
        this.aumento = aumento;
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
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "habilitacion")
    public int getHabilitacion() {
        return habilitacion;
    }

    public void setHabilitacion(int habilitacion) {
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
        FormaPago formaPago = (FormaPago) o;
        return id == formaPago.id &&
                Double.compare(formaPago.aumento, aumento) == 0 &&
                habilitacion == formaPago.habilitacion &&
                Objects.equals(descripcion, formaPago.descripcion) &&
                Objects.equals(nombre, formaPago.nombre) &&
                Objects.equals(fechaCreacion, formaPago.fechaCreacion) &&
                Objects.equals(fechaModificacion, formaPago.fechaModificacion) &&
                Objects.equals(fechaBaja, formaPago.fechaBaja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, aumento, descripcion, nombre, habilitacion, fechaCreacion, fechaModificacion, fechaBaja);
    }
}
