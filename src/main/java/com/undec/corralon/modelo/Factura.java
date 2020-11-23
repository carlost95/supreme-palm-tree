package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="factura")
public class Factura extends DateAudit{

    private Integer id;
    private Double monto;
    private Double descuento;
    private Double total;
    private Date fecha;
    private Cliente cliente;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "descuento")
    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
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
    @Column( name = "fecha")
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    public Cliente getCliente(){
        return this.cliente;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return Objects.equals(id, factura.id) &&
                Objects.equals(monto, factura.monto) &&
                Objects.equals(descuento, factura.descuento) &&
                Objects.equals(total, factura.total) &&
                Objects.equals(fecha, factura.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, monto, descuento, total, fecha);
    }
}
