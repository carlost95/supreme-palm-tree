package com.undec.corralon.repository;

import com.undec.corralon.modelo.Ajuste;
import com.undec.corralon.modelo.DetalleAjuste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleAjusteRepository extends JpaRepository<DetalleAjuste, Integer> {
    List <DetalleAjuste> findByAjusteByIdAjuste(Ajuste ajuste);
}
