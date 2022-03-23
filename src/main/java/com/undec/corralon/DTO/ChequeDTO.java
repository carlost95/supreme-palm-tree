package com.undec.corralon.DTO;

import java.util.Date;

public class ChequeDTO {
    private Integer idCheque;
    private String titularEmisor;
    private Date fecha;
    private Date fechaEmision;
    private Date fechaVenciomiento;
    private Date fechaCobro;
    private Integer idBanco;
    private Integer idTipoCheque;

    public Integer getIdCheque() {
        return idCheque;
    }

    public void setIdCheque(Integer idCheque) {
        this.idCheque = idCheque;
    }

    public String getTitularEmisor() {
        return titularEmisor;
    }

    public void setTitularEmisor(String titularEmisor) {
        this.titularEmisor = titularEmisor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVenciomiento() {
        return fechaVenciomiento;
    }

    public void setFechaVenciomiento(Date fechaVenciomiento) {
        this.fechaVenciomiento = fechaVenciomiento;
    }

    public Date getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(Date fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Integer getIdTipoCheque() {
        return idTipoCheque;
    }

    public void setIdTipoCheque(Integer idTipoCheque) {
        this.idTipoCheque = idTipoCheque;
    }
}
