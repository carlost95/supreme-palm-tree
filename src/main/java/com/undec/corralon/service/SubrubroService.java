package com.undec.corralon.service;

import com.undec.corralon.DTO.SubrubroDTO;
import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
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

    public SubrubroDTO guardarSubrubro(SubrubroDTO subrubroDTO) {
        SubRubro subRubro = mapDtoToEntity(subrubroDTO);
        validarSubRubro(subRubro);
        subRubro.setHabilitado(true);
        return this.mapEntityToDTO(subRubroRepository.save(subRubro));
    }


    public SubrubroDTO actualizarSubrubro(SubrubroDTO subrubroDTO) {
        SubRubro subRubroToUpdate = subRubroRepository.findById(subrubroDTO.getIdSubRubro()).
                orElseThrow(
                        () -> new NotFoundException("\nWARNING: error no existe el sub rubro por id"));

        subRubroToUpdate.setNombre(subrubroDTO.getNombre());
        subRubroToUpdate.setAbreviatura(subrubroDTO.getAbreviatura());
        subRubroToUpdate.setHabilitado(subrubroDTO.getHabilitado());
        subRubroToUpdate.setRubroByIdRubro(
                rubroRepository.findById(subrubroDTO.getRubroId()).
                        orElseThrow(
                                () -> new NotFoundException("\nWARNING: error no existe el rubro por id")));
        validateSubRubroUpdate(subRubroToUpdate);
        return this.mapEntityToDTO(subRubroRepository.save(subRubroToUpdate));
    }

    private SubRubro mapDtoToEntity(SubrubroDTO subrubroDTO) {
        SubRubro subRubro = new SubRubro();
        subRubro.setIdSubRubro(subrubroDTO.getIdSubRubro());
        subRubro.setNombre(subrubroDTO.getNombre());
        subRubro.setHabilitado(subrubroDTO.getHabilitado());
        subRubro.setAbreviatura(subrubroDTO.getAbreviatura());
        subRubro.setRubroByIdRubro(
                rubroRepository.findById(subrubroDTO.getRubroId()).
                        orElseThrow(
                                () -> new NotFoundException("\nWARNING: error no existe el rubro seleccionado")));
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

    public SubrubroDTO cambiarHabilitacion(Integer id) {
        SubRubro subRubro = subRubroRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException("\nWARNING: error no existe el sub rubro por id"));

        subRubro.setHabilitado(!subRubro.getHabilitado());
        return this.mapEntityToDTO(subRubroRepository.save(subRubro));
    }

    private void validateSubRubroUpdate(SubRubro subRubroToUpdate) {
        validateData(subRubroToUpdate.getNombre());
        validateData(subRubroToUpdate.getAbreviatura());
        if (subRubroRepository.existsDistinctByNombreAndIdSubRubroNot(subRubroToUpdate.getNombre(), subRubroToUpdate.getIdSubRubro()))
            throw new NotFoundException("\nWARNING: error el nombre del sub rubro ya existe");
        if (subRubroRepository.existsDistinctByAbreviaturaAndIdSubRubroNot(subRubroToUpdate.getAbreviatura(), subRubroToUpdate.getIdSubRubro()))
            throw new NotFoundException("\nWARNING: error la abreviatura del sub rubro ya existe");

    }

    private void validarSubRubro(SubRubro subrubro) {
        validateData(subrubro.getNombre());
        validateData(subrubro.getAbreviatura());
        if (subRubroRepository.existsDistinctByNombreOrAbreviatura(subrubro.getNombre(), subrubro.getAbreviatura())) {
            throw new NotFoundException("\nWARNING: el nombre o abreviatura ya existe en un sub rubro registrado");
        }
    }

    private void validateData(String data) {
        if (data == null || data.isEmpty())
            throw new BadRequestException("\nWARNING: error no se puede guardar un sub rubro con datos vacios");
    }

}
