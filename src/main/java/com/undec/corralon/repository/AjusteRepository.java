package com.undec.corralon.repository;

import com.undec.corralon.modelo.Ajustes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AjusteRepository extends JpaRepository <Ajustes, Integer> {
    List <Ajustes> findAjustesByHabilitacionEquals(boolean habilitacion);

}
