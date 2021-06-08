package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "banco_proveedor", schema = "corralon_dev", catalog = "")
public class BancoProveedor {
    private Integer idBancoProveedor;
    private String titularCuenta;
    private String cbu;
    private String numeroCuenta;
    private Integer idProveedor;
    private Integer idBanco;
    private Proveedor proveedorByIdProveedor;
    private Banco bancoByIdBanco;

    @Id
    @Column(name = "id_banco_proveedor")
    public Integer getIdBancoProveedor() {
        return idBancoProveedor;
    }

    public void setIdBancoProveedor(Integer idBancoProveedor) {
        this.idBancoProveedor = idBancoProveedor;
    }

    @Basic
    @Column(name = "titular_cuenta")
    public String getTitularCuenta() {
        return titularCuenta;
    }

    public void setTitularCuenta(String titularCuenta) {
        this.titularCuenta = titularCuenta;
    }

    @Basic
    @Column(name = "cbu")
    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    @Basic
    @Column(name = "numero_cuenta")
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    @Basic
    @Column(name = "id_proveedor")
    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Basic
    @Column(name = "id_banco")
    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BancoProveedor that = (BancoProveedor) o;
        return Objects.equals(idBancoProveedor, that.idBancoProveedor) &&
                Objects.equals(titularCuenta, that.titularCuenta) &&
                Objects.equals(cbu, that.cbu) &&
                Objects.equals(numeroCuenta, that.numeroCuenta) &&
                Objects.equals(idProveedor, that.idProveedor) &&
                Objects.equals(idBanco, that.idBanco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBancoProveedor, titularCuenta, cbu, numeroCuenta, idProveedor, idBanco);
    }

    @ManyToOne
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor", nullable = false)
    public Proveedor getProveedorByIdProveedor() {
        return proveedorByIdProveedor;
    }

    public void setProveedorByIdProveedor(Proveedor proveedorByIdProveedor) {
        this.proveedorByIdProveedor = proveedorByIdProveedor;
    }

    @ManyToOne
    @JoinColumn(name = "id_banco", referencedColumnName = "id_banco", nullable = false)
    public Banco getBancoByIdBanco() {
        return bancoByIdBanco;
    }

    public void setBancoByIdBanco(Banco bancoByIdBanco) {
        this.bancoByIdBanco = bancoByIdBanco;
    }
}