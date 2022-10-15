package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.UserDateAudit;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido extends UserDateAudit {
    private Integer idDetallePedido;
    private Double cantidad;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    private Pedido pedidoByIdPedido;
    private Articulo articuloByIdArticulo;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_pedido")
    public Integer getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(Integer idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }


    @Basic
    @Column(name = "cantidad")
    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    @Basic
    @Column(name = "fecha")
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetallePedido that = (DetallePedido) o;
        return Objects.equals(idDetallePedido, that.idDetallePedido) &&
                Objects.equals(cantidad, that.cantidad) &&
                Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetallePedido, cantidad, fecha);
    }

    @ManyToOne
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido", nullable = false)
    public Pedido getPedidoByIdPedido() {
        return pedidoByIdPedido;
    }

    public void setPedidoByIdPedido(Pedido pedidoByIdPedido) {
        this.pedidoByIdPedido = pedidoByIdPedido;
    }

    @ManyToOne
    @JoinColumn(name = "id_articulo", referencedColumnName = "id_articulo", nullable = false)
    public Articulo getArticuloByIdArticulo() {
        return articuloByIdArticulo;
    }

    public void setArticuloByIdArticulo(Articulo articuloByIdArticulo) {
        this.articuloByIdArticulo = articuloByIdArticulo;
    }

}
