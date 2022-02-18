package com.undec.corralon.repository;

import com.undec.corralon.modelo.Tipotarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTarjetaRepository extends JpaRepository<Tipotarjeta, Integer> {
}
