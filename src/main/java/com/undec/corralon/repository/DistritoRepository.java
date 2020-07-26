package com.undec.corralon.repository;

import com.undec.corralon.modelo.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito, Integer> {
    List<Distrito> findAllByHabilitadoEquals(Integer habilitado);

}
