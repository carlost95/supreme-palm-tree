package com.undec.corralon.repository;

import com.undec.corralon.DTO.DataReporte;
import com.undec.corralon.modelo.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    @Query(value = "SELECT v.nro_venta FROM venta v order by v.nro_venta desc limit 1", nativeQuery = true)
    Long findLastSaleNumber();

    @Query(value = "select count(*) as ventas , date_format(v.fecha_venta, '%Y') as anio, date_format(v.fecha_venta, '%m') as mes from venta v where v.fecha_venta >= :fechaInicial and v.fecha_venta <=  :fechaFinal group by month (fecha_venta)", nativeQuery = true)
    List<List<Integer>> obtenerVentasFecha(@Param("fechaInicial") Date fechaInicial, @Param("fechaFinal") Date fechaFinal);
}
