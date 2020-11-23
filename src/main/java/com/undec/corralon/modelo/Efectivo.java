package com.undec.corralon.modelo;

import javax.persistence.*;

@Entity
@Table(name = "efectivo")
public class Efectivo extends DateAudit{

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
}
