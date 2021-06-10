package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Marca extends DateAudit {
    private Integer idMarca;
    private String nombre;
    private String abreviatura;
    private boolean habilitado;
    private List<Articulo> articulosByIdMarca;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
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
    @Column(name = "abreviatura")
    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
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
        Marca marca = (Marca) o;
        return Objects.equals(idMarca, marca.idMarca) &&
                Objects.equals(nombre, marca.nombre) &&
                Objects.equals(abreviatura, marca.abreviatura) &&
                Objects.equals(habilitado, marca.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMarca, nombre, abreviatura, habilitado);
    }

    @OneToMany(mappedBy = "marcaByIdMarca")
    public List<Articulo> getArticulosByIdMarca() {
        return articulosByIdMarca;
    }

    public void setArticulosByIdMarca(List<Articulo> articulosByIdMarca) {
        this.articulosByIdMarca = articulosByIdMarca;
    }
}
