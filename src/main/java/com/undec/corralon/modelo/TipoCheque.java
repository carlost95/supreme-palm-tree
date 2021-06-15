package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tipo_cheque")
public class TipoCheque extends DateAudit{
    private Integer idTipoCheque;
    private String nombre;
    private String descripcion;
    private Boolean habilitado;
    private List<Cheque> chequesByIdTipoCheque;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
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

//    @OneToMany(mappedBy = "tipoChequeByIdTipoCheque")
//    public List<Cheque> getChequesByIdTipoCheque() {
//        return chequesByIdTipoCheque;
//    }
//
//    public void setChequesByIdTipoCheque(List<Cheque> chequesByIdTipoCheque) {
//        this.chequesByIdTipoCheque = chequesByIdTipoCheque;
//    }
}
