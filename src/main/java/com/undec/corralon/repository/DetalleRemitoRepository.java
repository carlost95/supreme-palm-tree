package com.undec.corralon.repository;

import com.undec.corralon.modelo.DetalleRemito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleRemitoRepository extends JpaRepository<DetalleRemito, Integer > {
}
