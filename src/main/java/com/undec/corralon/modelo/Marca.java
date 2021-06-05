package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Marca {
    private Integer idMarca;
    private String nombre;
    private String abreviatura;
    private Collection<Articulo> articulosByIdMarca;

    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marca marca = (Marca) o;
        return Objects.equals(idMarca, marca.idMarca) &&
                Objects.equals(nombre, marca.nombre) &&
                Objects.equals(abreviatura, marca.abreviatura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMarca, nombre, abreviatura);
    }

    @OneToMany(mappedBy = "marcaByIdMarca")
    public Collection<Articulo> getArticulosByIdMarca() {
        return articulosByIdMarca;
    }

    public void setArticulosByIdMarca(Collection<Articulo> articulosByIdMarca) {
        this.articulosByIdMarca = articulosByIdMarca;
    }
}
