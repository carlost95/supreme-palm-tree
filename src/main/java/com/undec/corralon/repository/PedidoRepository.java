package com.undec.corralon.repository;

import com.undec.corralon.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer > {
    List<Pedido> findByHabilitadoEquals(boolean habilitado);

    @Query(value = "select count(*) as pedidos , date_format(p.fecha, '%Y') as anio, date_format(p.fecha, '%m') as mes from pedido p where p.fecha >= :fechaInicial and p.fecha <=  :fechaFinal  group by month (fecha)", nativeQuery = true)
    List<List<Integer>> obtenerPedidosFecha(@Param("fechaInicial") Date fechaInicial, @Param("fechaFinal") Date fechaFinal);
}
