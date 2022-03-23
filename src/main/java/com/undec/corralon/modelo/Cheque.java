package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.UserDateAudit;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Cheque extends UserDateAudit {
    private Integer idCheque;
    private String titularEmisor;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmision;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVenciomiento;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCobro;
    private Boolean habilitado;
    private Banco bancoByIdBanco;
    private TipoCheque tipoChequeByIdTipoCheque;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cheque")
    public Integer getIdCheque() {
        return idCheque;
    }

    public void setIdCheque(Integer idCheque) {
        this.idCheque = idCheque;
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
        Cheque cheque = (Cheque) o;
        return Objects.equals(idCheque, cheque.idCheque) &&
                Objects.equals(titularEmisor, cheque.titularEmisor) &&
                Objects.equals(fecha, cheque.fecha) &&
                Objects.equals(fechaEmision, cheque.fechaEmision) &&
                Objects.equals(fechaVenciomiento, cheque.fechaVenciomiento) &&
                Objects.equals(fechaCobro, cheque.fechaCobro) &&
                Objects.equals(habilitado, cheque.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCheque, titularEmisor, fecha, fechaEmision, fechaVenciomiento, fechaCobro, habilitado);
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
}