package com.undec.corralon.service;

import com.undec.corralon.DTO.SubrubroDTO;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.excepciones.subrubro.*;
import com.undec.corralon.modelo.SubRubro;
import com.undec.corralon.repository.RubroRepository;
import com.undec.corralon.repository.SubRubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubrubroService {

    @Autowired
    SubRubroRepository subRubroRepository;

    @Autowired
    RubroRepository rubroRepository;

    public List<SubRubro> buscarTodosLosSubrubros() {
        return subRubroRepository.findAll();
    }

    public List<SubRubro> buscarTodosLosSubrubrosHabilitados() {
        return subRubroRepository.findAllByHabilitadoEquals(true);
    }

    public SubRubro obtenerSubrubroPorId(Integer id) {

        return subRubroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("\nWARNING: error no existe el sub rubro por id"));
    }

    public List<SubRubro> obtenerSubrubroPorRubro(Integer rubroId) {
        return subRubroRepository.findAllByRubroByIdRubro(rubroId);
    }

    public SubRubro guardarSubrubro(SubrubroDTO subrubroDTO) throws SubrubroException {
        SubRubro subRubro = mapDtoToEntity(subrubroDTO);

        if (subRubro == null)
            throw new SubRubroErrorToSaveException();

        subRubro.setHabilitado(true);
        return subRubroRepository.save(subRubro);
    }


    public SubRubro actualizarSubrubro(SubrubroDTO subrubroDTO) throws SubrubroException {

        SubRubro subRubroToUpdate = subRubroRepository.findById(subrubroDTO.getIdSubRubro()).get();

        if (subRubroToUpdate == null)
            throw new SubRubroErrorToUpdateException();

        subRubroToUpdate.setNombre(subrubroDTO.getNombre());
        subRubroToUpdate.setHabilitado(subrubroDTO.getHabilitado());
        subRubroToUpdate.setRubroByIdRubro(rubroRepository.findById(subrubroDTO.getRubroId()).get());

        return subRubroRepository.save(subRubroToUpdate);
    }

    private SubRubro mapDtoToEntity(SubrubroDTO subrubroDTO) {
        SubRubro subRubro = new SubRubro();
        subRubro.setIdSubRubro(subrubroDTO.getIdSubRubro());
        subRubro.setNombre(subrubroDTO.getNombre());
        subRubro.setHabilitado(subrubroDTO.getHabilitado());
        subRubro.setRubroByIdRubro(rubroRepository.findById(subrubroDTO.getRubroId()).get());
        return subRubro;
    }

    public SubRubro cambiarHabilitacion(Integer id) throws SubrubroException {
        Optional<SubRubro> subRubroOptional = subRubroRepository.findById(id);
        if (!subRubroOptional.isPresent()) {
            throw new SubRubroCambioEstadoException();
        }
        SubRubro subRubro = subRubroOptional.get();
        subRubro.setHabilitado(!subRubro.getHabilitado());
        return subRubroRepository.save(subRubro);
    }
}
