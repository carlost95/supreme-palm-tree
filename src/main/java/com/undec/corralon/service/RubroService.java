package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
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
        return this.rubroRepository.findAll();
    }

    public List<Rubro> obtenerTodosLosRubrosHabilitados() {
        return this.rubroRepository.findByHabilitadoEquals(true);
    }

    public Rubro obtenerPorId(Integer id) throws NotFoundException {

        return this.rubroRepository.findById(id).orElseThrow(
                ()->new NotFoundException("\nWARNING: No existe el rubro")
        );
    }

    public Rubro crearRubro(Rubro rubro) throws RubroException {
        rubro.setHabilitado(true);
        rubro = this.rubroRepository.save(rubro);

        if (rubro == null)
            throw new RubroErrorToSaveException();

        return rubro;
    }

    public Rubro actualizarRubro(Rubro rubro) throws RubroException {
        Rubro rubroToUpdate = this.rubroRepository.findById(rubro.getIdRubro()).get();

        if (rubroToUpdate == null)
            throw new RubroErrorToUpdateException();
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
}
