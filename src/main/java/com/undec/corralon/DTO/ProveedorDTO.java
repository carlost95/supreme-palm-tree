package com.undec.corralon.DTO;

import com.undec.corralon.modelo.Banco;

public class ProveedorDTO {
    private Integer idProveedor;
    private String razonSocial;
    private String email;
    private String telefono;
    private String domicilio;
    private Boolean habilitado;
    private String titularCuenta;
    private String numeroCuenta;
    private String cbu;
    private Banco banco;

    public Integer getIdProveedor() {
        return idProveedor;
    }


    public String getRazonSocial() {
        return razonSocial;
    }


    public String getEmail() {
        return email;
    }


    public String getTelefono() {
        return telefono;
    }


    public String getDomicilio() {
        return domicilio;
    }

    public String getTitularCuenta() {
        return titularCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getCbu() {
        return cbu;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
}
