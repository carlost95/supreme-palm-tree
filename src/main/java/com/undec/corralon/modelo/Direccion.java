package com.undec.corralon.modelo;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Direccion extends DateAudit{
    private Integer idDireccion;
    private String calle;
    private String numeroCalle;
    private String entreCalle;
    private String barrio;
    private String descripcion;
    private Boolean habilitado;
    private Ubicacion ubicacionByIdUbicacion;
    private Cliente clienteByIdCliente;
    private Distrito distritoByIdDistrito;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    @Basic
    @Column(name = "calle")
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    @Basic
    @Column(name = "numero_calle")
    public String getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(String numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    @Basic
    @Column(name = "entre_calle")
    public String getEntreCalle() {
        return entreCalle;
    }

    public void setEntreCalle(String entreCalle) {
        this.entreCalle = entreCalle;
    }

    @Basic
    @Column(name = "barrio")
    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "habilitado")
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
        Direccion direccion = (Direccion) o;
        return Objects.equals(idDireccion, direccion.idDireccion) &&
                Objects.equals(calle, direccion.calle) &&
                Objects.equals(numeroCalle, direccion.numeroCalle) &&
                Objects.equals(entreCalle, direccion.entreCalle) &&
                Objects.equals(barrio, direccion.barrio) &&
                Objects.equals(descripcion, direccion.descripcion) &&
                Objects.equals(habilitado, direccion.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDireccion, calle, numeroCalle, entreCalle, barrio, descripcion, habilitado);
    }

    @ManyToOne
    @JoinColumn(name = "id_ubicacion", referencedColumnName = "id_ubicacion")
    public Ubicacion getUbicacionByIdUbicacion() {
        return ubicacionByIdUbicacion;
    }

    public void setUbicacionByIdUbicacion(Ubicacion ubicacionByIdUbicacion) {
        this.ubicacionByIdUbicacion = ubicacionByIdUbicacion;
    }

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false)
    @JsonIgnore
    public Cliente getClienteByIdCliente() {
        return clienteByIdCliente;
    }

    public void setClienteByIdCliente(Cliente clienteByIdCliente) {
        this.clienteByIdCliente = clienteByIdCliente;
    }

    @ManyToOne
    @JoinColumn(name = "id_distrito", referencedColumnName = "id_distrito", nullable = false)
    public Distrito getDistritoByIdDistrito() {
        return distritoByIdDistrito;
    }

    public void setDistritoByIdDistrito(Distrito distritoByIdDistrito) {
        this.distritoByIdDistrito = distritoByIdDistrito;
    }
}
