package com.undec.corralon.repository;

import com.undec.corralon.modelo.SubRubro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubRubroRepository extends JpaRepository<SubRubro, Integer> {
    List<SubRubro> findAllByHabilitadoEquals(boolean habilitado);

    List<SubRubro> findAllByRubroByIdRubro(Integer id);

    Boolean existsDistinctByNombreOrAbreviatura(String nombre, String abreviatura);
    Boolean existsDistinctByNombreAndIdSubRubroNot(String nombre, Integer idSubRubro);
    Boolean existsDistinctByAbreviaturaAndIdSubRubroNot(String abreviatura, Integer idSubRubro);
}
