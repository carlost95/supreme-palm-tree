package com.undec.corralon.modelo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Pedido extends DateAudit {
    private Integer idPedido;
    private String nombre;
    private String descripcion;
    private Timestamp fecha;
    private boolean habilitado;
    private List<DetallePedido> detallePedidosByIdPedido;
    private List<MovimientoArticulo> movimientoArticulosByIdPedido;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Basic
    @Column(name = "habilitado")
    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(idPedido, pedido.idPedido) &&
                Objects.equals(nombre, pedido.nombre) &&
                Objects.equals(descripcion, pedido.descripcion) &&
                Objects.equals(fecha, pedido.fecha) &&
                Objects.equals(habilitado, pedido.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedido, nombre, descripcion, fecha, habilitado);
    }

    @OneToMany(mappedBy = "pedidoByIdPedido")
    public List<DetallePedido> getDetallePedidosByIdPedido() {
        return detallePedidosByIdPedido;
    }

    public void setDetallePedidosByIdPedido(List<DetallePedido> detallePedidosByIdPedido) {
        this.detallePedidosByIdPedido = detallePedidosByIdPedido;
    }

    @OneToMany(mappedBy = "pedidoByIdPedido")
    public List<MovimientoArticulo> getMovimientoArticulosByIdPedido() {
        return movimientoArticulosByIdPedido;
    }

    public void setMovimientoArticulosByIdPedido(List<MovimientoArticulo> movimientoArticulosByIdPedido) {
        this.movimientoArticulosByIdPedido = movimientoArticulosByIdPedido;
    }
}
