package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Tipotarjeta {
    private Integer idTipoTarjeta;
    private String nombreTipo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tipotarjeta that = (Tipotarjeta) o;
        return Objects.equals(idTipoTarjeta, that.idTipoTarjeta) &&
                Objects.equals(nombreTipo, that.nombreTipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipoTarjeta, nombreTipo);
    }

    @OneToMany(mappedBy = "tipotarjetaByIdTipoTarjeta")
    public Collection<Tarjeta> getTarjetasByIdTipoTarjeta() {
        return tarjetasByIdTipoTarjeta;
    }

    public void setTarjetasByIdTipoTarjeta(Collection<Tarjeta> tarjetasByIdTipoTarjeta) {
        this.tarjetasByIdTipoTarjeta = tarjetasByIdTipoTarjeta;
    }
}
