package com.undec.corralon.repository;

import com.undec.corralon.modelo.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
    List<Marca> findAllByHabilitacionEquals(Integer habilitacion);
}
