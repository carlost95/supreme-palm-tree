package com.undec.corralon.repository;

import com.undec.corralon.modelo.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    @Query (value = "SELECT v.nro_venta FROM venta v order by v.nro_venta desc limit 1", nativeQuery = true)
    Long findLastSaleNumber();

}
