package com.undec.corralon.repository;

import com.undec.corralon.modelo.Remito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RemitoRepository extends JpaRepository<Remito, Integer> {
    List<Remito> findAllByEntregadoEquals(Boolean status);

    @Query(value = "SELECT r.nro_remito FROM remito r order by r.nro_remito desc limit 1", nativeQuery = true)
    Long findLastRemitoNumber();

    @Query(value = "select count(*) as remitos , date_format(r.fecha_remito, '%Y') as anio, date_format(r.fecha_remito, '%m') as mes from remito r where r.fecha_remito >= :fechaInicial and r.fecha_remito <=  :fechaFinal and r.entregado = 1 group by month (fecha_remito)", nativeQuery = true)
    List<List<Integer>> obtenerRemitosFecha(@Param("fechaInicial") Date fechaInicial, @Param("fechaFinal") Date fechaFinal);

}
