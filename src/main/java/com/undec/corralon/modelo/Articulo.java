package com.undec.corralon.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "articulo", schema = "santo_domingo_corralon")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Articulo {
    private Integer id;
    private String nombre;
    private String abreviatura;
    private String codigoArt;
    private Integer stockMin;
    private Integer stockMax;
    private Proveedor proveedorId;
    private UnidadMedida unidadMedidaId;
    private Marca marcaId;
    private Rubro rubroId;
    private SubRubro subRubroId;
    private Integer habilitacion;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Date fechaBaja;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "codigo_art")
    public String getCodigoArt() {
        return codigoArt;
    }

    public void setCodigoArt(String codigoArt) {
        this.codigoArt = codigoArt;
    }

    @Basic
    @Column(name = "stock_min")
    public Integer getStockMin() {
        return stockMin;
    }

    public void setStockMin(Integer stockMin) {
        this.stockMin = stockMin;
    }

    @Basic
    @Column(name = "stock_max")
    public Integer getStockMax() {
        return stockMax;
    }

    public void setStockMax(Integer stockMax) {
        this.stockMax = stockMax;
    }

    @ManyToOne
    @JoinColumn(name = "proveedor_id", referencedColumnName = "id")
    public Proveedor getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Proveedor proveedorId) {
        this.proveedorId = proveedorId;
    }

    @ManyToOne
    @JoinColumn(name = "unidad_medida_id", referencedColumnName = "id")
    public UnidadMedida getUnidadMedidaId() {
        return unidadMedidaId;
    }

    public void setUnidadMedidaId(UnidadMedida unidadMedidaId) {
        this.unidadMedidaId = unidadMedidaId;
    }

    @ManyToOne
    @JoinColumn(name = "marca_id", referencedColumnName = "id")
    public Marca getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Marca marcaId) {
        this.marcaId = marcaId;
    }

    @ManyToOne
    @JoinColumn(name = "rubro_id", referencedColumnName = "id")
    public Rubro getRubroId() {
        return rubroId;
    }

    public void setRubroId(Rubro rubroId) {
        this.rubroId = rubroId;
    }

    @ManyToOne
    @JoinColumn(name = "sub_rubro_id", referencedColumnName = "id")
    public SubRubro getSubRubroId() {
        return subRubroId;
    }

    public void setSubRubroId(SubRubro subRubroId) {
        this.subRubroId = subRubroId;
    }

    @Basic
    @Column(name = "habilitacion")
    public Integer getHabilitacion() {
        return habilitacion;
    }

    public void setHabilitacion(Integer habilitacion) {
        this.habilitacion = habilitacion;
    }

    @Basic
    @Column(name = "fecha_creacion")
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Basic
    @Column(name = "fecha_modificacion")
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Basic
    @Column(name = "fecha_baja")
    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Articulo articulo = (Articulo) o;
        return Objects.equals(id, articulo.id) &&
                Objects.equals(nombre, articulo.nombre) &&
                Objects.equals(abreviatura, articulo.abreviatura) &&
                Objects.equals(codigoArt, articulo.codigoArt) &&
                Objects.equals(stockMin, articulo.stockMin) &&
                Objects.equals(stockMax, articulo.stockMax) &&
                Objects.equals(proveedorId, articulo.proveedorId) &&
                Objects.equals(unidadMedidaId, articulo.unidadMedidaId) &&
                Objects.equals(marcaId, articulo.marcaId) &&
                Objects.equals(rubroId, articulo.rubroId) &&
                Objects.equals(subRubroId, articulo.subRubroId) &&
                Objects.equals(habilitacion, articulo.habilitacion) &&
                Objects.equals(fechaCreacion, articulo.fechaCreacion) &&
                Objects.equals(fechaModificacion, articulo.fechaModificacion) &&
                Objects.equals(fechaBaja, articulo.fechaBaja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, abreviatura, codigoArt, stockMin, stockMax, proveedorId, unidadMedidaId, marcaId, rubroId, subRubroId, habilitacion, fechaCreacion, fechaModificacion, fechaBaja);
    }
}
