package com.undec.corralon.DTO;

import java.util.Date;
import java.util.List;

public class AjusteDTO {
    private int idAjusteDTO;
    private String nombre;
    private Date fecha;
    private String descripcion;
    List<DetalleTipoMovimientoDTO> detallesAjuste;

    public int getIdAjusteDTO() {
        return idAjusteDTO;
    }

    public void setIdAjusteDTO(int idAjusteDTO) {
        this.idAjusteDTO = idAjusteDTO;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<DetalleTipoMovimientoDTO> getDetallesAjuste() {
        return detallesAjuste;
    }

    public void setDetallesAjuste(List<DetalleTipoMovimientoDTO> detallesAjuste) {
        this.detallesAjuste = detallesAjuste;
    }
}
