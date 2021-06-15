package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Tarjeta extends DateAudit{
    private Integer idTarjeta;
    private String nombre;
    private String abreviatura;
    private Boolean habilitado;
//    private List<PagoVenta> pagoVentasByIdTarjeta;
    private Tipotarjeta tipotarjetaByIdTipoTarjeta;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        Tarjeta tarjeta = (Tarjeta) o;
        return Objects.equals(idTarjeta, tarjeta.idTarjeta) &&
                Objects.equals(nombre, tarjeta.nombre) &&
                Objects.equals(abreviatura, tarjeta.abreviatura) &&
                Objects.equals(habilitado, tarjeta.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTarjeta, nombre, abreviatura, habilitado);
    }

//    @OneToMany(mappedBy = "tarjetaByIdTarjeta")
//    public List<PagoVenta> getPagoVentasByIdTarjeta() {
//        return pagoVentasByIdTarjeta;
//    }
//
//    public void setPagoVentasByIdTarjeta(List<PagoVenta> pagoVentasByIdTarjeta) {
//        this.pagoVentasByIdTarjeta = pagoVentasByIdTarjeta;
//    }
//
    @ManyToOne
    @JoinColumn(name = "id_tipo_tarjeta", referencedColumnName = "id_tipo_tarjeta", nullable = false)
    public Tipotarjeta getTipotarjetaByIdTipoTarjeta() {
        return tipotarjetaByIdTipoTarjeta;
    }

    public void setTipotarjetaByIdTipoTarjeta(Tipotarjeta tipotarjetaByIdTipoTarjeta) {
        this.tipotarjetaByIdTipoTarjeta = tipotarjetaByIdTipoTarjeta;
    }
}
