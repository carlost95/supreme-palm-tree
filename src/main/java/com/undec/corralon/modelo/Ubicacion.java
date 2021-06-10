package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Ubicacion extends DateAudit{
    private Integer idUbicacion;
    private String latitud;
    private String longitud;
    private boolean habilitado;
    private List<Direccion> direccionsByIdUbicacion;

    @Id
    @Column(name = "id_ubicacion")
    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
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
    @Column(name = "habilitado")
    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ubicacion ubicacion = (Ubicacion) o;
        return Objects.equals(idUbicacion, ubicacion.idUbicacion) &&
                Objects.equals(latitud, ubicacion.latitud) &&
                Objects.equals(longitud, ubicacion.longitud) &&
                Objects.equals(habilitado, ubicacion.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUbicacion, latitud, longitud, habilitado);
    }

    @OneToMany(mappedBy = "ubicacionByIdUbicacion")
    public List<Direccion> getDireccionsByIdUbicacion() {
        return direccionsByIdUbicacion;
    }

    public void setDireccionsByIdUbicacion(List<Direccion> direccionsByIdUbicacion) {
        this.direccionsByIdUbicacion = direccionsByIdUbicacion;
    }
}
