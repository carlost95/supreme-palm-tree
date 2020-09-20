package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Ubicacion extends DateAudit {

    private Integer id;
    private String latitud;
    private String longitud;
    private Boolean estado;

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
    @Column(name = "latitud")
    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    @Basic
    @Column(name = "longitud")
    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    @Basic
    @Column(name = "estado")
    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ubicacion ubicacion = (Ubicacion) o;
        return id.equals(ubicacion.id) &&
                Objects.equals(latitud, ubicacion.latitud) &&
                Objects.equals(longitud, ubicacion.longitud);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitud, longitud);
    }
}

