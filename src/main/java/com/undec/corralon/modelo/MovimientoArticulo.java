package com.undec.corralon.modelo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "movimiento_articulo", schema = "corralon_dev", catalog = "")
public class MovimientoArticulo {
    private Integer idMovimientoArticulo;
    private Integer movimiento;
    private Timestamp fecha;
    private Integer idArticulo;
    private Integer idVenta;
    private Integer idAjuste;
    private Integer idPedido;
    private Integer idRemito;
    private Integer devolocion;
    private Articulo articuloByIdArticulo;
    private Venta ventaByIdVenta;
    private Ajuste ajusteByIdAjuste;
    private Pedido pedidoByIdPedido;
    private Remito remitoByIdRemito;

    @Id
    @Column(name = "id_movimiento_articulo")
    public Integer getIdMovimientoArticulo() {
        return idMovimientoArticulo;
    }

    public void setIdMovimientoArticulo(Integer idMovimientoArticulo) {
        this.idMovimientoArticulo = idMovimientoArticulo;
    }

    @Basic
    @Column(name = "movimiento")
    public Integer getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Integer movimiento) {
        this.movimiento = movimiento;
    }

    @Basic
    @Column(name = "fecha")
    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
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
    @Column(name = "id_venta")
    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
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
    @Column(name = "id_pedido")
    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    @Basic
    @Column(name = "id_remito")
    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

    @Basic
    @Column(name = "devolocion")
    public Integer getDevolocion() {
        return devolocion;
    }

    public void setDevolocion(Integer devolocion) {
        this.devolocion = devolocion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovimientoArticulo that = (MovimientoArticulo) o;
        return Objects.equals(idMovimientoArticulo, that.idMovimientoArticulo) &&
                Objects.equals(movimiento, that.movimiento) &&
                Objects.equals(fecha, that.fecha) &&
                Objects.equals(idArticulo, that.idArticulo) &&
                Objects.equals(idVenta, that.idVenta) &&
                Objects.equals(idAjuste, that.idAjuste) &&
                Objects.equals(idPedido, that.idPedido) &&
                Objects.equals(idRemito, that.idRemito) &&
                Objects.equals(devolocion, that.devolocion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMovimientoArticulo, movimiento, fecha, idArticulo, idVenta, idAjuste, idPedido, idRemito, devolocion);
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
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
    public Venta getVentaByIdVenta() {
        return ventaByIdVenta;
    }

    public void setVentaByIdVenta(Venta ventaByIdVenta) {
        this.ventaByIdVenta = ventaByIdVenta;
    }

    @ManyToOne
    @JoinColumn(name = "id_ajuste", referencedColumnName = "id_ajuste")
    public Ajuste getAjusteByIdAjuste() {
        return ajusteByIdAjuste;
    }

    public void setAjusteByIdAjuste(Ajuste ajusteByIdAjuste) {
        this.ajusteByIdAjuste = ajusteByIdAjuste;
    }

    @ManyToOne
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
    public Pedido getPedidoByIdPedido() {
        return pedidoByIdPedido;
    }

    public void setPedidoByIdPedido(Pedido pedidoByIdPedido) {
        this.pedidoByIdPedido = pedidoByIdPedido;
    }

    @ManyToOne
    @JoinColumn(name = "id_remito", referencedColumnName = "id_remito")
    public Remito getRemitoByIdRemito() {
        return remitoByIdRemito;
    }

    public void setRemitoByIdRemito(Remito remitoByIdRemito) {
        this.remitoByIdRemito = remitoByIdRemito;
    }
}
