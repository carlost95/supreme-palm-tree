package com.undec.corralon.repository;

import com.undec.corralon.modelo.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Integer> {
//    List<Direccion> findDireccionByClienteId(Integer id);
    List<Direccion> findDireccionByClienteByIdCliente(Integer id);
}