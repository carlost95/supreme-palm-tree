package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Tipotarjeta extends DateAudit{
    private Integer idTipoTarjeta;
    private String nombreTipo;
    private boolean habilitado;
    private List<Tarjeta> tarjetasByIdTipoTarjeta;

    @Id
    @Column(name = "id_tipo_tarjeta")
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
        Tipotarjeta that = (Tipotarjeta) o;
        return Objects.equals(idTipoTarjeta, that.idTipoTarjeta) &&
                Objects.equals(nombreTipo, that.nombreTipo) &&
                Objects.equals(habilitado, that.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipoTarjeta, nombreTipo, habilitado);
    }

    @OneToMany(mappedBy = "tipotarjetaByIdTipoTarjeta")
    public List<Tarjeta> getTarjetasByIdTipoTarjeta() {
        return tarjetasByIdTipoTarjeta;
    }

    public void setTarjetasByIdTipoTarjeta(List<Tarjeta> tarjetasByIdTipoTarjeta) {
        this.tarjetasByIdTipoTarjeta = tarjetasByIdTipoTarjeta;
    }
}
