package com.undec.corralon.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FechaReporte {

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd", timezone = "America/Argentina/Buenos_Aires")
    Date fechaFinal;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd", timezone = "America/Argentina/Buenos_Aires")
    Date fechaInicial;

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
}
