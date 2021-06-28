package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.DateAudit;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Cliente extends DateAudit {
    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String dni;
    private Boolean habilitado;
    private String mail;
//    private List<Direccion> direccionsByIdCliente;
//    private List<Venta> ventasByIdCliente;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "apellido")
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Basic
    @Column(name = "dni")
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Basic
    @Column(name = "habilitado")
    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Basic
    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(idCliente, cliente.idCliente) &&
                Objects.equals(nombre, cliente.nombre) &&
                Objects.equals(apellido, cliente.apellido) &&
                Objects.equals(dni, cliente.dni) &&
                Objects.equals(habilitado, cliente.habilitado) &&
                Objects.equals(mail, cliente.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, nombre, apellido, dni, habilitado, mail);
    }

//    @OneToMany(mappedBy = "clienteByIdCliente")
//    public List<Direccion> getDireccionsByIdCliente() {
//        return direccionsByIdCliente;
//    }
//
//    public void setDireccionsByIdCliente(List<Direccion> direccionsByIdCliente) {
//        this.direccionsByIdCliente = direccionsByIdCliente;
//    }

//    @OneToMany(mappedBy = "clienteByIdCliente")
//    public List<Venta> getVentasByIdCliente() {
//        return ventasByIdCliente;
//    }
//
//    public void setVentasByIdCliente(List<Venta> ventasByIdCliente) {
//        this.ventasByIdCliente = ventasByIdCliente;
//    }
}
