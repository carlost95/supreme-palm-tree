package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.UserDateAudit;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "detalle_remito")
public class DetalleRemito extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_remito", nullable = false)
    private Integer idDetalleRemito;
    @Basic
    @Column(name = "cantidad", nullable = false)
    private Double cantidad;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "id_remito", referencedColumnName = "id_remito", nullable = false)
    private Remito remito;
    @ManyToOne
    @JoinColumn(name = "id_articulo", referencedColumnName = "id_articulo", nullable = false)
    private Articulo articulo;

    public Integer getIdDetalleRemito() {
        return idDetalleRemito;
    }

    public void setIdDetalleRemito(Integer idDetalleRemito) {
        this.idDetalleRemito = idDetalleRemito;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Remito getRemito() {
        return remito;
    }

    public void setRemito(Remito remito) {
        this.remito = remito;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleRemito that = (DetalleRemito) o;
        return Objects.equals(idDetalleRemito, that.idDetalleRemito) && Objects.equals(cantidad, that.cantidad) && Objects.equals(fecha, that.fecha) && Objects.equals(remito, that.remito) && Objects.equals(articulo, that.articulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetalleRemito, cantidad, fecha, remito, articulo);
    }
}
