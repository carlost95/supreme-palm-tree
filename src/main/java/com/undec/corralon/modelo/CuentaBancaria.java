package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.DateAudit;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cuentas_bancarias")
public class CuentaBancaria extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "titular")
    private String titular;
    @Basic
    @Column(name = "cbu")
    private String cbu;
    @Basic
    @Column(name = "numero")
    private String numero;
    @Basic
    @Column(name = "alias")
    private String alias;
    @Basic
    @Column(name = "habilitado")
    private Boolean habilitado;
    @Basic
    @ManyToOne
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor", nullable = false)
    private Proveedor proveedor;

    @Basic
    @ManyToOne
    @JoinColumn(name = "id_banco", referencedColumnName = "id_banco", nullable = false)
    private Banco banco;

    public CuentaBancaria() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaBancaria that = (CuentaBancaria) o;
        return Objects.equals(id, that.id) && Objects.equals(titular, that.titular)
                && Objects.equals(cbu, that.cbu) && Objects.equals(numero, that.numero)
                && Objects.equals(alias, that.alias) && Objects.equals(proveedor, that.proveedor)
                && Objects.equals(banco, that.banco) && Objects.equals(habilitado, that.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titular, cbu, numero, alias, habilitado, proveedor, banco);
    }
}
