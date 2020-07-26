package com.undec.corralon.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Distrito {
    private Integer id;
    private String nombre;
    private String abreviatura;
    private Integer habilitado;
    private LocalDate fechaalta;
    private LocalDate fechaactualizacion;
    private LocalDate fechabaja;
    private Departamento departamentoByFkdepartamentosid;

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
    @Column(name = "abreviatura")
    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    @Basic
    @Column(name = "habilitado")
    public Integer getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Integer habilitado) {
        this.habilitado = habilitado;
    }

    @Basic
    @Column(name = "fechaalta")
    public LocalDate getFechaalta() {
        return fechaalta;
    }

    public void setFechaalta(LocalDate fechaalta) {
        this.fechaalta = fechaalta;
    }

    @Basic
    @Column(name = "fechaactualizacion")
    public LocalDate getFechaactualizacion() {
        return fechaactualizacion;
    }

    public void setFechaactualizacion(LocalDate fechaactualizacion) {
        this.fechaactualizacion = fechaactualizacion;
    }

    @Basic
    @Column(name = "fechabaja")
    public LocalDate getFechabaja() {
        return fechabaja;
    }

    public void setFechabaja(LocalDate fechabaja) {
        this.fechabaja = fechabaja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distrito distrito = (Distrito) o;
        return Objects.equals(id, distrito.id) &&
                Objects.equals(nombre, distrito.nombre) &&
                Objects.equals(abreviatura, distrito.abreviatura) &&
                Objects.equals(habilitado, distrito.habilitado) &&
                Objects.equals(fechaalta, distrito.fechaalta) &&
                Objects.equals(fechaactualizacion, distrito.fechaactualizacion) &&
                Objects.equals(fechabaja, distrito.fechabaja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, abreviatura, habilitado, fechaalta, fechaactualizacion, fechabaja);
    }


    @ManyToOne
    @JoinColumn(name = "fkdepartamentosid", referencedColumnName = "id")
    public Departamento getDepartamentoByFkdepartamentosid() {
        return departamentoByFkdepartamentosid;
    }

    public void setDepartamentoByFkdepartamentosid(Departamento departamentoByFkdepartamentosid) {
        this.departamentoByFkdepartamentosid = departamentoByFkdepartamentosid;
    }
}
