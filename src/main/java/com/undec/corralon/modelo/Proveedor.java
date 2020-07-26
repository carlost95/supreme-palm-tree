package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Proveedor {
    private Integer id;
    private String razonSocial;
    private String domicilio;
    private String mail;
    private String celular;
    private String telefono;
    private Byte habilitado;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "razon_social")
    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    @Basic
    @Column(name = "domicilio")
    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    @Basic
    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "celular")
    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Basic
    @Column(name = "telefono")
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Basic
    @Column(name = "habilitado")
    public Byte getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Byte habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proveedor proveedor = (Proveedor) o;
        return Objects.equals(id, proveedor.id) &&
                Objects.equals(razonSocial, proveedor.razonSocial) &&
                Objects.equals(domicilio, proveedor.domicilio) &&
                Objects.equals(mail, proveedor.mail) &&
                Objects.equals(celular, proveedor.celular) &&
                Objects.equals(telefono, proveedor.telefono) &&
                Objects.equals(habilitado, proveedor.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, razonSocial, domicilio, mail, celular, telefono, habilitado);
    }
}
