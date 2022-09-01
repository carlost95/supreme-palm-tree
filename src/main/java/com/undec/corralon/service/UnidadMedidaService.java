package com.undec.corralon.service;

import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.excepciones.unidadMedida.*;
import com.undec.corralon.modelo.UnidadMedida;
import com.undec.corralon.repository.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadMedidaService {

    @Autowired
    UnidadMedidaRepository unidadMedidaRepository;

    public List<UnidadMedida> obtenerTodasLasUnidadesDeMedida() {

        return this.unidadMedidaRepository.findAll();
    }

    public List<UnidadMedida> obtenerUnidadMedidaHabilitados() {
        return this.unidadMedidaRepository.findAllByHabilitadoEquals(true);
    }

    public UnidadMedida obtenerUnidadMedidaPorId(Integer id) {

        return this.unidadMedidaRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("\nWARNING: error no existe la unidad de mediad"));
    }

    public UnidadMedida crearUnidadMedida(UnidadMedida unidadMedida) throws UnidadMedidaException {

        unidadMedida.setHabilitado(true);
        return this.unidadMedidaRepository.save(unidadMedida);

    }

    public UnidadMedida actualizarUnidadMedida(UnidadMedida unidadMedida) throws UnidadMedidaException {

        UnidadMedida unidadMedidaToUpdate = this.unidadMedidaRepository.findById(unidadMedida.getIdUnidadMedida()).get();

        if (unidadMedidaToUpdate == null)
            throw new UnidadMedidaErrorToUpdate();

        unidadMedidaToUpdate.setNombre(unidadMedida.getNombre());
        unidadMedidaToUpdate.setAbreviatura(unidadMedida.getAbreviatura());

        return this.unidadMedidaRepository.save(unidadMedidaToUpdate);
    }

    public UnidadMedida cambiarHabilitacion(Integer id) throws UnidadMedidadCambioHabilitacionExceptioon {

        Optional<UnidadMedida> unidadMedidaOptional = unidadMedidaRepository.findById(id);
        if (!unidadMedidaOptional.isPresent()) {
            throw new UnidadMedidadCambioHabilitacionExceptioon();
        }
        UnidadMedida unidadMedida = unidadMedidaOptional.get();
        unidadMedida.setHabilitado(!unidadMedida.getHabilitado());
        return  unidadMedidaRepository.save(unidadMedida);

    }

}
