package com.undec.corralon.repository;

import com.undec.corralon.modelo.MovimientoArticulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface MovimientoArticuloRepository extends JpaRepository<MovimientoArticulo, Integer> {
    List<MovimientoArticulo> findMovimientoArticuloByPedidoId_IdEqualsAndFechaBefore(Integer id, String fecha);

    @Query("SELECT SUM(m.movimiento) FROM MovimientoArticulo m WHERE m.articuloId.id = :id and m.articuloId.habilitacion = 1 and m.fecha <= :fechaPedido")
    Double stockPorArticulo(@Param("id") Integer idArticulo, @Param("fechaPedido") String fechaPedido);

//    @Query("SELECT m FROM MovimientoArticulo m WHERE m.articuloId.id = :idArticulo and m.pedidoId.id = :idPedido")
//    MovimientoArticulo buscarPorArticuloYPedido(@Param("idArticulo") Integer idArticulo, @Param("idPedido") Integer idPedido);

//    MovimientoArticulo findByArticuloIdEquals_IdAndPedidoId_IdEquals(Integer idArticulo, Integer idPedido);


//    @Query("SELECT m.articuloId FROM MovimientoArticulo m WHERE m.pedidoId.id = :idPedido")
//    MovimientoArticulo buscarArticulos(@Param("idArticulo") Integer idArticulo, @Param("idPedido") Integer idPedido);

    List<MovimientoArticulo> findAllByPedidoId_Id( Integer pedidoId);
}
