package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tipo_permiso", schema = "santo_domingo_corralon", catalog = "")
public class TipoPermiso {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Permiso permisoByPermisoId;

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
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoPermiso that = (TipoPermiso) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion);
    }

    @ManyToOne
    @JoinColumn(name = "permiso_id", referencedColumnName = "id")
    public Permiso getPermisoByPermisoId() {
        return permisoByPermisoId;
    }

    public void setPermisoByPermisoId(Permiso permisoByPermisoId) {
        this.permisoByPermisoId = permisoByPermisoId;
    }
}
