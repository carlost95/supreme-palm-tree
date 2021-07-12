package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.DTO.SubrubroDTO;
import com.undec.corralon.excepciones.departamento.DepartamentoErrorToUpdateException;
import com.undec.corralon.excepciones.distrito.DistritoListNotFoundException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.excepciones.rubro.RubroCambioEstadoException;
import com.undec.corralon.excepciones.subrubro.*;
import com.undec.corralon.modelo.SubRubro;
import com.undec.corralon.repository.RubroRepository;
import com.undec.corralon.repository.SubRubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SubrubroService {

    @Autowired
    SubRubroRepository subRubroRepository;

    @Autowired
    RubroRepository rubroRepository;

    public Response buscarTodosLosSubrubros() {
        Response response = new Response();
        List<SubRubro> subrubros = subRubroRepository.findAll();

        response.setCode(200);
        response.setMsg("Todos los subrubros");
        response.setData(subrubros);

        return response;
    }

    public Response buscarTodosLosSubrubrosHabilitados() {
        Response response = new Response();
        List<SubRubro> subrubros = subRubroRepository.findAllByHabilitadoEquals(true);

        response.setCode(200);
        response.setMsg("Todos los subrubros habilitados");
        response.setData(subrubros);

        return response;
    }

    public Response obtenerSubrubroPorId(Integer id) {

        Response response = new Response();
        SubRubro subrubro = subRubroRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("\nWARNING: error no existe el sub rubro por id")
                );

        response.setCode(200);
        response.setMsg("Subrubro");
        response.setData(subrubro);

        return response;
    }

    public Response obtenerSubrubroPorRubro(Integer rubroId) {

        Response response = new Response();
        List<SubRubro> subrubro = subRubroRepository.findAllByRubroByIdRubro(rubroId);

        response.setCode(200);
        response.setMsg("Subrubro pertenecientes al rubro: " + this.rubroRepository.findById(rubroId).get().getNombre());
        response.setData(subrubro);

        return response;
    }

    public Response guardarSubrubro(SubrubroDTO subrubroDTO) throws SubrubroException {
        Response response = new Response();
        SubRubro subRubro = mapDtoToEntity(subrubroDTO);

        if (subRubro == null)
            throw new SubRubroErrorToSaveException();

        subRubro.setHabilitado(true);
        subRubro = subRubroRepository.save(subRubro);

        response.setCode(200);
        response.setMsg("Sub-rubro creado correctamente");
        response.setData(subRubro);

        return response;
    }

    public Response actualizarSubrubro(SubrubroDTO subrubroDTO) throws SubrubroException {

        Response response = new Response();
        SubRubro subRubroToUpdate = subRubroRepository.findById(subrubroDTO.getIdSubRubro()).get();

        if (subRubroToUpdate == null)
            throw new SubRubroErrorToUpdateException();

        subRubroToUpdate.setNombre(subrubroDTO.getNombre());
        subRubroToUpdate.setHabilitado(subrubroDTO.getHabilitado());
        subRubroToUpdate.setRubroByIdRubro(rubroRepository.findById(subrubroDTO.getRubroId()).get());

        subRubroToUpdate = subRubroRepository.save(subRubroToUpdate);

        response.setCode(200);
        response.setMsg("Sub-rubro actualizado correctamente");
        response.setData(subRubroToUpdate);

        return response;
    }

    private SubRubro mapDtoToEntity(SubrubroDTO subrubroDTO) {
        SubRubro subRubro = new SubRubro();
        subRubro.setIdSubRubro(subrubroDTO.getIdSubRubro());
        subRubro.setNombre(subrubroDTO.getNombre());
        subRubro.setHabilitado(subrubroDTO.getHabilitado());
        subRubro.setRubroByIdRubro(rubroRepository.findById(subrubroDTO.getRubroId()).get());
        return subRubro;
    }

    public Response cambiarHabilitacion(Integer id) throws SubrubroException {
        Response response = new Response();
        Optional<SubRubro> subRubroOptional = subRubroRepository.findById(id);
        if (!subRubroOptional.isPresent()) {
            throw new SubRubroCambioEstadoException();
        }
        SubRubro subRubro = subRubroOptional.get();
        subRubro.setHabilitado(!subRubro.getHabilitado());
        subRubroRepository.save(subRubro);

        response.setCode(200);
        response.setMsg("cambio de estado subrubro");
        response.setData(subRubro);
        return response;
    }
}
