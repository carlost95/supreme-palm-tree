package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Usuario {
    private Integer idUsuario;
    private String nombre;
    private String nombreUsuario;
    private String password;
    private String email;
    private Collection<UsuarioRol> usuarioRolsByIdUsuario;

    @Id
    @Column(name = "id_usuario")
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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
    @Column(name = "nombre_usuario")
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(idUsuario, usuario.idUsuario) &&
                Objects.equals(nombre, usuario.nombre) &&
                Objects.equals(nombreUsuario, usuario.nombreUsuario) &&
                Objects.equals(password, usuario.password) &&
                Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nombre, nombreUsuario, password, email);
    }

    @OneToMany(mappedBy = "usuarioByIdUsuario")
    public Collection<UsuarioRol> getUsuarioRolsByIdUsuario() {
        return usuarioRolsByIdUsuario;
    }

    public void setUsuarioRolsByIdUsuario(Collection<UsuarioRol> usuarioRolsByIdUsuario) {
        this.usuarioRolsByIdUsuario = usuarioRolsByIdUsuario;
    }
}
