package com.undec.corralon.modelo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Precio {
    private Integer id;
    private Double valor;
    private String feachaHasta;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "valor")
    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Basic
    @Column(name = "feacha_hasta")
    public String getFeachaHasta() {
        return feachaHasta;
    }

    public void setFeachaHasta(String feachaHasta) {
        this.feachaHasta = feachaHasta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Precio precio = (Precio) o;
        return Objects.equals(id, precio.id) &&
                Objects.equals(valor, precio.valor) &&
                Objects.equals(feachaHasta, precio.feachaHasta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valor, feachaHasta);
    }
}
