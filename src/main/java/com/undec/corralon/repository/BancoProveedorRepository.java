package com.undec.corralon.repository;

import com.undec.corralon.modelo.BancoProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BancoProveedorRepository extends JpaRepository<BancoProveedor, Integer> {
}
