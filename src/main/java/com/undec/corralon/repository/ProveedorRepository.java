package com.undec.corralon.repository;

import com.undec.corralon.modelo.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    List<Proveedor> findAllByHabilitadoEquals(Boolean habilitado);

    Boolean existsByRazonSocialAndEmail(String RZ, String Em);
}
