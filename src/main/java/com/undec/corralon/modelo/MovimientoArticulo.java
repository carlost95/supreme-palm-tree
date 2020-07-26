package com.undec.corralon.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "movimiento_articulo", schema = "santo_domingo_corralon")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MovimientoArticulo {
    private Integer id;
    private String fecha;
    private Integer movimiento;
    private Articulo articuloId;
    private Ajustes ajusteId;
    private Pedido pedidoId;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "movimiento")
    public Integer getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Integer movimiento) {
        this.movimiento = movimiento;
    }

    @ManyToOne
    @JoinColumn(name = "articulo_id", referencedColumnName = "id")
    public Articulo getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(Articulo articuloId) {
        this.articuloId = articuloId;
    }

    @ManyToOne
    @JoinColumn(name = "ajuste_id", referencedColumnName = "id")
    public Ajustes getAjusteId() {
        return ajusteId;
    }

    public void setAjusteId(Ajustes ajusteId) {
        this.ajusteId = ajusteId;
    }

    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    public Pedido getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Pedido pedidoId) {
        this.pedidoId = pedidoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovimientoArticulo that = (MovimientoArticulo) o;
        return id == that.id &&
                movimiento == that.movimiento &&
                articuloId == that.articuloId &&
                Objects.equals(fecha, that.fecha) &&
                Objects.equals(ajusteId, that.ajusteId) &&
                Objects.equals(pedidoId, that.pedidoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, movimiento, articuloId, ajusteId, pedidoId);
    }

    @Override
    public String toString() {
        return "MovimientoArticulo{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", movimiento=" + movimiento +
                ", articuloId=" + articuloId +
                ", ajusteId=" + ajusteId +
                ", pedidoId=" + pedidoId +
                '}';
    }
}
