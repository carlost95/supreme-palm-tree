package com.undec.corralon.service;

import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.UnidadMedida;
import com.undec.corralon.repository.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadMedidaService {

    @Autowired
    UnidadMedidaRepository unidadMedidaRepository;

    public List<UnidadMedida> obtenerTodasLasUnidadesDeMedida() {
        List<UnidadMedida> unidadMedidas = unidadMedidaRepository.findAll();
        if (unidadMedidas.isEmpty()) {
            throw new NotFoundException("No se encontraron unidades de medida");
        }

        return unidadMedidas;
    }

    public List<UnidadMedida> obtenerUnidadMedidaHabilitados() {
        List<UnidadMedida> unidadMedidas = unidadMedidaRepository.findAllByHabilitadoEquals(true);
        if (unidadMedidas.isEmpty()) {
            throw new NotFoundException("No se encontraron unidades de medida habilitadas");
        }
        return unidadMedidas;
    }

    public UnidadMedida obtenerUnidadMedidaPorId(Integer id) {
        return this.unidadMedidaRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException("\nWARNING: error no existe la unidad de medida"));
    }

    public UnidadMedida crearUnidadMedida(UnidadMedida unidadMedida) {
        validarUnidadMedida(unidadMedida);
        unidadMedida.setHabilitado(true);
        unidadMedida = unidadMedidaRepository.save(unidadMedida);
        if (unidadMedida.toString().isEmpty()) {
            throw new NotFoundException("No se pudo crear la unidad de medida");
        }
        return unidadMedida;
    }

    public UnidadMedida actualizarUnidadMedida(UnidadMedida unidadMedida) {
        UnidadMedida unidadMedidaToUpdate = this.unidadMedidaRepository.findById(
                        unidadMedida.getIdUnidadMedida()).
                orElseThrow(
                        () -> new NotFoundException("\nWARNING: error no existe la unidad de medida"));

        unidadMedidaToUpdate.setNombre(unidadMedida.getNombre());
        unidadMedidaToUpdate.setAbreviatura(unidadMedida.getAbreviatura());
        unidadMedidaToUpdate.setHabilitado(unidadMedida.getHabilitado());
        validateUpdateUnidadMedida(unidadMedidaToUpdate);
        unidadMedidaToUpdate = unidadMedidaRepository.save(unidadMedidaToUpdate);
        if (unidadMedidaToUpdate.toString().isEmpty()) {
            throw new NotFoundException("No se pudo actualizar la unidad de medida");
        }
        return unidadMedidaToUpdate;
    }

    public UnidadMedida cambiarHabilitacion(Integer id) {
        UnidadMedida unidadMedidaToUpdate = this.unidadMedidaRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException("\nWARNING: error no existe la unidad de medida"));

        unidadMedidaToUpdate.setHabilitado(!unidadMedidaToUpdate.getHabilitado());
        unidadMedidaToUpdate = unidadMedidaRepository.save(unidadMedidaToUpdate);
        if (unidadMedidaToUpdate.toString().isEmpty()) {
            throw new NotFoundException("No se pudo actualizar la unidad de medida");
        }
        return unidadMedidaToUpdate;

    }

    private void validarUnidadMedida(UnidadMedida unidadMedida) {
        validateData(unidadMedida.getNombre());
        validateData(unidadMedida.getAbreviatura());
        if (unidadMedidaRepository.existsUnidadMedidaByNombreOrAbreviatura(unidadMedida.getNombre(), unidadMedida.getAbreviatura())) {
            throw new NotFoundException("Ya existe una unidad de medida con el nombre " + unidadMedida.getNombre() + " o abreviatura " + unidadMedida.getAbreviatura() + " ingresada");
        }
    }

    private void validateUpdateUnidadMedida(UnidadMedida unidadMedida) {
        validateData(unidadMedida.getNombre());
        validateData(unidadMedida.getAbreviatura());
        if (unidadMedidaRepository.existsUnidadMedidaByNombreAndIdUnidadMedidaNot(unidadMedida.getNombre(), unidadMedida.getIdUnidadMedida())) {
            throw new NotFoundException("Ya existe una unidad de medida con el nombre " + unidadMedida.getNombre() + " ingresada");
        }
        if (unidadMedidaRepository.existsUnidadMedidaByAbreviaturaAndIdUnidadMedidaNot(unidadMedida.getAbreviatura(), unidadMedida.getIdUnidadMedida())) {
            throw new NotFoundException("Ya existe una unidad de medida con la abreviatura " + unidadMedida.getAbreviatura() + " ingresada");
        }
    }

    private void validateData(String data) {
        if (data.isEmpty()) {
            throw new NotFoundException("Warning: el dato de unidad de medida no puede ser vacio o null");
        }
    }

}
