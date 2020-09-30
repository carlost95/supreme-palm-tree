package com.undec.corralon.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Objects;

@Entity
public class Direccion extends DateAudit{

    private Integer id;
    private String calle;
    private String descripcion;
    private String numerocalle;
    private Boolean estado;
    private Cliente cliente;
    private Ubicacion ubicacion;
    private Distrito distrito;

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
    @Column(name = "calle")
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
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
    @Column(name = "estado")
    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "numerocalle")
    public String getNumerocalle() {
        return numerocalle;
    }

    public void setNumerocalle(String numerocalle) {
        this.numerocalle = numerocalle;
    }

    @ManyToOne
    @JoinColumn(name="cliente_id")
    @JsonIgnore
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    @ManyToOne
    @JoinColumn(name="distritos_id")
    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    @OneToOne
    @JoinColumn(name = "ubicacion_id")
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direccion direccion = (Direccion) o;
        return id == direccion.id &&
                Objects.equals(calle, direccion.calle) &&
                Objects.equals(descripcion, direccion.descripcion) &&
                Objects.equals(estado, direccion.estado) &&
                Objects.equals(numerocalle, direccion.numerocalle) &&
                Objects.equals(cliente, direccion.cliente) &&
                Objects.equals(distrito, direccion.distrito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, calle, descripcion, estado, numerocalle, cliente, distrito);
    }
}
