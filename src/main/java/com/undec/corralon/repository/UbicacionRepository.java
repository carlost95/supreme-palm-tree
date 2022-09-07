package com.undec.corralon.repository;

import com.undec.corralon.modelo.CuentaBancaria;
import com.undec.corralon.modelo.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Integer> {
    @Query("select u from Ubicacion u where u.idUbicacion = :id")
    Ubicacion findUbicacionByIdDireccion(@Param("id") Integer id);
}
