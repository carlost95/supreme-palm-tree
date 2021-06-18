package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
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

    public Response obtenerTodosLosRubros() {
        Response response = new Response();
        List<Rubro> rubros = this.rubroRepository.findAll();

        response.setData(rubros);
        response.setCode(200);
        response.setMsg("Rubros");

        return response;
    }

    public Response obtenerTodosLosRubrosHabilitados() {
        Response response = new Response();
        List<Rubro> rubros = this.rubroRepository.findByHabilitadoEquals(true);

        response.setData(rubros);
        response.setCode(200);
        response.setMsg("Rubros Habilitados");

        return response;
    }

    public Response obtenerPorId(Integer id) throws RubroException {
        Response response = new Response();

        Rubro rubro = this.rubroRepository.findById(id).get();

        if (rubro == null)
            throw new RubroNotFoundException();
        response.setData(rubro);
        response.setCode(200);
        response.setMsg("Rubro " + id);

        return response;
    }

    public Response crearRubro(Rubro rubro) throws RubroException {
        Response response = new Response();
        rubro.setHabilitado(true);
        rubro = this.rubroRepository.save(rubro);

        if (rubro == null)
            throw new RubroErrorToSaveException();
        response.setData(rubro);
        response.setCode(200);
        response.setMsg("Rubro creado corectamente");

        return response;
    }

    public Response actualizarRubro(Rubro rubro) throws RubroException {
        Response response = new Response();

        Rubro rubroToUpdate = this.rubroRepository.findById(rubro.getIdRubro()).get();

        if (rubroToUpdate == null)
            throw new RubroErrorToUpdateException();
        rubroToUpdate.setNombre(rubro.getNombre());

        rubroToUpdate = rubroRepository.save(rubroToUpdate);

        response.setData(rubroToUpdate);
        response.setCode(200);
        response.setMsg("Rubro actualizado");

        return response;
    }


    public Response cambiarHabilitacion(Integer id) throws RubroException {
        Response response = new Response();
        Optional<Rubro> rubroOptional = rubroRepository.findById(id);
        if (!rubroOptional.isPresent()) {
            throw new RubroCambioEstadoException();
        }
        Rubro rubro = rubroOptional.get();
        rubro.setHabilitado(!rubro.getHabilitado());
        rubroRepository.save(rubro);

        response.setCode(200);
        response.setMsg("El rubro cambio el estado");
        response.setData(rubro);
        return response;
    }
}
