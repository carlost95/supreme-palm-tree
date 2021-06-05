package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Tarjeta {
    private Integer idTarjeta;
    private String nombre;
    private String abreviatura;
    private Integer idTipoTarjeta;
    private Collection<PagoVenta> pagoVentasByIdTarjeta;
    private Tipotarjeta tipotarjetaByIdTipoTarjeta;

    @Id
    @Column(name = "id_tarjeta")
    public Integer getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(Integer idTarjeta) {
        this.idTarjeta = idTarjeta;
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
    @Column(name = "id_tipo_tarjeta")
    public Integer getIdTipoTarjeta() {
        return idTipoTarjeta;
    }

    public void setIdTipoTarjeta(Integer idTipoTarjeta) {
        this.idTipoTarjeta = idTipoTarjeta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarjeta tarjeta = (Tarjeta) o;
        return Objects.equals(idTarjeta, tarjeta.idTarjeta) &&
                Objects.equals(nombre, tarjeta.nombre) &&
                Objects.equals(abreviatura, tarjeta.abreviatura) &&
                Objects.equals(idTipoTarjeta, tarjeta.idTipoTarjeta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTarjeta, nombre, abreviatura, idTipoTarjeta);
    }

    @OneToMany(mappedBy = "tarjetaByIdTarjeta")
    public Collection<PagoVenta> getPagoVentasByIdTarjeta() {
        return pagoVentasByIdTarjeta;
    }

    public void setPagoVentasByIdTarjeta(Collection<PagoVenta> pagoVentasByIdTarjeta) {
        this.pagoVentasByIdTarjeta = pagoVentasByIdTarjeta;
    }

    @ManyToOne
    @JoinColumn(name = "id_tipo_tarjeta", referencedColumnName = "id_tipo_tarjeta", nullable = false)
    public Tipotarjeta getTipotarjetaByIdTipoTarjeta() {
        return tipotarjetaByIdTipoTarjeta;
    }

    public void setTipotarjetaByIdTipoTarjeta(Tipotarjeta tipotarjetaByIdTipoTarjeta) {
        this.tipotarjetaByIdTipoTarjeta = tipotarjetaByIdTipoTarjeta;
    }
}
