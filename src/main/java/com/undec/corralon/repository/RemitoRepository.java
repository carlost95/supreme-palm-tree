package com.undec.corralon.repository;

import com.undec.corralon.modelo.Remito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RemitoRepository extends JpaRepository<Remito, Integer> {
    List<Remito> findAllByEntregadoEquals(Boolean status);
    @Query(value = "SELECT r.nro_remito FROM remito r order by r.nro_remito desc limit 1", nativeQuery = true)
    Long findLastRemitoNumber();

}
