package com.undec.corralon.modelo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Ajuste  extends DateAudit{
    private Integer idAjuste;
    private String nombre;
    private String descripcion;
    private Boolean habilitado;
    private String fecha;
//    private List<DetalleAjuste> detalleAjustesByIdAjuste;
//    private List<MovimientoArticulo> movimientoArticulosByIdAjuste;

    @Id
    @Column(name = "id_ajuste")
    public Integer getIdAjuste() {
        return idAjuste;
    }

    public void setIdAjuste(Integer idAjuste) {
        this.idAjuste = idAjuste;
    }

    @Basic
    @Column(name = "nombre")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Basic
    @Column(name = "habilitado")
    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Basic
    @Column(name = "fecha")
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ajuste ajuste = (Ajuste) o;
        return Objects.equals(idAjuste, ajuste.idAjuste) &&
                Objects.equals(nombre, ajuste.nombre) &&
                Objects.equals(descripcion, ajuste.descripcion) &&
                Objects.equals(habilitado, ajuste.habilitado) &&
                Objects.equals(fecha, ajuste.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAjuste, nombre, descripcion, habilitado, fecha);
    }

//    @OneToMany(mappedBy = "ajusteByIdAjuste")
//    public List<DetalleAjuste> getDetalleAjustesByIdAjuste() {
//        return detalleAjustesByIdAjuste;
//    }
//
//    public void setDetalleAjustesByIdAjuste(List<DetalleAjuste> detalleAjustesByIdAjuste) {
//        this.detalleAjustesByIdAjuste = detalleAjustesByIdAjuste;
//    }
//
//    @OneToMany(mappedBy = "ajusteByIdAjuste")
//    public List<MovimientoArticulo> getMovimientoArticulosByIdAjuste() {
//        return movimientoArticulosByIdAjuste;
//    }
//
//    public void setMovimientoArticulosByIdAjuste(List<MovimientoArticulo> movimientoArticulosByIdAjuste) {
//        this.movimientoArticulosByIdAjuste = movimientoArticulosByIdAjuste;
//    }
}
