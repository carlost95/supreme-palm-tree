package com.undec.corralon.modelo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "detalle_ajuste")
public class DetalleAjuste extends DateAudit{
    private Integer idDetalleAjuste;
    private Double cantidad;
    private String fecha;
    private Ajuste ajusteByIdAjuste;
    private Articulo articuloByIdArticulo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_ajuste")
    public Integer getIdDetalleAjuste() {
        return idDetalleAjuste;
    }

    public void setIdDetalleAjuste(Integer idDetalleAjuste) {
        this.idDetalleAjuste = idDetalleAjuste;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleAjuste that = (DetalleAjuste) o;
        return Objects.equals(idDetalleAjuste, that.idDetalleAjuste) &&
                Objects.equals(cantidad, that.cantidad) &&
                Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetalleAjuste, cantidad, fecha);
    }

    @ManyToOne
    @JoinColumn(name = "id_ajuste", referencedColumnName = "id_ajuste", nullable = false)
    public Ajuste getAjusteByIdAjuste() {
        return ajusteByIdAjuste;
    }

    public void setAjusteByIdAjuste(Ajuste ajusteByIdAjuste) {
        this.ajusteByIdAjuste = ajusteByIdAjuste;
    }

    @ManyToOne
    @JoinColumn(name = "id_articulo", referencedColumnName = "id_articulo", nullable = false)
    public Articulo getArticuloByIdArticulo() {
        return articuloByIdArticulo;
    }

    public void setArticuloByIdArticulo(Articulo articuloByIdArticulo) {
        this.articuloByIdArticulo = articuloByIdArticulo;
    }
}
