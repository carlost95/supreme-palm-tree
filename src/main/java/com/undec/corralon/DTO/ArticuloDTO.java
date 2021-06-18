package com.undec.corralon.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArticuloDTO {

    @JsonProperty(value = "id", required = false)
    private Integer id;
    private String nombre;
    private String abreviatura;
    private String codigoArt;
    private Integer stockMin;
    private Integer stockMax;

    @JsonProperty(value = "proveedorId", required = false)
    private Integer idProveedor;
    @JsonProperty(value = "unidadMedidaId", required = false)
    private Integer idUnidadMedida;
    @JsonProperty(value = "marcaId", required = false)
    private Integer idMarca;
    @JsonProperty(value = "rubroId", required = false)
    private Integer idRubro;
    @JsonProperty(value = "subRubroId", required = false)
    private Integer idSubRubro;
    @JsonProperty(value = "costoId", required = false)
    private Integer idCosto;
    @JsonProperty(value = "precioId", required = false)
    private Integer idPrecio;
    @JsonProperty(value = "habilitado", required = false)
    private Integer habilitado;

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

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(Integer idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Integer getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(Integer idRubro) {
        this.idRubro = idRubro;
    }

    public Integer getIdSubRubro() {
        return idSubRubro;
    }

    public void setIdSubRubro(Integer idSubRubro) {
        this.idSubRubro = idSubRubro;
    }

    public Integer getIdCosto() {
        return idCosto;
    }

    public void setIdCosto(Integer idCosto) {
        this.idCosto = idCosto;
    }

    public Integer getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(Integer idPrecio) {
        this.idPrecio = idPrecio;
    }

    public Integer getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Integer habilitado) {
        this.habilitado = habilitado;
    }
}
