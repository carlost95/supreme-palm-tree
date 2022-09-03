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
import java.util.stream.Collectors;

@Service
public class SubrubroService {

    @Autowired
    SubRubroRepository subRubroRepository;

    @Autowired
    RubroRepository rubroRepository;

    public List<SubrubroDTO> buscarTodosLosSubrubros() {
        return subRubroRepository
                .findAll().stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    public List<SubrubroDTO> buscarTodosLosSubrubrosHabilitados() {
        return subRubroRepository
                .findAllByHabilitadoEquals(true)
                .stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    public SubrubroDTO obtenerSubrubroPorId(Integer id) {

        return subRubroRepository
                .findById(id)
                .map(this::mapEntityToDTO)
                .orElseThrow(() -> new NotFoundException("\nWARNING: error no existe el sub rubro por id"));
    }

    public List<SubrubroDTO> obtenerSubrubroPorRubro(Integer rubroId) {
        return subRubroRepository
                .findAllByRubroByIdRubro(rubroId)
                .stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    public SubrubroDTO guardarSubrubro(SubrubroDTO subrubroDTO) throws SubrubroException {
        SubRubro subRubro = mapDtoToEntity(subrubroDTO);
        if (subRubro == null)
            throw new SubRubroErrorToSaveException();

        subRubro.setHabilitado(true);
        return this.mapEntityToDTO(subRubroRepository.save(subRubro));
    }


    public SubrubroDTO actualizarSubrubro(SubrubroDTO subrubroDTO) throws SubrubroException {

        SubRubro subRubroToUpdate = subRubroRepository.findById(subrubroDTO.getIdSubRubro()).get();

        if (subRubroToUpdate == null)
            throw new SubRubroErrorToUpdateException();

        subRubroToUpdate.setNombre(subrubroDTO.getNombre());
        subRubroToUpdate.setAbreviatura(subrubroDTO.getAbreviatura());
        subRubroToUpdate.setHabilitado(subrubroDTO.getHabilitado());
        subRubroToUpdate.setRubroByIdRubro(rubroRepository.findById(subrubroDTO.getRubroId()).get());

        return this.mapEntityToDTO(subRubroRepository.save(subRubroToUpdate));
    }

    private SubRubro mapDtoToEntity(SubrubroDTO subrubroDTO) {
        SubRubro subRubro = new SubRubro();
        subRubro.setIdSubRubro(subrubroDTO.getIdSubRubro());
        subRubro.setNombre(subrubroDTO.getNombre());
        subRubro.setHabilitado(subrubroDTO.getHabilitado());
        subRubro.setAbreviatura(subrubroDTO.getAbreviatura());
        subRubro.setRubroByIdRubro(rubroRepository.findById(subrubroDTO.getRubroId()).get());
        return subRubro;
    }

    private SubrubroDTO mapEntityToDTO(SubRubro subRubro) {
        SubrubroDTO subrubroDTO = new SubrubroDTO();
        subrubroDTO.setIdSubRubro(subRubro.getIdSubRubro());
        subrubroDTO.setHabilitado(subRubro.getHabilitado());
        subrubroDTO.setNombre(subRubro.getNombre());
        subrubroDTO.setAbreviatura(subRubro.getAbreviatura());
        subrubroDTO.setRubroId(subRubro.getRubroByIdRubro().getIdRubro());
        return subrubroDTO;
    }

    public SubrubroDTO cambiarHabilitacion(Integer id) throws SubrubroException {
        Optional<SubRubro> subRubroOptional = subRubroRepository.findById(id);
        if (!subRubroOptional.isPresent()) {
            throw new SubRubroCambioEstadoException();
        }
        SubRubro subRubro = subRubroOptional.get();
        subRubro.setHabilitado(!subRubro.getHabilitado());
        return this.mapEntityToDTO(subRubroRepository.save(subRubro));
    }
}
