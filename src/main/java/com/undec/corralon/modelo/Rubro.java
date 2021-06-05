package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Rubro {
    private Integer idRubro;
    private String nombre;
    private String abreviatura;
    private Byte habilitado;
    private Collection<Articulo> articulosByIdRubro;
    private Collection<SubRubro> subRubrosByIdRubro;

    @Id
    @Column(name = "id_rubro")
    public Integer getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(Integer idRubro) {
        this.idRubro = idRubro;
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
    public Byte getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Byte habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rubro rubro = (Rubro) o;
        return Objects.equals(idRubro, rubro.idRubro) &&
                Objects.equals(nombre, rubro.nombre) &&
                Objects.equals(abreviatura, rubro.abreviatura) &&
                Objects.equals(habilitado, rubro.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRubro, nombre, abreviatura, habilitado);
    }

    @OneToMany(mappedBy = "rubroByIdRubro")
    public Collection<Articulo> getArticulosByIdRubro() {
        return articulosByIdRubro;
    }

    public void setArticulosByIdRubro(Collection<Articulo> articulosByIdRubro) {
        this.articulosByIdRubro = articulosByIdRubro;
    }

    @OneToMany(mappedBy = "rubroByIdRubro")
    public Collection<SubRubro> getSubRubrosByIdRubro() {
        return subRubrosByIdRubro;
    }

    public void setSubRubrosByIdRubro(Collection<SubRubro> subRubrosByIdRubro) {
        this.subRubrosByIdRubro = subRubrosByIdRubro;
    }
}
