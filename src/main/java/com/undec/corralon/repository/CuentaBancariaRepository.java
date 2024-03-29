package com.undec.corralon.repository;

import com.undec.corralon.modelo.CuentaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria, Integer> {
    @Query("select c from CuentaBancaria c where c.proveedor.idProveedor = :id")
    List<CuentaBancaria> findAllCuentaBancariaByIdProveedor(@Param("id") Integer id);


    Boolean existsCuentaBancariaByAlias(String alias);
    Boolean existsCuentaBancariaByNumero(String numeroCuenta);
    Boolean existsCuentaBancariaByCbu(String cbu);
    Boolean existsCuentaBancariasByAliasAndIdIsNot(String alias, Integer id);
    Boolean existsCuentaBancariaByNumeroAndIdNot(String numeroCuenta, Integer id);
    Boolean existsCuentaBancariaByCbuAndIdNot(String cbu, Integer id);
}


