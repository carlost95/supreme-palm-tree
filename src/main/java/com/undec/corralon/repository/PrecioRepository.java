package com.undec.corralon.repository;

import com.undec.corralon.modelo.Articulo;
import com.undec.corralon.modelo.PrecioArticulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecioRepository extends JpaRepository<PrecioArticulo, Integer> {
    @Query("SELECT pa  from PrecioArticulo pa WHERE pa.articuloByIdArticulo = :articulo "+
            "and pa.fechaHasta is null")
    PrecioArticulo findPrecioArticuloByIdArtculo(@Param("articulo") Articulo articulo);
}
