package com.undec.corralon.repository;

import com.undec.corralon.modelo.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChequeRepository extends JpaRepository<Cheque, Integer> {
    List<Cheque> findChequeByHabilitadoEquals(boolean habilitado);

}
