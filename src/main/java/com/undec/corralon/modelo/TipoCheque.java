package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "tipo_cheque", schema = "corralon_dev", catalog = "")
public class TipoCheque {
    private Integer idTipoCheque;
    private String nombre;
    private String descripcion;
    private Integer habilitado;
    private Collection<Cheque> chequesByIdTipoCheque;

    @Id
    @Column(name = "id_tipo_cheque")
    public Integer getIdTipoCheque() {
        return idTipoCheque;
    }

    public void setIdTipoCheque(Integer idTipoCheque) {
        this.idTipoCheque = idTipoCheque;
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
    @Column(name = "habilitado")
    public Integer getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Integer habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoCheque that = (TipoCheque) o;
        return Objects.equals(idTipoCheque, that.idTipoCheque) &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(descripcion, that.descripcion) &&
                Objects.equals(habilitado, that.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipoCheque, nombre, descripcion, habilitado);
    }

    @OneToMany(mappedBy = "tipoChequeByIdTipoCheque")
    public Collection<Cheque> getChequesByIdTipoCheque() {
        return chequesByIdTipoCheque;
    }

    public void setChequesByIdTipoCheque(Collection<Cheque> chequesByIdTipoCheque) {
        this.chequesByIdTipoCheque = chequesByIdTipoCheque;
    }
}
