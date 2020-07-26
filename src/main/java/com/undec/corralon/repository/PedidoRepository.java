package com.undec.corralon.repository;

import com.undec.corralon.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer > {
    List<Pedido> findByHabilitacionEquals(Integer habilitacion);
}
