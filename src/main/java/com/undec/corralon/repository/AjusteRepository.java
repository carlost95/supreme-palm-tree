package com.undec.corralon.repository;

import com.undec.corralon.modelo.Ajuste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AjusteRepository extends JpaRepository <Ajuste, Integer> {
//    List <Ajuste> findAjusteByHabilitadoEquals(boolean habilitado);

}
