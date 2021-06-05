package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Rol {
    private Integer idRol;
    private String nombreRol;
    private Collection<UsuarioRol> usuarioRolsByIdRol;

    @Id
    @Column(name = "id_rol")
    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    @Basic
    @Column(name = "nombre_rol")
    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rol rol = (Rol) o;
        return Objects.equals(idRol, rol.idRol) &&
                Objects.equals(nombreRol, rol.nombreRol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, nombreRol);
    }

    @OneToMany(mappedBy = "rolByIdRol")
    public Collection<UsuarioRol> getUsuarioRolsByIdRol() {
        return usuarioRolsByIdRol;
    }

    public void setUsuarioRolsByIdRol(Collection<UsuarioRol> usuarioRolsByIdRol) {
        this.usuarioRolsByIdRol = usuarioRolsByIdRol;
    }
}
