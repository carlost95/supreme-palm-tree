package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sub_rubro")
public class SubRubro extends DateAudit{
    private Integer idSubRubro;
    private String nombre;
    private String abreviatura;
    private Boolean habilitado;
//    private List<Articulo> articulosByIdSubRubro;
    private Rubro rubroByIdRubro;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubRubro subRubro = (SubRubro) o;
        return Objects.equals(idSubRubro, subRubro.idSubRubro) &&
                Objects.equals(nombre, subRubro.nombre) &&
                Objects.equals(abreviatura, subRubro.abreviatura) &&
                Objects.equals(habilitado, subRubro.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSubRubro, nombre, abreviatura, habilitado);
    }

//    @OneToMany(mappedBy = "subRubroByIdSubRubro")
//    @JoinColumn(name = "id_sub_rubro", referencedColumnName = "id_sub_rubro")
//    public List<Articulo> getArticulosByIdSubRubro() {
//        return articulosByIdSubRubro;
//    }
//
//    public void setArticulosByIdSubRubro(List<Articulo> articulosByIdSubRubro) {
//        this.articulosByIdSubRubro = articulosByIdSubRubro;
//    }

    @ManyToOne
    @JoinColumn(name = "id_rubro", referencedColumnName = "id_rubro")
    public Rubro getRubroByIdRubro() {
        return rubroByIdRubro;
    }

    public void setRubroByIdRubro(Rubro rubroByIdRubro) {
        this.rubroByIdRubro = rubroByIdRubro;
    }
}
