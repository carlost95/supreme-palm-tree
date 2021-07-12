package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.DateAudit;
import com.undec.corralon.modelo.audit.UserDateAudit;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Rubro extends UserDateAudit {
    private Integer idRubro;
    private String nombre;
    private String abreviatura;
    private Boolean habilitado;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rubro")
    public Integer getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(Integer idRubro) {
        this.idRubro = idRubro;
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
        Rubro rubro = (Rubro) o;
        return Objects.equals(idRubro, rubro.idRubro) &&
                Objects.equals(nombre, rubro.nombre) &&
                Objects.equals(abreviatura, rubro.abreviatura) &&
                Objects.equals(habilitado, rubro.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRubro, nombre, abreviatura, habilitado);
    }

}
