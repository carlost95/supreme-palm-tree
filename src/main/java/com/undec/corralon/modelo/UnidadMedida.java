package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "unidad_medida", schema = "corralon_dev", catalog = "")
public class UnidadMedida {
    private Integer idUnidadMedida;
    private String nombre;
    private String abreviatura;
    private Byte habiliotado;
    private Collection<Articulo> articulosByIdUnidadMedida;

    @Id
    @Column(name = "id_unidad_medida")
    public Integer getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(Integer idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
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
    @Column(name = "habiliotado")
    public Byte getHabiliotado() {
        return habiliotado;
    }

    public void setHabiliotado(Byte habiliotado) {
        this.habiliotado = habiliotado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnidadMedida that = (UnidadMedida) o;
        return Objects.equals(idUnidadMedida, that.idUnidadMedida) &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(abreviatura, that.abreviatura) &&
                Objects.equals(habiliotado, that.habiliotado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUnidadMedida, nombre, abreviatura, habiliotado);
    }

    @OneToMany(mappedBy = "unidadMedidaByIdUnidadMedida")
    public Collection<Articulo> getArticulosByIdUnidadMedida() {
        return articulosByIdUnidadMedida;
    }

    public void setArticulosByIdUnidadMedida(Collection<Articulo> articulosByIdUnidadMedida) {
        this.articulosByIdUnidadMedida = articulosByIdUnidadMedida;
    }
}
