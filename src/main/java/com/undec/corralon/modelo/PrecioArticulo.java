package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.UserDateAudit;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "precio_articulo")
public class PrecioArticulo extends UserDateAudit {
    private Integer idPrecio;
    private Double precio;
    private String fechaDesde;
    private String fechaHasta;
    private Articulo articuloByIdArticulo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_precio")
    public Integer getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(Integer idPrecio) {
        this.idPrecio = idPrecio;
    }

    @Basic
    @Column(name = "precio")
    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
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
        PrecioArticulo that = (PrecioArticulo) o;
        return Objects.equals(idPrecio, that.idPrecio) &&
                Objects.equals(precio, that.precio) &&
                Objects.equals(fechaDesde, that.fechaDesde) &&
                Objects.equals(fechaHasta, that.fechaHasta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPrecio, precio, fechaDesde, fechaHasta);
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
