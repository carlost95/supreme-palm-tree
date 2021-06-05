package com.undec.corralon.modelo;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Cheque {
    private Integer idCheque;
    private Integer idBanco;
    private String titularEmisor;
    private Integer idTipoCheque;
    private Date fecha;
    private Date fechaEmision;
    private Date fechaVenciomiento;
    private Date fechaCobro;
    private Banco bancoByIdBanco;
    private TipoCheque tipoChequeByIdTipoCheque;
    private Collection<PagoVenta> pagoVentasByIdCheque;

    @Id
    @Column(name = "id_cheque")
    public Integer getIdCheque() {
        return idCheque;
    }

    public void setIdCheque(Integer idCheque) {
        this.idCheque = idCheque;
    }

    @Basic
    @Column(name = "id_banco")
    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    @Basic
    @Column(name = "titular_emisor")
    public String getTitularEmisor() {
        return titularEmisor;
    }

    public void setTitularEmisor(String titularEmisor) {
        this.titularEmisor = titularEmisor;
    }

    @Basic
    @Column(name = "id_tipo_cheque")
    public Integer getIdTipoCheque() {
        return idTipoCheque;
    }

    public void setIdTipoCheque(Integer idTipoCheque) {
        this.idTipoCheque = idTipoCheque;
    }

    @Basic
    @Column(name = "fecha")
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "fecha_emision")
    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    @Basic
    @Column(name = "fecha_venciomiento")
    public Date getFechaVenciomiento() {
        return fechaVenciomiento;
    }

    public void setFechaVenciomiento(Date fechaVenciomiento) {
        this.fechaVenciomiento = fechaVenciomiento;
    }

    @Basic
    @Column(name = "fecha_cobro")
    public Date getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(Date fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cheque cheque = (Cheque) o;
        return Objects.equals(idCheque, cheque.idCheque) &&
                Objects.equals(idBanco, cheque.idBanco) &&
                Objects.equals(titularEmisor, cheque.titularEmisor) &&
                Objects.equals(idTipoCheque, cheque.idTipoCheque) &&
                Objects.equals(fecha, cheque.fecha) &&
                Objects.equals(fechaEmision, cheque.fechaEmision) &&
                Objects.equals(fechaVenciomiento, cheque.fechaVenciomiento) &&
                Objects.equals(fechaCobro, cheque.fechaCobro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCheque, idBanco, titularEmisor, idTipoCheque, fecha, fechaEmision, fechaVenciomiento, fechaCobro);
    }

    @ManyToOne
    @JoinColumn(name = "id_banco", referencedColumnName = "id_banco", nullable = false)
    public Banco getBancoByIdBanco() {
        return bancoByIdBanco;
    }

    public void setBancoByIdBanco(Banco bancoByIdBanco) {
        this.bancoByIdBanco = bancoByIdBanco;
    }

    @ManyToOne
    @JoinColumn(name = "id_tipo_cheque", referencedColumnName = "id_tipo_cheque", nullable = false)
    public TipoCheque getTipoChequeByIdTipoCheque() {
        return tipoChequeByIdTipoCheque;
    }

    public void setTipoChequeByIdTipoCheque(TipoCheque tipoChequeByIdTipoCheque) {
        this.tipoChequeByIdTipoCheque = tipoChequeByIdTipoCheque;
    }

    @OneToMany(mappedBy = "chequeByIdCheque")
    public Collection<PagoVenta> getPagoVentasByIdCheque() {
        return pagoVentasByIdCheque;
    }

    public void setPagoVentasByIdCheque(Collection<PagoVenta> pagoVentasByIdCheque) {
        this.pagoVentasByIdCheque = pagoVentasByIdCheque;
    }
}
