package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.DateAudit;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Cheque extends DateAudit {
    private Integer idCheque;
    private String titularEmisor;
    private String fecha;
    private String fechaEmision;
    private String fechaVenciomiento;
    private String fechaCobro;
    private Boolean habilitado;
    private Banco bancoByIdBanco;
    private TipoCheque tipoChequeByIdTipoCheque;
//    private List<PagoVenta> pagoVentasByIdCheque;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
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
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "fecha_emision")
    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    @Basic
    @Column(name = "fecha_venciomiento")
    public String getFechaVenciomiento() {
        return fechaVenciomiento;
    }

    public void setFechaVenciomiento(String fechaVenciomiento) {
        this.fechaVenciomiento = fechaVenciomiento;
    }

    @Basic
    @Column(name = "fecha_cobro")
    public String getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(String fechaCobro) {
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

//    @OneToMany(mappedBy = "chequeByIdCheque")
//    public List<PagoVenta> getPagoVentasByIdCheque() {
//        return pagoVentasByIdCheque;
//    }
//
//    public void setPagoVentasByIdCheque(List<PagoVenta> pagoVentasByIdCheque) {
//        this.pagoVentasByIdCheque = pagoVentasByIdCheque;
//    }
}
