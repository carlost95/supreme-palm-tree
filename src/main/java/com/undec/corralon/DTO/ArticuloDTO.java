package com.undec.corralon.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArticuloDTO {

    @JsonProperty(value = "id", required = false)
    private Integer id ;
    private String nombre;
    private String abreviatura;
    private String codigoArt;
    private Integer stockMin;
    private Integer stockMax;
    @JsonProperty(value = "proveedorId", required = false)
    private Integer proveedorId;
    @JsonProperty(value = "unidadMedidaId", required = false)
    private Integer unidadMedidaId;
    @JsonProperty(value = "marcaId",required = false)
    private Integer marcaId;
    @JsonProperty(value = "rubroId",required = false)
    private Integer rubroId;
    @JsonProperty(value = "subRubroId",required = false)
    private Integer subRubroId;
    @JsonProperty(value = "habilitacion",required = false)
    private Integer habilitacion;

    public ArticuloDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getCodigoArt() {
        return codigoArt;
    }

    public void setCodigoArt(String codigoArt) {
        this.codigoArt = codigoArt;
    }

    public Integer getStockMin() {
        return stockMin;
    }

    public void setStockMin(Integer stockMin) {
        this.stockMin = stockMin;
    }

    public Integer getStockMax() {
        return stockMax;
    }

    public void setStockMax(Integer stockMax) {
        this.stockMax = stockMax;
    }

    public Integer getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Integer getUnidadMedidaId() {
        return unidadMedidaId;
    }

    public void setUnidadMedidaId(Integer unidadMedidaId) {
        this.unidadMedidaId = unidadMedidaId;
    }

    public Integer getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Integer marcaId) {
        this.marcaId = marcaId;
    }

    public Integer getRubroId() {
        return rubroId;
    }

    public void setRubroId(Integer rubroId) {
        this.rubroId = rubroId;
    }

    public Integer getSubRubroId() {
        return subRubroId;
    }

    public void setSubRubroId(Integer subRubroId) {
        this.subRubroId = subRubroId;
    }

    public Integer getHabilitacion() {
        return habilitacion;
    }

    public void setHabilitacion(Integer habilitacion) {
        this.habilitacion = habilitacion;
    }
}
