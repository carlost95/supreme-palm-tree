package com.undec.corralon.repository;

import com.undec.corralon.modelo.CuentaBancaria;
import com.undec.corralon.modelo.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Integer> {
    @Query("select c from Direccion c where c.cliente.idCliente = :id")
    List<Direccion> findAllDireccionesByIdCliente(@Param("id") Integer id);

    @Query("select c from Direccion c where c.cliente.idCliente = :id and c.status = true ")
    List<Direccion> findAllEnabledDireccionesByIdCliente(@Param("id") Integer id);
}