package com.undec.corralon.repository;

import com.undec.corralon.modelo.Articulo;
import com.undec.corralon.modelo.MovimientoArticulo;
import org.exolab.castor.types.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

@Repository
public interface MovimientoArticuloRepository extends JpaRepository<MovimientoArticulo, Integer> {
    //VERIFICAR QUERY POR NUEVA DISPOSION EN LA BASE DE DATOS
    @Query("SELECT SUM(m.movimiento)  from MovimientoArticulo m WHERE m.articuloByIdArticulo = :articulo and m.fecha <= :fecha and" +
            "(m.detalleAjusteByIdDetalleAjuste IS NOT NULL OR m.detallePedidoByIdDetallePedido IS NOT NULL OR m.detalleRemitoByIdDetalleRemito IS NOT NULL) ")
    Double stockPorArticulo(@Param("articulo") Articulo articulo, @Param("fecha") Date fechaPedido);
//
//    List<MovimientoArticulo> findAllByPedidoByIdPedido( Integer pedidoId);
//
//    List<MovimientoArticulo> findAllByAjusteByIdAjuste( Integer ajusteId);
}
