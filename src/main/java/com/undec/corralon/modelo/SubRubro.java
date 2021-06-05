package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "sub_rubro", schema = "corralon_dev", catalog = "")
public class SubRubro {
    private Integer idSubRubro;
    private String nombre;
    private String abreviatura;
    private Byte habilitado;
    private Integer idRubro;
    private Collection<Articulo> articulosByIdSubRubro;
    private Rubro rubroByIdRubro;

    @Id
    @Column(name = "id_sub_rubro")
    public Integer getIdSubRubro() {
        return idSubRubro;
    }

    public void setIdSubRubro(Integer idSubRubro) {
        this.idSubRubro = idSubRubro;
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

    @Basic
    @Column(name = "id_rubro")
    public Integer getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(Integer idRubro) {
        this.idRubro = idRubro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubRubro subRubro = (SubRubro) o;
        return Objects.equals(idSubRubro, subRubro.idSubRubro) &&
                Objects.equals(nombre, subRubro.nombre) &&
                Objects.equals(abreviatura, subRubro.abreviatura) &&
                Objects.equals(habilitado, subRubro.habilitado) &&
                Objects.equals(idRubro, subRubro.idRubro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSubRubro, nombre, abreviatura, habilitado, idRubro);
    }

    @OneToMany(mappedBy = "subRubroByIdSubRubro")
    public Collection<Articulo> getArticulosByIdSubRubro() {
        return articulosByIdSubRubro;
    }

    public void setArticulosByIdSubRubro(Collection<Articulo> articulosByIdSubRubro) {
        this.articulosByIdSubRubro = articulosByIdSubRubro;
    }

    @ManyToOne
    @JoinColumn(name = "id_rubro", referencedColumnName = "id_rubro")
    public Rubro getRubroByIdRubro() {
        return rubroByIdRubro;
    }

    public void setRubroByIdRubro(Rubro rubroByIdRubro) {
        this.rubroByIdRubro = rubroByIdRubro;
    }
}
