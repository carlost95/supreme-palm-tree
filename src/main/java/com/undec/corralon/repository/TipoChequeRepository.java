package com.undec.corralon.repository;

import com.undec.corralon.modelo.TipoCheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoChequeRepository extends JpaRepository<TipoCheque, Integer> {
    List<TipoCheque> findTipoChequeByHabilitadoEquals(boolean habilitado);
}
