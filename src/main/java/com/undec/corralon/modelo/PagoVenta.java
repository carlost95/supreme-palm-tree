package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.DateAudit;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pago_venta")
public class PagoVenta extends DateAudit {
    private Integer idPagoVenta;
    private Double monto;
    private String fechaPago;
    private Cheque chequeByIdCheque;
    private Tarjeta tarjetaByIdTarjeta;
//    private List<Venta> ventasByIdPagoVenta;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago_venta")
    public Integer getIdPagoVenta() {
        return idPagoVenta;
    }

    public void setIdPagoVenta(Integer idPagoVenta) {
        this.idPagoVenta = idPagoVenta;
    }

    @Basic
    @Column(name = "monto")
    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    @Basic
    @Column(name = "fecha_pago")
    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagoVenta pagoVenta = (PagoVenta) o;
        return Objects.equals(idPagoVenta, pagoVenta.idPagoVenta) &&
                Objects.equals(monto, pagoVenta.monto) &&
                Objects.equals(fechaPago, pagoVenta.fechaPago);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPagoVenta, monto, fechaPago);
    }

    @ManyToOne
    @JoinColumn(name = "id_cheque", referencedColumnName = "id_cheque")
    public Cheque getChequeByIdCheque() {
        return chequeByIdCheque;
    }

    public void setChequeByIdCheque(Cheque chequeByIdCheque) {
        this.chequeByIdCheque = chequeByIdCheque;
    }

    @ManyToOne
    @JoinColumn(name = "id_tarjeta", referencedColumnName = "id_tarjeta")
    public Tarjeta getTarjetaByIdTarjeta() {
        return tarjetaByIdTarjeta;
    }

    public void setTarjetaByIdTarjeta(Tarjeta tarjetaByIdTarjeta) {
        this.tarjetaByIdTarjeta = tarjetaByIdTarjeta;
    }

//    @OneToMany(mappedBy = "pagoVentaByIdPagoVenta")
//    public List<Venta> getVentasByIdPagoVenta() {
//        return ventasByIdPagoVenta;
//    }
//
//    public void setVentasByIdPagoVenta(List<Venta> ventasByIdPagoVenta) {
//        this.ventasByIdPagoVenta = ventasByIdPagoVenta;
//    }
}
