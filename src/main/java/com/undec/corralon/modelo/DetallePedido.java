package com.undec.corralon.modelo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido extends DateAudit{
    private Integer idDetallePedido;
    private Double cantidad;
    private Timestamp fecha;
    private Integer idPedido;
    private Integer idArticulo;
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
    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "id_pedido")
    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    @Basic
    @Column(name = "id_articulo")
    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetallePedido that = (DetallePedido) o;
        return Objects.equals(idDetallePedido, that.idDetallePedido) &&
                Objects.equals(cantidad, that.cantidad) &&
                Objects.equals(fecha, that.fecha) &&
                Objects.equals(idPedido, that.idPedido) &&
                Objects.equals(idArticulo, that.idArticulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetallePedido, cantidad, fecha, idPedido, idArticulo);
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
