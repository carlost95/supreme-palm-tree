package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.DateAudit;
import com.undec.corralon.modelo.audit.UserDateAudit;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Ubicacion extends UserDateAudit {
    @Id
    @Column(name = "id_ubicacion")
    private Integer idUbicacion;

    @Basic
    @Column(name = "latitud")
    private String latitud;
    @Basic
    @Column(name = "longitud")
    private String longitud;
    @Basic
    @Column(name = "status")
    private Boolean status;

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ubicacion ubicacion = (Ubicacion) o;
        return Objects.equals(idUbicacion, ubicacion.idUbicacion) && Objects.equals(latitud, ubicacion.latitud) && Objects.equals(longitud, ubicacion.longitud) && Objects.equals(status, ubicacion.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUbicacion, latitud, longitud, status);
    }
}
