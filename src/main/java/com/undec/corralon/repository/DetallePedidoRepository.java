package com.undec.corralon.repository;

import com.undec.corralon.modelo.DetallePedido;
import com.undec.corralon.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer >  {
    List<DetallePedido> findByPedidoByIdPedido(Pedido pedido);
}
