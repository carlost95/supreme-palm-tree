package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Banco extends DateAudit{
    private Integer idBanco;
    private String nombre;
    private String abreviatura;
    private boolean habilitado;
    private List<BancoProveedor> bancoProveedorsByIdBanco;
    private List<Cheque> chequesByIdBanco;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_banco")
    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banco banco = (Banco) o;
        return Objects.equals(idBanco, banco.idBanco) &&
                Objects.equals(nombre, banco.nombre) &&
                Objects.equals(abreviatura, banco.abreviatura) &&
                Objects.equals(habilitado, banco.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBanco, nombre, abreviatura, habilitado);
    }

    @OneToMany(mappedBy = "bancoByIdBanco")
    public List<BancoProveedor> getBancoProveedorsByIdBanco() {
        return bancoProveedorsByIdBanco;
    }

    public void setBancoProveedorsByIdBanco(List<BancoProveedor> bancoProveedorsByIdBanco) {
        this.bancoProveedorsByIdBanco = bancoProveedorsByIdBanco;
    }

    @OneToMany(mappedBy = "bancoByIdBanco")
    public List<Cheque> getChequesByIdBanco() {
        return chequesByIdBanco;
    }

    public void setChequesByIdBanco(List<Cheque> chequesByIdBanco) {
        this.chequesByIdBanco = chequesByIdBanco;
    }
}
