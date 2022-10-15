package com.undec.corralon.repository;

import com.undec.corralon.modelo.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
    @Query (value = "SELECT * FROM detalle_venta WHERE id_venta = :idVenta", nativeQuery = true)
    List<DetalleVenta> findByIdVenta(@Param("idVenta") Integer idVenta);
}
