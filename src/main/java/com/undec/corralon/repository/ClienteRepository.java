package com.undec.corralon.repository;

import com.undec.corralon.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findAllByHabilitacionEquals(Integer habilitacion);
}
