package com.undec.corralon.modelo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Venta extends DateAudit{
    private Integer idVenta;
    private Integer idCliente;
    private String fechaVenta;
    private Double totalSinDescuento;
    private Double descuento;
    private Double recargo;
    private Double total;
    private Integer idPagoVenta;
    private Cliente clienteByIdCliente;
    private PagoVenta pagoVentaByIdPagoVenta;
//    private List<DetalleVenta> detalleVentasByIdVenta;
//    private List<MovimientoArticulo> movimientoArticulosByIdVenta;
//    private List<Remito> remitosByIdVenta;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    @Basic
    @Column(name = "id_cliente")
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Basic
    @Column(name = "fechaVenta")
    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    @Basic
    @Column(name = "total_sin_descuento")
    public Double getTotalSinDescuento() {
        return totalSinDescuento;
    }

    public void setTotalSinDescuento(Double totalSinDescuento) {
        this.totalSinDescuento = totalSinDescuento;
    }

    @Basic
    @Column(name = "descuento")
    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    @Basic
    @Column(name = "recargo")
    public Double getRecargo() {
        return recargo;
    }

    public void setRecargo(Double recargo) {
        this.recargo = recargo;
    }

    @Basic
    @Column(name = "total")
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Basic
    @Column(name = "id_pago_venta")
    public Integer getIdPagoVenta() {
        return idPagoVenta;
    }

    public void setIdPagoVenta(Integer idPagoVenta) {
        this.idPagoVenta = idPagoVenta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venta venta = (Venta) o;
        return Objects.equals(idVenta, venta.idVenta) &&
                Objects.equals(idCliente, venta.idCliente) &&
                Objects.equals(fechaVenta, venta.fechaVenta) &&
                Objects.equals(totalSinDescuento, venta.totalSinDescuento) &&
                Objects.equals(descuento, venta.descuento) &&
                Objects.equals(recargo, venta.recargo) &&
                Objects.equals(total, venta.total) &&
                Objects.equals(idPagoVenta, venta.idPagoVenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVenta, idCliente, fechaVenta, totalSinDescuento, descuento, recargo, total, idPagoVenta);
    }

//    @OneToMany(mappedBy = "ventaByIdVenta")
//    public List<DetalleVenta> getDetalleVentasByIdVenta() {
//        return detalleVentasByIdVenta;
//    }
//
//    public void setDetalleVentasByIdVenta(List<DetalleVenta> detalleVentasByIdVenta) {
//        this.detalleVentasByIdVenta = detalleVentasByIdVenta;
//    }
//
//    @OneToMany(mappedBy = "ventaByIdVenta")
//    public List<MovimientoArticulo> getMovimientoArticulosByIdVenta() {
//        return movimientoArticulosByIdVenta;
//    }
//
//    public void setMovimientoArticulosByIdVenta(List<MovimientoArticulo> movimientoArticulosByIdVenta) {
//        this.movimientoArticulosByIdVenta = movimientoArticulosByIdVenta;
//    }
//
//    @OneToMany(mappedBy = "ventaByIdVenta")
//    public List<Remito> getRemitosByIdVenta() {
//        return remitosByIdVenta;
//    }
//
//    public void setRemitosByIdVenta(List<Remito> remitosByIdVenta) {
//        this.remitosByIdVenta = remitosByIdVenta;
//    }
//
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false)
    public Cliente getClienteByIdCliente() {
        return clienteByIdCliente;
    }

    public void setClienteByIdCliente(Cliente clienteByIdCliente) {
        this.clienteByIdCliente = clienteByIdCliente;
    }

    @ManyToOne
    @JoinColumn(name = "id_pago_venta", referencedColumnName = "id_pago_venta", nullable = false)
    public PagoVenta getPagoVentaByIdPagoVenta() {
        return pagoVentaByIdPagoVenta;
    }

    public void setPagoVentaByIdPagoVenta(PagoVenta pagoVentaByIdPagoVenta) {
        this.pagoVentaByIdPagoVenta = pagoVentaByIdPagoVenta;
    }
}
