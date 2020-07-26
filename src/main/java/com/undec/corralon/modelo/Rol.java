package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Rol {
    private Integer id;
    private String nombre;
    private Collection<Pantalla> pantallasById;
    private Collection<UsuarioRol> usuarioRolsById;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rol rol = (Rol) o;
        return Objects.equals(id, rol.id) &&
                Objects.equals(nombre, rol.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @OneToMany(mappedBy = "rolByRolId")
    public Collection<Pantalla> getPantallasById() {
        return pantallasById;
    }

    public void setPantallasById(Collection<Pantalla> pantallasById) {
        this.pantallasById = pantallasById;
    }

    @OneToMany(mappedBy = "rolByRolId")
    public Collection<UsuarioRol> getUsuarioRolsById() {
        return usuarioRolsById;
    }

    public void setUsuarioRolsById(Collection<UsuarioRol> usuarioRolsById) {
        this.usuarioRolsById = usuarioRolsById;
    }
}
