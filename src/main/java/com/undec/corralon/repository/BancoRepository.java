package com.undec.corralon.repository;

import com.undec.corralon.modelo.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BancoRepository extends JpaRepository <Banco, Integer> {
    List<Banco>findAllByHabilitadoEquals(boolean habilitado);
    Boolean existsBancoByNombreOrAbreviatura(String nombre, String abreviatura);
    Boolean existsBancoByNombreAndIdBancoNot(String nombre, Integer idBanco);
    Boolean existsBancoByAbreviaturaAndIdBancoNot(String abreviatura, Integer idBanco);
}
