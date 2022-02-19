package com.undec.corralon.DTO;

public class ChequeDTO {
    private Integer idCheque;
    private String titularEmisor;
    private String fecha;
    private String fechaEmision;
    private String fechaVenciomiento;
    private String fechaCobro;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFechaVenciomiento() {
        return fechaVenciomiento;
    }

    public void setFechaVenciomiento(String fechaVenciomiento) {
        this.fechaVenciomiento = fechaVenciomiento;
    }

    public String getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(String fechaCobro) {
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
