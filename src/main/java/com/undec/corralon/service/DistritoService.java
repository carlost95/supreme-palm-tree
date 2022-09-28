package com.undec.corralon.service;

import com.undec.corralon.DTO.DistritoDTO;
import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.Departamento;
import com.undec.corralon.modelo.Distrito;
import com.undec.corralon.repository.DepartamentoRepository;
import com.undec.corralon.repository.DistritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistritoService {

    @Autowired
    DistritoRepository distritoRepository;

    @Autowired
    DepartamentoRepository departamentoRepository;

    public List<DistritoDTO> listAllDistrict() {
        List<DistritoDTO> districtStorage = new ArrayList<>();
        List<Distrito> distritos = distritoRepository.findAll();

        if (distritos == null) throw new NotFoundException("\nWARNING: No existen distritos");
        for (Distrito dist : distritos) districtStorage.add(mappedDistrictToDistrictDTO(dist));
        return districtStorage;
    }

    public List<DistritoDTO> listAllDistrictHabilitation() {
        List<DistritoDTO> districtStorage = new ArrayList<>();
        List<Distrito> distritos = distritoRepository.findByHabilitadoEquals(true);

        if (distritos == null) throw new NotFoundException("\nWARNING: No existen datos de distritos habilitados");
        for (Distrito dist : distritos) districtStorage.add(mappedDistrictToDistrictDTO(dist));
        return districtStorage;
    }

    public DistritoDTO findDistrictById(Integer id) {
        if (id == null) {
            throw new BadRequestException("\nWARNING: No se enviaron datos o el id del distrito es null");
        }
        Distrito distrito = distritoRepository.findById(id).orElseThrow(() -> new NotFoundException("\nWARNING: No existe el distrito solicitado"));
        DistritoDTO distritoDTO = mappedDistrictToDistrictDTO(distrito);

        return distritoDTO;
    }

    public DistritoDTO saveDistrict(DistritoDTO distritoDTO) {
        Distrito distrito = mappedDTOToDistrict(distritoDTO);
        Integer idDepartament = distritoDTO.getIdDepartamento();
        Departamento departamento = this.departamentoRepository.findById(idDepartament).orElseThrow(() -> new NotFoundException("\nWARNING: El departamento al que se quiere agregar el distrito no existe"));
        distrito.setIdDepartamento(departamento);
        distrito.setHabilitado(true);

        if (validationDistrict(distrito))
            throw new NotFoundException("\nWARNING: El nombre o abreviatura del distrito cargado ya existe");

        Distrito guardado = distritoRepository.save(distrito);

        if (guardado == null)
            throw new NotFoundException("\nWARNING: Error al guardar el distrito, no existe no se puede guardar");

        distritoDTO = mappedDistrictToDistrictDTO(guardado);
        return distritoDTO;
    }


    public DistritoDTO updateDistrict(DistritoDTO distritoDTO) {
        Distrito distrito = mappedDTOToDistrict(distritoDTO);
        Integer idDepartamento = distritoDTO.getIdDepartamento();
        Departamento departamento = this.departamentoRepository.findById(
                        idDepartamento).
                orElseThrow(
                        () -> new NotFoundException("\nWARNING: No existe el departamento al que se le quiere asiganr el distrito"));

        distrito.setIdDepartamento(departamento);
        distrito.setIdDistrito(distritoDTO.getIdDistrito());
        distrito.setHabilitado(distritoDTO.getHabilitado());
        validatioUpdateDistrict(distrito);

        Distrito saveDistrict = distritoRepository.save(distrito);

        if (saveDistrict == null)
            throw new NotFoundException("\nWARNING: Error al actualizar el distrito, no existe el dato solicitado");

        distritoDTO = mappedDistrictToDistrictDTO(saveDistrict);
        return distritoDTO;
    }

    public DistritoDTO changeStatus(Integer id) {
        Distrito changeStatus = distritoRepository.findById(id).orElseThrow(() -> new NotFoundException("\nWARNING: No exite el distrito que se quiere cambiar el estado de habilitacion"));

        changeStatus.setHabilitado(!changeStatus.getHabilitado());
        changeStatus = this.distritoRepository.save(changeStatus);

        DistritoDTO distritoDTO = mappedDistrictToDistrictDTO(changeStatus);
        if (distritoDTO == null) throw new NotFoundException("\nWARNING: Error al cambiar el estado del distrito");

        return distritoDTO;
    }

    private Distrito mappedDTOToDistrict(DistritoDTO distritoDTO) {
        Distrito distrito = new Distrito();
        if (distritoDTO.getNombre() == null || distritoDTO.getAbreviatura() == null)
            throw new BadRequestException("\nWARNING: Error en los datos del distrito, no pueden ser null");
        distrito.setNombre(distritoDTO.getNombre());
        distrito.setAbreviatura(distritoDTO.getAbreviatura());
        distrito.setHabilitado(distritoDTO.getHabilitado());

        return distrito;
    }

    private DistritoDTO mappedDistrictToDistrictDTO(Distrito distrito) {
        DistritoDTO distritoDTO = new DistritoDTO();
        distritoDTO.setIdDistrito(distrito.getIdDistrito());
        distritoDTO.setNombre(distrito.getNombre());
        distritoDTO.setAbreviatura(distrito.getAbreviatura());
        distritoDTO.setHabilitado(distrito.getHabilitado());
        distritoDTO.setIdDepartamento(distrito.getIdDepartamento().getIdDepartamento());

        return distritoDTO;
    }

    private boolean validationDistrict(Distrito distrito) {
        return (distritoRepository.existsDistinctByNombreOrAbreviatura(distrito.getNombre(), distrito.getAbreviatura())) ? true : false;
    }

    private void validatioUpdateDistrict(Distrito distrito) {
        if (distritoRepository.existsDistinctByNombreAndIdDistritoNot(distrito.getNombre(), distrito.getIdDistrito())) {
            throw new NotFoundException("\nWARNING: El nombre del distrito por actualizar se encuentra registrado en otro distrito");
        }
        if (distritoRepository.existsDistinctByAbreviaturaAndIdDistritoNot(distrito.getAbreviatura(), distrito.getIdDistrito())) {
            throw new NotFoundException("\nWARNING: La abreviatura del distrito por actualizar se encuentra registrado en otro distrito");
        }
    }
}
