package com.undec.corralon.repository;

import com.undec.corralon.modelo.Departamento;
import com.undec.corralon.modelo.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito, Integer> {
    List<Distrito> findByHabilitadoEquals(boolean habilitado);

    Boolean existsDistinctByNombreOrAbreviatura(String nombre, String abreviatura);

    Boolean existsDistinctByNombreAndIdDistritoNot(String nombre, Integer idDistrito);

    Boolean existsDistinctByAbreviaturaAndIdDistritoNot(String abreviatura, Integer idDistrito);
}
