package com.undec.corralon.repository;

import com.undec.corralon.modelo.Articulo;
import com.undec.corralon.modelo.CostoArticulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostoRepository extends JpaRepository<CostoArticulo,Integer> {
    @Query("SELECT ca  from CostoArticulo ca WHERE ca.articuloByIdArticulo = :articulo "+
    "and ca.fechaHasta is null")
    CostoArticulo findCostoArticuloByIdArticulo(@Param("articulo") Articulo articulo);

    @Query("SELECT ca  from CostoArticulo ca WHERE ca.articuloByIdArticulo = :articulo ")
    List<CostoArticulo> findAllCostoArticuloByIdArticulo(@Param(value = "articulo") Articulo articulo);
}
