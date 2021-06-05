package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Distrito {
    private Integer idDistrito;
    private String nombre;
    private String abreviatura;
    private Byte habilitado;
    private Integer idDepartamento;
    private Collection<Direccion> direccionsByIdDistrito;
    private Departamento departamentoByIdDepartamento;

    @Id
    @Column(name = "id_distrito")
    public Integer getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(Integer idDistrito) {
        this.idDistrito = idDistrito;
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
    public Byte getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Byte habilitado) {
        this.habilitado = habilitado;
    }

    @Basic
    @Column(name = "id_departamento")
    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distrito distrito = (Distrito) o;
        return Objects.equals(idDistrito, distrito.idDistrito) &&
                Objects.equals(nombre, distrito.nombre) &&
                Objects.equals(abreviatura, distrito.abreviatura) &&
                Objects.equals(habilitado, distrito.habilitado) &&
                Objects.equals(idDepartamento, distrito.idDepartamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDistrito, nombre, abreviatura, habilitado, idDepartamento);
    }

    @OneToMany(mappedBy = "distritoByIdDistrito")
    public Collection<Direccion> getDireccionsByIdDistrito() {
        return direccionsByIdDistrito;
    }

    public void setDireccionsByIdDistrito(Collection<Direccion> direccionsByIdDistrito) {
        this.direccionsByIdDistrito = direccionsByIdDistrito;
    }

    @ManyToOne
    @JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento", nullable = false)
    public Departamento getDepartamentoByIdDepartamento() {
        return departamentoByIdDepartamento;
    }

    public void setDepartamentoByIdDepartamento(Departamento departamentoByIdDepartamento) {
        this.departamentoByIdDepartamento = departamentoByIdDepartamento;
    }
}
