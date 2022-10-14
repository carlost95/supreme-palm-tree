package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.UserDateAudit;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Remito extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_remito")
    private Integer idRemito;
    @Basic
    @Column(name = "nro_remito", nullable = false)
    private Integer nroRemito;
    @Basic
    @Column(name = "fecha_remito", nullable = false)
    private Timestamp fechaRemito;
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

    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

    public Integer getNroRemito() {
        return nroRemito;
    }

    public void setNroRemito(Integer nroRemito) {
        this.nroRemito = nroRemito;
    }

    public Timestamp getFechaRemito() {
        return fechaRemito;
    }

    public void setFechaRemito(Timestamp fechaRemito) {
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

}
