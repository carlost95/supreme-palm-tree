package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.excepciones.rubro.*;
import com.undec.corralon.modelo.Rubro;
import com.undec.corralon.repository.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RubroService {

    @Autowired
    RubroRepository rubroRepository;

    public List<Rubro> obtenerTodosLosRubros() {
        List<Rubro> rubros = rubroRepository.findAll();
        if (rubros.isEmpty()) {
            throw new NotFoundException("No se encontraron rubros");
        }
        return rubros;
    }

    public List<Rubro> obtenerTodosLosRubrosHabilitados() {
        List<Rubro> rubros = rubroRepository.findByHabilitadoEquals(true);
        if (rubros.isEmpty()) {
            throw new NotFoundException("No se encontraron rubros habilitados");
        }
        return rubros;
    }

    public Rubro obtenerPorId(Integer id) throws NotFoundException {
        return this.rubroRepository.findById(id).orElseThrow(
                () -> new NotFoundException("\nWARNING: No existe el rubro")
        );
    }

    public Rubro crearRubro(Rubro rubro) {
        validarRubro(rubro);
        rubro.setHabilitado(true);
        rubro = this.rubroRepository.save(rubro);

        return rubro;
    }


    public Rubro actualizarRubro(Rubro rubro) {
        Rubro rubroToUpdate = this.rubroRepository.findById(rubro.getIdRubro()).get();

        if (rubroToUpdate.toString().isEmpty())
            throw new NotFoundException("Warning: No se encontro el rubro en base de datos");
        validateUpdateRubro(rubro);
        rubroToUpdate.setNombre(rubro.getNombre());
        rubroToUpdate.setAbreviatura(rubro.getAbreviatura());

        return rubroRepository.save(rubroToUpdate);
    }


    public Rubro cambiarHabilitacion(Integer id) throws RubroException {
        Optional<Rubro> rubroOptional = rubroRepository.findById(id);
        if (!rubroOptional.isPresent()) {
            throw new RubroCambioEstadoException();
        }
        Rubro rubro = rubroOptional.get();
        rubro.setHabilitado(!rubro.getHabilitado());
        return rubroRepository.save(rubro);
    }

    private void validarRubro(Rubro rubro) {
        validateDataBlank(rubro.getNombre());
        validateDataBlank(rubro.getAbreviatura());
        if (rubroRepository.existsRubroByNombreOrAbreviatura(rubro.getNombre(),rubro.getAbreviatura()))
            throw new NotFoundException("Warning: Ya existe un rubro con ese nombre o abreviatura");
    }
    private void validateUpdateRubro(Rubro rubro){
        validateDataBlank(rubro.getNombre());
        validateDataBlank(rubro.getAbreviatura());
        if (rubroRepository.existsRubroByNombreAndIdRubroNot(rubro.getNombre(),rubro.getIdRubro()))
            throw new NotFoundException("Warning: Ya existe un rubro con ese nombre");
        if (rubroRepository.existsRubroByAbreviaturaAndIdRubroNot(rubro.getAbreviatura(),rubro.getIdRubro()))
            throw new NotFoundException("Warning: Ya existe un rubro con esa abreviatura");
    }

    private void validateDataBlank(String data) {
        if (data.trim().isEmpty()) {
            throw new BadRequestException("No se permiten datos en nulls o vacios");
        }
    }

}
