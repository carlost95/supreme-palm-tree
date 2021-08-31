package com.undec.corralon.repository;

import com.undec.corralon.modelo.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer >  {
}
