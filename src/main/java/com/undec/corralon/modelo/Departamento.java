package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Departamento {
    private Integer idDepartamento;
    private String nombre;
    private String abreviatura;
    private Byte habilitado;
    private Collection<Distrito> distritosByIdDepartamento;

    @Id
    @Column(name = "id_departamento")
    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departamento that = (Departamento) o;
        return Objects.equals(idDepartamento, that.idDepartamento) &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(abreviatura, that.abreviatura) &&
                Objects.equals(habilitado, that.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDepartamento, nombre, abreviatura, habilitado);
    }

    @OneToMany(mappedBy = "departamentoByIdDepartamento")
    public Collection<Distrito> getDistritosByIdDepartamento() {
        return distritosByIdDepartamento;
    }

    public void setDistritosByIdDepartamento(Collection<Distrito> distritosByIdDepartamento) {
        this.distritosByIdDepartamento = distritosByIdDepartamento;
    }
}
