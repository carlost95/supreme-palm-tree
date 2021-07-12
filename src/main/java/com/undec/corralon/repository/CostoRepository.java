package com.undec.corralon.repository;

import com.undec.corralon.modelo.CostoArticulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostoRepository extends JpaRepository<CostoArticulo,Integer> {
}
