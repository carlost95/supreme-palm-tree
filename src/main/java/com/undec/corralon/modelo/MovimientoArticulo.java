package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.UserDateAudit;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "movimiento_articulo")
public class MovimientoArticulo extends UserDateAudit {
    private Integer idMovimientoArticulo;
    private Integer movimiento;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    private Integer devolocion;
    private Articulo articuloByIdArticulo;
    private DetalleAjuste detalleAjusteByIdDetalleAjuste;
    private DetallePedido detallePedidoByIdDetallePedido;
    private DetalleRemito detalleRemitoByIdDetalleRemito;
    private DetalleVenta detalleVentaByIdDetalleVenta;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
                Objects.equals(devolocion, that.devolocion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMovimientoArticulo, movimiento, fecha, devolocion);
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
    @JoinColumn(name = "id_detalle_ajuste", referencedColumnName = "id_detalle_ajuste")
    public DetalleAjuste getDetalleAjusteByIdDetalleAjuste() {
        return detalleAjusteByIdDetalleAjuste;
    }

    public void setDetalleAjusteByIdDetalleAjuste(DetalleAjuste detalleAjusteByIdDetalleAjuste) {
        this.detalleAjusteByIdDetalleAjuste = detalleAjusteByIdDetalleAjuste;
    }

    @ManyToOne
    @JoinColumn(name = "id_detalle_pedido", referencedColumnName = "id_detalle_pedido")
    public DetallePedido getDetallePedidoByIdDetallePedido() {
        return detallePedidoByIdDetallePedido;
    }

    public void setDetallePedidoByIdDetallePedido(DetallePedido detallePedidoByIdDetallePedido) {
        this.detallePedidoByIdDetallePedido = detallePedidoByIdDetallePedido;
    }

    @ManyToOne
    @JoinColumn(name = "id_detalle_remito", referencedColumnName = "id_detalle_remito")
    public DetalleRemito getDetalleRemitoByIdDetalleRemito() {
        return detalleRemitoByIdDetalleRemito;
    }

    public void setDetalleRemitoByIdDetalleRemito(DetalleRemito detalleRemitoByIdDetalleRemito) {
        this.detalleRemitoByIdDetalleRemito = detalleRemitoByIdDetalleRemito;
    }


    @ManyToOne
    @JoinColumn(name = "id_detalle_venta", referencedColumnName = "id_detalle_venta")
    public DetalleVenta getDetalleVentaByIdDetalleVenta() {
        return detalleVentaByIdDetalleVenta;
    }

    public void setDetalleVentaByIdDetalleVenta(DetalleVenta detalleVentaByIdDetalleVenta) {
        this.detalleVentaByIdDetalleVenta = detalleVentaByIdDetalleVenta;
    }
}
