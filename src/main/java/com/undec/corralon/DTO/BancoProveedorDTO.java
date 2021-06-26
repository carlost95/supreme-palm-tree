package com.undec.corralon.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.undec.corralon.modelo.Banco;
import com.undec.corralon.modelo.Proveedor;

public class BancoProveedorDTO {
    private Integer idBancoProveedor;
    private String titularCuenta;
    private String cbu;
    private String numeroCuenta;
    private Integer idProveedor;
    private Integer idBanco;

    public Integer getIdBancoProveedor() {
        return idBancoProveedor;
    }

    public void setIdBancoProveedor(Integer idBancoProveedor) {
        this.idBancoProveedor = idBancoProveedor;
    }

    public String getTitularCuenta() {
        return titularCuenta;
    }

    public void setTitularCuenta(String titularCuenta) {
        this.titularCuenta = titularCuenta;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }
}
