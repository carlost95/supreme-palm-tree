package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Articulo extends DateAudit {
    private Integer idArticulo;
    private String codigo;
    private String nombre;
    private String abreviatura;
    private Integer stockMinimo;
    private Integer stockMaximo;
    private Boolean habilitado;

    private Proveedor proveedorByIdProveedor;
    private UnidadMedida unidadMedidaByIdUnidadMedida;
    private Rubro rubroByIdRubro;
    private Marca marcaByIdMarca;
    private SubRubro subRubroByIdSubRubro;

//    private List<CostoArticulo> costoArticulosByIdArticulo;
//    private List<DetalleAjuste> detalleAjustesByIdArticulo;
//    private List<DetallePedido> detallePedidosByIdArticulo;
//    private List<DetalleRemito> detalleRemitosByIdArticulo;
//    private List<DetalleVenta> detalleVentasByIdArticulo;
//    private List<MovimientoArticulo> movimientoArticulosByIdArticulo;
//    private List<PrecioArticulo> precioArticulosByIdArticulo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
                Objects.equals(abreviatura, articulo.abreviatura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArticulo, nombre, stockMinimo, stockMaximo, codigo, abreviatura);
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

    @Basic
    @Column(name = "habilitado")
    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }
}
