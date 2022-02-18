package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.UserDateAudit;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Tipotarjeta extends UserDateAudit {
    private Integer idTipoTarjeta;
    private String nombreTipo;
    private Boolean habilitado;

    @Id
    @Column(name = "id_tipo_tarjeta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdTipoTarjeta() {
        return idTipoTarjeta;
    }

    public void setIdTipoTarjeta(Integer idTipoTarjeta) {
        this.idTipoTarjeta = idTipoTarjeta;
    }

    @Basic
    @Column(name = "nombre_tipo")
    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
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
        Tipotarjeta that = (Tipotarjeta) o;
        return Objects.equals(idTipoTarjeta, that.idTipoTarjeta) &&
                Objects.equals(nombreTipo, that.nombreTipo) &&
                Objects.equals(habilitado, that.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipoTarjeta, nombreTipo, habilitado);
    }

}
