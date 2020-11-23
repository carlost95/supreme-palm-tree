package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="pago")
public class Pago extends DateAudit{
    private Integer id;
    private Double monto;

    @Id
    @Column(name = "id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pago pago = (Pago) o;
        return Objects.equals(id, pago.id) &&
                Objects.equals(monto, pago.monto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, monto);
    }
}
