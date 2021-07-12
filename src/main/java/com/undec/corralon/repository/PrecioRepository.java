package com.undec.corralon.repository;

import com.undec.corralon.modelo.PrecioArticulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecioRepository extends JpaRepository<PrecioArticulo, Integer> {
}
