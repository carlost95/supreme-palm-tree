package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pago_factura")
public class PagoFactura extends DateAudit{

    private Integer id;
    private Double monto;

    @Id
    @Column(name = "id")
    @GeneratedValue( strategy = GenerationType.AUTO)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagoFactura that = (PagoFactura) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(monto, that.monto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, monto);
    }
}
