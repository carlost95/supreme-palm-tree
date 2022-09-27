package com.undec.corralon.repository;

import com.undec.corralon.modelo.Articulo;
import com.undec.corralon.modelo.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {
    List<Articulo> findArticuloByHabilitadoEquals(boolean habilitado);
    Boolean existsByCodigo(String codigo);
    Articulo findArticuloByCodigo(String codigo);

    Boolean existsArticuloByCodigoAndIdArticuloNot(String codigo, Integer idArticulo);
    // TODO: Reemplazar por findArticuloByCodigo
    @Deprecated
    @Query("select a from Articulo a where a.nombre = :nombre and a.codigo = :codigo")
    Articulo findArticuloForCodigo(@Param("nombre") String nombre,@Param("codigo") String codigo);
    List<Articulo> findArticulosByProveedorByIdProveedorAndHabilitadoTrue(Proveedor proveedor);
}
