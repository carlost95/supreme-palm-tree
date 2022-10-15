package com.undec.corralon.repository;

import com.undec.corralon.modelo.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    List <Empresa> findAllByStatusEquals(Boolean status);
    Empresa findByCuit(String cuit);

}
