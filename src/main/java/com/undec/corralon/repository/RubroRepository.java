package com.undec.corralon.repository;

import com.undec.corralon.modelo.Rubro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RubroRepository extends JpaRepository<Rubro, Integer> {

    List<Rubro> findByHabilitacionEquals(Integer habilitacion);
}
