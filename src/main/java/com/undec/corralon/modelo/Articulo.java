package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Articulo {
    private Integer idArticulo;
    private String nombre;
    private Integer stockMinimo;
    private Integer stockMaximo;
    private String codigo;
    private String abreviatura;
    private Integer idProveedor;
    private Integer idUnidadMedida;
    private Integer idRubro;
    private Integer idMarca;
    private Integer idSubRubro;
    private Proveedor proveedorByIdProveedor;
    private UnidadMedida unidadMedidaByIdUnidadMedida;
    private Rubro rubroByIdRubro;
    private Marca marcaByIdMarca;
    private SubRubro subRubroByIdSubRubro;
    private Collection<CostoArticulo> costoArticulosByIdArticulo;
    private Collection<DetalleAjuste> detalleAjustesByIdArticulo;
    private Collection<DetallePedido> detallePedidosByIdArticulo;
    private Collection<DetalleRemito> detalleRemitosByIdArticulo;
    private Collection<DetalleVenta> detalleVentasByIdArticulo;
    private Collection<MovimientoArticulo> movimientoArticulosByIdArticulo;
    private Collection<PrecioArticulo> precioArticulosByIdArticulo;

    @Id
    @Column(name = "id_articulo")
    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
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
    @Column(name = "stock_minimo")
    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    @Basic
    @Column(name = "stock_maximo")
    public Integer getStockMaximo() {
        return stockMaximo;
    }

    public void setStockMaximo(Integer stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    @Basic
    @Column(name = "codigo")
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
    @Column(name = "id_proveedor")
    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Basic
    @Column(name = "id_unidad_medida")
    public Integer getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(Integer idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    @Basic
    @Column(name = "id_rubro")
    public Integer getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(Integer idRubro) {
        this.idRubro = idRubro;
    }

    @Basic
    @Column(name = "id_marca")
    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    @Basic
    @Column(name = "id_sub_rubro")
    public Integer getIdSubRubro() {
        return idSubRubro;
    }

    public void setIdSubRubro(Integer idSubRubro) {
        this.idSubRubro = idSubRubro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Articulo articulo = (Articulo) o;
        return Objects.equals(idArticulo, articulo.idArticulo) &&
                Objects.equals(nombre, articulo.nombre) &&
                Objects.equals(stockMinimo, articulo.stockMinimo) &&
                Objects.equals(stockMaximo, articulo.stockMaximo) &&
                Objects.equals(codigo, articulo.codigo) &&
                Objects.equals(abreviatura, articulo.abreviatura) &&
                Objects.equals(idProveedor, articulo.idProveedor) &&
                Objects.equals(idUnidadMedida, articulo.idUnidadMedida) &&
                Objects.equals(idRubro, articulo.idRubro) &&
                Objects.equals(idMarca, articulo.idMarca) &&
                Objects.equals(idSubRubro, articulo.idSubRubro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArticulo, nombre, stockMinimo, stockMaximo, codigo, abreviatura, idProveedor, idUnidadMedida, idRubro, idMarca, idSubRubro);
    }

    @ManyToOne
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor", nullable = false)
    public Proveedor getProveedorByIdProveedor() {
        return proveedorByIdProveedor;
    }

    public void setProveedorByIdProveedor(Proveedor proveedorByIdProveedor) {
        this.proveedorByIdProveedor = proveedorByIdProveedor;
    }

    @ManyToOne
    @JoinColumn(name = "id_unidad_medida", referencedColumnName = "id_unidad_medida", nullable = false)
    public UnidadMedida getUnidadMedidaByIdUnidadMedida() {
        return unidadMedidaByIdUnidadMedida;
    }

    public void setUnidadMedidaByIdUnidadMedida(UnidadMedida unidadMedidaByIdUnidadMedida) {
        this.unidadMedidaByIdUnidadMedida = unidadMedidaByIdUnidadMedida;
    }

    @ManyToOne
    @JoinColumn(name = "id_rubro", referencedColumnName = "id_rubro", nullable = false)
    public Rubro getRubroByIdRubro() {
        return rubroByIdRubro;
    }

    public void setRubroByIdRubro(Rubro rubroByIdRubro) {
        this.rubroByIdRubro = rubroByIdRubro;
    }

    @ManyToOne
    @JoinColumn(name = "id_marca", referencedColumnName = "id_marca", nullable = false)
    public Marca getMarcaByIdMarca() {
        return marcaByIdMarca;
    }

    public void setMarcaByIdMarca(Marca marcaByIdMarca) {
        this.marcaByIdMarca = marcaByIdMarca;
    }

    @ManyToOne
    @JoinColumn(name = "id_sub_rubro", referencedColumnName = "id_sub_rubro", nullable = false)
    public SubRubro getSubRubroByIdSubRubro() {
        return subRubroByIdSubRubro;
    }

    public void setSubRubroByIdSubRubro(SubRubro subRubroByIdSubRubro) {
        this.subRubroByIdSubRubro = subRubroByIdSubRubro;
    }

    @OneToMany(mappedBy = "articuloByIdArticulo")
    public Collection<CostoArticulo> getCostoArticulosByIdArticulo() {
        return costoArticulosByIdArticulo;
    }

    public void setCostoArticulosByIdArticulo(Collection<CostoArticulo> costoArticulosByIdArticulo) {
        this.costoArticulosByIdArticulo = costoArticulosByIdArticulo;
    }

    @OneToMany(mappedBy = "articuloByIdArticulo")
    public Collection<DetalleAjuste> getDetalleAjustesByIdArticulo() {
        return detalleAjustesByIdArticulo;
    }

    public void setDetalleAjustesByIdArticulo(Collection<DetalleAjuste> detalleAjustesByIdArticulo) {
        this.detalleAjustesByIdArticulo = detalleAjustesByIdArticulo;
    }

    @OneToMany(mappedBy = "articuloByIdArticulo")
    public Collection<DetallePedido> getDetallePedidosByIdArticulo() {
        return detallePedidosByIdArticulo;
    }

    public void setDetallePedidosByIdArticulo(Collection<DetallePedido> detallePedidosByIdArticulo) {
        this.detallePedidosByIdArticulo = detallePedidosByIdArticulo;
    }

    @OneToMany(mappedBy = "articuloByIdArticulo")
    public Collection<DetalleRemito> getDetalleRemitosByIdArticulo() {
        return detalleRemitosByIdArticulo;
    }

    public void setDetalleRemitosByIdArticulo(Collection<DetalleRemito> detalleRemitosByIdArticulo) {
        this.detalleRemitosByIdArticulo = detalleRemitosByIdArticulo;
    }

    @OneToMany(mappedBy = "articuloByIdArticulo")
    public Collection<DetalleVenta> getDetalleVentasByIdArticulo() {
        return detalleVentasByIdArticulo;
    }

    public void setDetalleVentasByIdArticulo(Collection<DetalleVenta> detalleVentasByIdArticulo) {
        this.detalleVentasByIdArticulo = detalleVentasByIdArticulo;
    }

    @OneToMany(mappedBy = "articuloByIdArticulo")
    public Collection<MovimientoArticulo> getMovimientoArticulosByIdArticulo() {
        return movimientoArticulosByIdArticulo;
    }

    public void setMovimientoArticulosByIdArticulo(Collection<MovimientoArticulo> movimientoArticulosByIdArticulo) {
        this.movimientoArticulosByIdArticulo = movimientoArticulosByIdArticulo;
    }

    @OneToMany(mappedBy = "articuloByIdArticulo")
    public Collection<PrecioArticulo> getPrecioArticulosByIdArticulo() {
        return precioArticulosByIdArticulo;
    }

    public void setPrecioArticulosByIdArticulo(Collection<PrecioArticulo> precioArticulosByIdArticulo) {
        this.precioArticulosByIdArticulo = precioArticulosByIdArticulo;
    }
}
