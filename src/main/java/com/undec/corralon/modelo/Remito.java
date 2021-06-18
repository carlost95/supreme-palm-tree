package com.undec.corralon.modelo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Remito extends DateAudit{
    private Integer idRemito;
    private Timestamp fechaRemito;
    private String estadoRemito;
    private Integer idDetalleRemito;
    private Boolean habilitado;
    private Venta remitosByIdVenta;
//    private List<DetalleRemito> detalleRemitosByIdRemito;
//    private List<MovimientoArticulo> movimientoArticulosByIdRemito;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_remito")
    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

    @Basic
    @Column(name = "fechaRemito")
    public Timestamp getFechaRemito() {
        return fechaRemito;
    }

    public void setFechaRemito(Timestamp fechaRemito) {
        this.fechaRemito = fechaRemito;
    }

    @Basic
    @Column(name = "estadoRemito")
    public String getEstadoRemito() {
        return estadoRemito;
    }

    public void setEstadoRemito(String estadoRemito) {
        this.estadoRemito = estadoRemito;
    }

    @Basic
    @Column(name = "idDetalleRemito")
    public Integer getIdDetalleRemito() {
        return idDetalleRemito;
    }

    public void setIdDetalleRemito(Integer idDetalleRemito) {
        this.idDetalleRemito = idDetalleRemito;
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
        Remito remito = (Remito) o;
        return Objects.equals(idRemito, remito.idRemito) &&
                Objects.equals(fechaRemito, remito.fechaRemito) &&
                Objects.equals(estadoRemito, remito.estadoRemito) &&
                Objects.equals(idDetalleRemito, remito.idDetalleRemito) &&
                Objects.equals(habilitado, remito.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRemito, fechaRemito, estadoRemito, idDetalleRemito, habilitado);
    }

//    @OneToMany(mappedBy = "remitoByIdRemito")
//    public List<DetalleRemito> getDetalleRemitosByIdRemito() {
//        return detalleRemitosByIdRemito;
//    }
//
//    public void setDetalleRemitosByIdRemito(List<DetalleRemito> detalleRemitosByIdRemito) {
//        this.detalleRemitosByIdRemito = detalleRemitosByIdRemito;
//    }
//
//    @OneToMany(mappedBy = "remitoByIdRemito")
//    public List<MovimientoArticulo> getMovimientoArticulosByIdRemito() {
//        return movimientoArticulosByIdRemito;
//    }
//
//    public void setMovimientoArticulosByIdRemito(List<MovimientoArticulo> movimientoArticulosByIdRemito) {
//        this.movimientoArticulosByIdRemito = movimientoArticulosByIdRemito;
//    }
//
    @ManyToOne
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta", nullable = false)
    public Venta getRemitosByIdVenta() {
        return remitosByIdVenta;
    }

    public void setRemitosByIdVenta(Venta remitosByIdVenta) {
        this.remitosByIdVenta = remitosByIdVenta;
    }
}
