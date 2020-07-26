package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Tarjeta {
    private Integer id;
    private String nombre;
    private Integer tipoTarjetaId;
    private Integer bancoId;
    private TipoTarjeta tipoTarjetaByTipoTarjetaId;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "tipo_tarjeta_id")
    public Integer getTipoTarjetaId() {
        return tipoTarjetaId;
    }

    public void setTipoTarjetaId(Integer tipoTarjetaId) {
        this.tipoTarjetaId = tipoTarjetaId;
    }

    @Basic
    @Column(name = "banco_id")
    public Integer getBancoId() {
        return bancoId;
    }

    public void setBancoId(Integer bancoId) {
        this.bancoId = bancoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarjeta tarjeta = (Tarjeta) o;
        return Objects.equals(id, tarjeta.id) &&
                Objects.equals(nombre, tarjeta.nombre) &&
                Objects.equals(tipoTarjetaId, tarjeta.tipoTarjetaId) &&
                Objects.equals(bancoId, tarjeta.bancoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, tipoTarjetaId, bancoId);
    }

//    @ManyToOne
//    @JoinColumn(name = "tipo_tarjeta_id", referencedColumnName = "id", nullable = false)
//    public TipoTarjeta getTipoTarjetaByTipoTarjetaId() {
//        return tipoTarjetaByTipoTarjetaId;
//    }
//
//    public void setTipoTarjetaByTipoTarjetaId(TipoTarjeta tipoTarjetaByTipoTarjetaId) {
//        this.tipoTarjetaByTipoTarjetaId = tipoTarjetaByTipoTarjetaId;
//    }
}
