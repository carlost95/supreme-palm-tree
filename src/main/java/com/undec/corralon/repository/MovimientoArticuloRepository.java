package com.undec.corralon.repository;

import com.undec.corralon.modelo.MovimientoArticulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface MovimientoArticuloRepository extends JpaRepository<MovimientoArticulo, Integer> {
//VERIFICAR QUERY POR NUEBA DISPOSION EN LA BASE DE DATOS
    //    @Query("SELECT SUM(m.movimiento)  from MovimientoArticulo m WHERE m.articuloByIdArticulo = :idArticulo and m.fecha <= :fechaPedido and" +
//            "(m.ajusteByIdAjuste IS NOT NULL OR m.pedidoByIdPedido IS NOT NULL OR m.remitoByIdRemito IS NOT NULL) ")
//    Double stockPorArticulo(@Param("idArticulo") Integer idArticulo, @Param("fechaPedido") Timestamp fechaPedido);
//
//    List<MovimientoArticulo> findAllByPedidoByIdPedido( Integer pedidoId);
//
//    List<MovimientoArticulo> findAllByAjusteByIdAjuste( Integer ajusteId);
}
