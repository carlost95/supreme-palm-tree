package com.undec.corralon.modelo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Usuario {
    private Integer id;
    private String nombre;
    private String password;
    private Byte habilitacion;
    private Timestamp fechaCreacion;
    private Timestamp fechaModificacion;
    private String email;
    private String nombreUsuario;
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

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "habilitacion")
    public Byte getHabilitacion() {
        return habilitacion;
    }

    public void setHabilitacion(Byte habilitacion) {
        this.habilitacion = habilitacion;
    }

    @Basic
    @Column(name = "fecha_creacion")
    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Basic
    @Column(name = "fecha_modificacion")
    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
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
    @Column(name = "nombre_usuario")
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) &&
                Objects.equals(nombre, usuario.nombre) &&
                Objects.equals(password, usuario.password) &&
                Objects.equals(habilitacion, usuario.habilitacion) &&
                Objects.equals(fechaCreacion, usuario.fechaCreacion) &&
                Objects.equals(fechaModificacion, usuario.fechaModificacion) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(nombreUsuario, usuario.nombreUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, password, habilitacion, fechaCreacion, fechaModificacion, email, nombreUsuario);
    }

    @OneToMany(mappedBy = "usuarioByUsuarioId")
    public Collection<UsuarioRol> getUsuarioRolsById() {
        return usuarioRolsById;
    }

    public void setUsuarioRolsById(Collection<UsuarioRol> usuarioRolsById) {
        this.usuarioRolsById = usuarioRolsById;
    }
}
