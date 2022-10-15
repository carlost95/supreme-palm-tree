package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.UserDateAudit;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Venta extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false)
    private Cliente idCliente;
    @ManyToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", nullable = false)
    private Empresa idEmpresa;
    @ManyToOne
    @JoinColumn(name = "id_direccion", referencedColumnName = "id_direccion", nullable = false)
    private Direccion idDireccion;
    @Basic
    @Column(name = "nro_venta", nullable = false)
    private Long nroVenta;
    @Basic
    @Column(name = "descuento")
    private Double descuento;
    @Basic
    @Column(name = "total")
    private Double total;
    @Basic
    @Column(name = "fecha_venta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVenta;


    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Long getNroVenta() {
        return nroVenta;
    }

    public void setNroVenta(Long nroVenta) {
        this.nroVenta = nroVenta;
    }

    public Direccion getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Direccion idDireccion) {
        this.idDireccion = idDireccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venta venta = (Venta) o;
        return Objects.equals(idVenta, venta.idVenta) && Objects.equals(idCliente, venta.idCliente) && Objects.equals(idEmpresa, venta.idEmpresa) && Objects.equals(idDireccion, venta.idDireccion) && Objects.equals(nroVenta, venta.nroVenta) && Objects.equals(descuento, venta.descuento) && Objects.equals(total, venta.total) && Objects.equals(fechaVenta, venta.fechaVenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVenta, idCliente, idEmpresa, idDireccion, nroVenta, descuento, total, fechaVenta);
    }
}
