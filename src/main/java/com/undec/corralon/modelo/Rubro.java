package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Rubro extends DateAudit{
    private Integer idRubro;
    private String nombre;
    private String abreviatura;
    private boolean habilitado;
    private List<Articulo> articulosByIdRubro;
    private List<SubRubro> subRubrosByIdRubro;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public List<Articulo> getArticulosByIdRubro() {
        return articulosByIdRubro;
    }

    public void setArticulosByIdRubro(List<Articulo> articulosByIdRubro) {
        this.articulosByIdRubro = articulosByIdRubro;
    }

    @OneToMany(mappedBy = "rubroByIdRubro")
    public List<SubRubro> getSubRubrosByIdRubro() {
        return subRubrosByIdRubro;
    }

    public void setSubRubrosByIdRubro(List<SubRubro> subRubrosByIdRubro) {
        this.subRubrosByIdRubro = subRubrosByIdRubro;
    }
}
