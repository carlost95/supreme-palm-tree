package com.undec.corralon.modelo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "costo_articulo", schema = "corralon_dev", catalog = "")
public class CostoArticulo {
    private Integer idCosto;
    private Double costo;
    private Timestamp fechaDesde;
    private Timestamp fechaHasta;
    private Integer idArticulo;
    private Articulo articuloByIdArticulo;

    @Id
    @Column(name = "id_costo")
    public Integer getIdCosto() {
        return idCosto;
    }

    public void setIdCosto(Integer idCosto) {
        this.idCosto = idCosto;
    }

    @Basic
    @Column(name = "costo")
    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    @Basic
    @Column(name = "fecha_desde")
    public Timestamp getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Timestamp fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    @Basic
    @Column(name = "fecha_hasta")
    public Timestamp getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Timestamp fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    @Basic
    @Column(name = "id_articulo")
    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CostoArticulo that = (CostoArticulo) o;
        return Objects.equals(idCosto, that.idCosto) &&
                Objects.equals(costo, that.costo) &&
                Objects.equals(fechaDesde, that.fechaDesde) &&
                Objects.equals(fechaHasta, that.fechaHasta) &&
                Objects.equals(idArticulo, that.idArticulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCosto, costo, fechaDesde, fechaHasta, idArticulo);
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