package com.undec.corralon.modelo;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "detalle_remito")
public class DetalleRemito extends DateAudit{
    private Integer idDetalleRemito;
    private Double cantidad;
    private String fecha;
    private Integer idArticulo;
    private Integer idRemito;
    private Articulo articuloByIdArticulo;
    private Remito remitoByIdRemito;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_remito")
    public Integer getIdDetalleRemito() {
        return idDetalleRemito;
    }

    public void setIdDetalleRemito(Integer idDetalleRemito) {
        this.idDetalleRemito = idDetalleRemito;
    }

    @Basic
    @Column(name = "cantidad")
    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    @Basic
    @Column(name = "fecha")
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "id_articulo")
    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    @Basic
    @Column(name = "id_remito")
    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleRemito that = (DetalleRemito) o;
        return Objects.equals(idDetalleRemito, that.idDetalleRemito) &&
                Objects.equals(cantidad, that.cantidad) &&
                Objects.equals(fecha, that.fecha) &&
                Objects.equals(idArticulo, that.idArticulo) &&
                Objects.equals(idRemito, that.idRemito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetalleRemito, cantidad, fecha, idArticulo, idRemito);
    }

    @ManyToOne
    @JoinColumn(name = "id_articulo", referencedColumnName = "id_articulo", nullable = false)
    public Articulo getArticuloByIdArticulo() {
        return articuloByIdArticulo;
    }

    public void setArticuloByIdArticulo(Articulo articuloByIdArticulo) {
        this.articuloByIdArticulo = articuloByIdArticulo;
    }

    @ManyToOne
    @JoinColumn(name = "id_remito", referencedColumnName = "id_remito", nullable = false)
    public Remito getRemitoByIdRemito() {
        return remitoByIdRemito;
    }

    public void setRemitoByIdRemito(Remito remitoByIdRemito) {
        this.remitoByIdRemito = remitoByIdRemito;
    }
}
