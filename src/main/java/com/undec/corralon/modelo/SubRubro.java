package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.DateAudit;
import com.undec.corralon.modelo.audit.UserDateAudit;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sub_rubro")
public class SubRubro extends UserDateAudit {
    private Integer idSubRubro;
    private String nombre;
    private String abreviatura;
    private Boolean habilitado;
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


    @ManyToOne
    @JoinColumn(name = "id_rubro", referencedColumnName = "id_rubro")
    public Rubro getRubroByIdRubro() {
        return rubroByIdRubro;
    }

    public void setRubroByIdRubro(Rubro rubroByIdRubro) {
        this.rubroByIdRubro = rubroByIdRubro;
    }
}
