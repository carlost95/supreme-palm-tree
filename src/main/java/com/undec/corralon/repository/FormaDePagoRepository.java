package com.undec.corralon.repository;

import com.undec.corralon.modelo.FormaPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormaDePagoRepository extends JpaRepository<FormaPago, Integer> {
    List<FormaPago> findByHabilitacionEquals(Integer habilitacion);
}
