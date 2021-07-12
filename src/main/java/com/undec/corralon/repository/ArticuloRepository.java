package com.undec.corralon.repository;

import com.undec.corralon.modelo.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo,Integer> {
    List<Articulo> findArticuloByHabilitadoEquals(boolean habilitado);
    Boolean existsByNombreOrAbreviaturaOrCodigo(String nombre, String abreviatura, String codigo);
}
