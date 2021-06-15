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
    private Integer idAjuste;
    private Integer idArticulo;
//    private Ajuste ajusteByIdAjuste;
//    private Articulo articuloByIdArticulo;

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

    @Basic
    @Column(name = "id_ajuste")
    public Integer getIdAjuste() {
        return idAjuste;
    }

    public void setIdAjuste(Integer idAjuste) {
        this.idAjuste = idAjuste;
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
        DetalleAjuste that = (DetalleAjuste) o;
        return Objects.equals(idDetalleAjuste, that.idDetalleAjuste) &&
                Objects.equals(cantidad, that.cantidad) &&
                Objects.equals(fecha, that.fecha) &&
                Objects.equals(idAjuste, that.idAjuste) &&
                Objects.equals(idArticulo, that.idArticulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetalleAjuste, cantidad, fecha, idAjuste, idArticulo);
    }

//    @ManyToOne
//    @JoinColumn(name = "id_ajuste", referencedColumnName = "id_ajuste", nullable = false)
//    public Ajuste getAjusteByIdAjuste() {
//        return ajusteByIdAjuste;
//    }
//
//    public void setAjusteByIdAjuste(Ajuste ajusteByIdAjuste) {
//        this.ajusteByIdAjuste = ajusteByIdAjuste;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "id_articulo", referencedColumnName = "id_articulo", nullable = false)
//    public Articulo getArticuloByIdArticulo() {
//        return articuloByIdArticulo;
//    }
//
//    public void setArticuloByIdArticulo(Articulo articuloByIdArticulo) {
//        this.articuloByIdArticulo = articuloByIdArticulo;
//    }
}
