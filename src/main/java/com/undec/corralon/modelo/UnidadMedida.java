package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "unidad_medida")
public class UnidadMedida extends DateAudit{
    private Integer idUnidadMedida;
    private String nombre;
    private String abreviatura;
    private boolean habilitado;
    private List<Articulo> articulosByIdUnidadMedida;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        UnidadMedida that = (UnidadMedida) o;
        return Objects.equals(idUnidadMedida, that.idUnidadMedida) &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(abreviatura, that.abreviatura) &&
                Objects.equals(habilitado, that.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUnidadMedida, nombre, abreviatura, habilitado);
    }

    @OneToMany(mappedBy = "unidadMedidaByIdUnidadMedida")
    public List<Articulo> getArticulosByIdUnidadMedida() {
        return articulosByIdUnidadMedida;
    }

    public void setArticulosByIdUnidadMedida(List<Articulo> articulosByIdUnidadMedida) {
        this.articulosByIdUnidadMedida = articulosByIdUnidadMedida;
    }
}
