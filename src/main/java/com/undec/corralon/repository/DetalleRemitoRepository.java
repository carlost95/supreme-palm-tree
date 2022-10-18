package com.undec.corralon.repository;

import com.undec.corralon.modelo.DetalleRemito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleRemitoRepository extends JpaRepository<DetalleRemito, Integer > {
    @Query(value = "SELECT * FROM detalle_remito WHERE id_remito = :idRemito", nativeQuery = true)
    List<DetalleRemito> findByIdRemito(@Param("idRemito") Integer idRemito);
}
