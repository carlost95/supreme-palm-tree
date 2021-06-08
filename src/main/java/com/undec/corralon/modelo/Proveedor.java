package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Proveedor {
    private Integer idProveedor;
    private String razonSocial;
    private String domicilio;
    private String email;
    private String telefono;
    private Byte habilitado;
    private Collection<Articulo> articulosByIdProveedor;
    private Collection<BancoProveedor> bancoProveedorsByIdProveedor;

    @Id
    @Column(name = "id_proveedor")
    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return Objects.equals(idProveedor, proveedor.idProveedor) &&
                Objects.equals(razonSocial, proveedor.razonSocial) &&
                Objects.equals(domicilio, proveedor.domicilio) &&
                Objects.equals(email, proveedor.email) &&
                Objects.equals(telefono, proveedor.telefono) &&
                Objects.equals(habilitado, proveedor.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProveedor, razonSocial, domicilio, email, telefono, habilitado);
    }

    @OneToMany(mappedBy = "proveedorByIdProveedor")
    public Collection<Articulo> getArticulosByIdProveedor() {
        return articulosByIdProveedor;
    }

    public void setArticulosByIdProveedor(Collection<Articulo> articulosByIdProveedor) {
        this.articulosByIdProveedor = articulosByIdProveedor;
    }

    @OneToMany(mappedBy = "proveedorByIdProveedor")
    public Collection<BancoProveedor> getBancoProveedorsByIdProveedor() {
        return bancoProveedorsByIdProveedor;
    }

    public void setBancoProveedorsByIdProveedor(Collection<BancoProveedor> bancoProveedorsByIdProveedor) {
        this.bancoProveedorsByIdProveedor = bancoProveedorsByIdProveedor;
    }
}
