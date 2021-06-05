package com.undec.corralon.modelo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Remito {
    private Integer idRemito;
    private Timestamp fechaRemito;
    private String estadoRemito;
    private Integer idVenta;
    private Integer idDetalleRemito;
    private Collection<DetalleRemito> detalleRemitosByIdRemito;
    private Collection<MovimientoArticulo> movimientoArticulosByIdRemito;
    private Venta ventaByIdVenta;

    @Id
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
    @Column(name = "id_venta")
    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    @Basic
    @Column(name = "idDetalleRemito")
    public Integer getIdDetalleRemito() {
        return idDetalleRemito;
    }

    public void setIdDetalleRemito(Integer idDetalleRemito) {
        this.idDetalleRemito = idDetalleRemito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Remito remito = (Remito) o;
        return Objects.equals(idRemito, remito.idRemito) &&
                Objects.equals(fechaRemito, remito.fechaRemito) &&
                Objects.equals(estadoRemito, remito.estadoRemito) &&
                Objects.equals(idVenta, remito.idVenta) &&
                Objects.equals(idDetalleRemito, remito.idDetalleRemito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRemito, fechaRemito, estadoRemito, idVenta, idDetalleRemito);
    }

    @OneToMany(mappedBy = "remitoByIdRemito")
    public Collection<DetalleRemito> getDetalleRemitosByIdRemito() {
        return detalleRemitosByIdRemito;
    }

    public void setDetalleRemitosByIdRemito(Collection<DetalleRemito> detalleRemitosByIdRemito) {
        this.detalleRemitosByIdRemito = detalleRemitosByIdRemito;
    }

    @OneToMany(mappedBy = "remitoByIdRemito")
    public Collection<MovimientoArticulo> getMovimientoArticulosByIdRemito() {
        return movimientoArticulosByIdRemito;
    }

    public void setMovimientoArticulosByIdRemito(Collection<MovimientoArticulo> movimientoArticulosByIdRemito) {
        this.movimientoArticulosByIdRemito = movimientoArticulosByIdRemito;
    }

    @ManyToOne
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta", nullable = false)
    public Venta getVentaByIdVenta() {
        return ventaByIdVenta;
    }

    public void setVentaByIdVenta(Venta ventaByIdVenta) {
        this.ventaByIdVenta = ventaByIdVenta;
    }
}
