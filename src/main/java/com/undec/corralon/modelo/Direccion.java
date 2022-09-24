package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.UserDateAudit;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Direccion extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private Integer idDireccion;
    @Basic
    @Column(name = "calle")
    private String calle;
    @Basic
    @Column(name = "numero_calle")
    private String numeroCalle;
    @Basic
    @Column(name = "entre_calle")
    private String entreCalle;
    @Basic
    @Column(name = "barrio")
    private String barrio;
    @Basic
    @Column(name = "descripcion")
    private String descripcion;
    @Basic
    @Column(name = "status")
    private Boolean status;
    @Basic
    @Column(name = "latitud")
    private String latitud;
    @Basic
    @Column(name = "longitud")
    private String longitud;
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_distrito", referencedColumnName = "id_distrito", nullable = false)
    private Distrito distrito;

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(String numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    public String getEntreCalle() {
        return entreCalle;
    }

    public void setEntreCalle(String entreCalle) {
        this.entreCalle = entreCalle;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direccion direccion = (Direccion) o;
        return Objects.equals(idDireccion, direccion.idDireccion) && Objects.equals(calle, direccion.calle) && Objects.equals(numeroCalle, direccion.numeroCalle) && Objects.equals(entreCalle, direccion.entreCalle) && Objects.equals(barrio, direccion.barrio) && Objects.equals(descripcion, direccion.descripcion) && Objects.equals(status, direccion.status) && Objects.equals(latitud, direccion.latitud) && Objects.equals(longitud, direccion.longitud) && Objects.equals(cliente, direccion.cliente) && Objects.equals(distrito, direccion.distrito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDireccion, calle, numeroCalle, entreCalle, barrio, descripcion, status, latitud, longitud, cliente, distrito);
    }
}
