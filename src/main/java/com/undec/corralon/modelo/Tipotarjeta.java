package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Tipotarjeta {
    private Integer idTipoTarjeta;
    private String nombreTipo;
    private Byte habilitado;
    private Collection<Tarjeta> tarjetasByIdTipoTarjeta;

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
    public Collection<Tarjeta> getTarjetasByIdTipoTarjeta() {
        return tarjetasByIdTipoTarjeta;
    }

    public void setTarjetasByIdTipoTarjeta(Collection<Tarjeta> tarjetasByIdTipoTarjeta) {
        this.tarjetasByIdTipoTarjeta = tarjetasByIdTipoTarjeta;
    }
}
