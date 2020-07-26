package com.undec.corralon.repository;

import com.undec.corralon.modelo.Tipodireccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipodireccionRepository extends JpaRepository<Tipodireccion, Integer> {
    List<Tipodireccion> findAllByHabilitadoEquals(Integer habilitado);
}
