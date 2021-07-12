package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.DateAudit;
import com.undec.corralon.modelo.audit.UserDateAudit;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "costo_articulo")
public class CostoArticulo extends UserDateAudit {
    private Integer idCosto;
    private Double costo;
    private String fechaDesde;
    private String fechaHasta;
    private Articulo articuloByIdArticulo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public String getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    @Basic
    @Column(name = "fecha_hasta")
    public String getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CostoArticulo that = (CostoArticulo) o;
        return Objects.equals(idCosto, that.idCosto) &&
                Objects.equals(costo, that.costo) &&
                Objects.equals(fechaDesde, that.fechaDesde) &&
                Objects.equals(fechaHasta, that.fechaHasta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCosto, costo, fechaDesde, fechaHasta);
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
