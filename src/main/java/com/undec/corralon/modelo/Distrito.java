package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Distrito extends DateAudit{
    private Integer idDistrito;
    private String nombre;
    private String abreviatura;
    private boolean habilitado;
    private Integer idDepartamento;
    private List<Direccion> direccionsByIdDistrito;
    private Departamento departamentoByIdDepartamento;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
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
    public List<Direccion> getDireccionsByIdDistrito() {
        return direccionsByIdDistrito;
    }

    public void setDireccionsByIdDistrito(List<Direccion> direccionsByIdDistrito) {
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
