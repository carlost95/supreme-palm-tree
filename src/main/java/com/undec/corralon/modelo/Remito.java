package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.UserDateAudit;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
public class Remito extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_remito")
    private Integer idRemito;
    @Basic
    @Column(name = "nro_remito", nullable = false)
    private Long nroRemito;
    @Basic
    @Column(name = "fecha_remito", nullable = false)
    private Date fechaRemito;
    @Basic
    @Column(name = "entregado", nullable = false)
    private Boolean entregado;
    @ManyToOne
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta", nullable = false)
    private Venta venta;
    @ManyToOne
    @JoinColumn(name = "id_direccion", referencedColumnName = "id_direccion", nullable = false)
    private Direccion direccion;
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", nullable = false)
    private Empresa empresa;

    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

    public Long getNroRemito() {
        return nroRemito;
    }

    public void setNroRemito(Long nroRemito) {
        this.nroRemito = nroRemito;
    }

    public Date getFechaRemito() {
        return fechaRemito;
    }

    public void setFechaRemito(Date fechaRemito) {
        this.fechaRemito = fechaRemito;
    }

    public Boolean getEntregado() {
        return entregado;
    }

    public void setEntregado(Boolean entregado) {
        this.entregado = entregado;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Remito remito = (Remito) o;
        return Objects.equals(idRemito, remito.idRemito) && Objects.equals(nroRemito, remito.nroRemito) && Objects.equals(fechaRemito, remito.fechaRemito) && Objects.equals(entregado, remito.entregado) && Objects.equals(venta, remito.venta) && Objects.equals(direccion, remito.direccion) && Objects.equals(cliente, remito.cliente) && Objects.equals(empresa, remito.empresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRemito, nroRemito, fechaRemito, entregado, venta, direccion, cliente, empresa);
    }

    @Override
    public String toString() {
        return "Remito{" +
                "idRemito=" + idRemito +
                ", nroRemito=" + nroRemito +
                ", fechaRemito=" + fechaRemito +
                ", entregado=" + entregado +
                ", venta=" + venta +
                ", direccion=" + direccion +
                ", cliente=" + cliente +
                ", empresa=" + empresa +
                '}';
    }
}
