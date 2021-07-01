package com.undec.corralon.repository;

import com.undec.corralon.modelo.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
    List<Departamento> findByHabilitadoEquals(boolean habilitado);

    Boolean existsByNombreAndAbreviatura(String nombre, String abreviatura);
}
