package com.undec.corralon.repository;

import com.undec.corralon.DTO.VentaPorMes;
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

    @Query(value = "select count(*) as ventas , month (v.fecha_venta) as mes from venta v where year (v.fecha_venta) = :fecha group by month (v.fecha_venta)", nativeQuery = true)
    List<List<Integer>> obtenerVentasPorMes(@Param("fecha") Integer fecha);

//    @Query(value = "select count(*) as ventas , month (v.fecha_venta) as mes from venta v where year (fecha_venta) = :fecha group by month (fecha_venta)", nativeQuery = true)
//    List<List<Integer>> obtenerVentasPorMes(@Param("fecha") Date fecha);

    @Query(value = "select count(*) as ventas , month (v.fecha_venta) as mes from venta v where v.fecha_venta >= :fechaInicial and fecha_venta <=  :fechaFinal group by month(fecha_venta)", nativeQuery = true)
    List<List<Integer>> obtenerVentas(@Param("fechaInicial") Date fechaInicial, @Param("fechaFinal") Date fechaFinal);

}
