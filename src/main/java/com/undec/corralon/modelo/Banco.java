package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.DateAudit;
import com.undec.corralon.modelo.audit.UserDateAudit;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Banco extends UserDateAudit {
    private Integer idBanco;
    private String nombre;
    private String abreviatura;
    private Boolean habilitado;

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

}
