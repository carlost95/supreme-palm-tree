package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.UserDateAudit;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Proveedor extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer idProveedor;
    @Basic
    @Column(name = "razon_social")
    private String razonSocial;
    @Basic
    @Column(name = "domicilio")
    private String domicilio;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "telefono")
    private String telefono;
    @Basic
    @Column(name = "habilitado")
    private Boolean habilitado;


    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        Proveedor proveedor = (Proveedor) o;
        return Objects.equals(idProveedor, proveedor.idProveedor) &&
                Objects.equals(razonSocial, proveedor.razonSocial) &&
                Objects.equals(domicilio, proveedor.domicilio) &&
                Objects.equals(email, proveedor.email) &&
                Objects.equals(telefono, proveedor.telefono) &&
                Objects.equals(habilitado, proveedor.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                idProveedor, razonSocial, domicilio, email, telefono, habilitado);
    }
}
