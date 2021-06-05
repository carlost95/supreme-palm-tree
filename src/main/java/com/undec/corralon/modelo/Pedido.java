package com.undec.corralon.modelo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Pedido {
    private Integer idPedido;
    private String nombre;
    private String descripcion;
    private Timestamp fecha;
    private Collection<DetallePedido> detallePedidosByIdPedido;
    private Collection<MovimientoArticulo> movimientoArticulosByIdPedido;

    @Id
    @Column(name = "id_pedido")
    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "fecha")
    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(idPedido, pedido.idPedido) &&
                Objects.equals(nombre, pedido.nombre) &&
                Objects.equals(descripcion, pedido.descripcion) &&
                Objects.equals(fecha, pedido.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedido, nombre, descripcion, fecha);
    }

    @OneToMany(mappedBy = "pedidoByIdPedido")
    public Collection<DetallePedido> getDetallePedidosByIdPedido() {
        return detallePedidosByIdPedido;
    }

    public void setDetallePedidosByIdPedido(Collection<DetallePedido> detallePedidosByIdPedido) {
        this.detallePedidosByIdPedido = detallePedidosByIdPedido;
    }

    @OneToMany(mappedBy = "pedidoByIdPedido")
    public Collection<MovimientoArticulo> getMovimientoArticulosByIdPedido() {
        return movimientoArticulosByIdPedido;
    }

    public void setMovimientoArticulosByIdPedido(Collection<MovimientoArticulo> movimientoArticulosByIdPedido) {
        this.movimientoArticulosByIdPedido = movimientoArticulosByIdPedido;
    }
}
