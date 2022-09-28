package com.undec.corralon.repository;

import com.undec.corralon.modelo.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Integer> {
    List<UnidadMedida> findAllByHabilitadoEquals(boolean habilitacion);
    Boolean existsUnidadMedidaByNombreOrAbreviatura(String nombre, String abreviatura);
    Boolean existsUnidadMedidaByNombreAndIdUnidadMedidaNot(String nombre, Integer idUnidadMedida);
    Boolean existsUnidadMedidaByAbreviaturaAndIdUnidadMedidaNot(String abreviatura, Integer idUnidadMedida);
}
