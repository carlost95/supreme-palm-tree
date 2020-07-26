package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.rubro.*;
import com.undec.corralon.modelo.Rubro;
import com.undec.corralon.repository.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RubroService {

    @Autowired
    RubroRepository rubroRepository;

    public Response obtenerTodosLosRubros(){
        Response response = new Response();
        List<Rubro> rubros =  this.rubroRepository.findAll();

        response.setData(rubros);
        response.setCode(200);
        response.setMsg("Rubros");

        return response;
    }

    public Response obtenerTodosLosRubrosHabilitados(){
        Response response = new Response();
        List<Rubro> rubros =  this.rubroRepository.findByHabilitacionEquals(1);

        response.setData(rubros);
        response.setCode(200);
        response.setMsg("Rubros Habilitados");

        return response;
    }

    public Response obtenerPorId(Integer id) throws RubroException {
        Response response = new Response();

        Rubro rubro =  this.rubroRepository.findById(id).get();

        if(rubro == null)
            throw new RubroNotFoundException();
        response.setData(rubro);
        response.setCode(200);
        response.setMsg("Rubro " + id);

        return response;
    }

    public Response crearRubro(Rubro rubro) throws RubroException{
        Response response = new Response();
        rubro.setHabilitacion(1);
        rubro.setFechaCreacion(new Date());
        rubro =  this.rubroRepository.save(rubro);

        if(rubro == null)
            throw new RubroErrorToSaveException();
        response.setData(rubro);
        response.setCode(200);
        response.setMsg("Creado");

        return response;
    }

    public Response actualizarRubro(Rubro rubro) throws RubroException{
        Response response = new Response();

        Rubro rubroToUpdate =  this.rubroRepository.findById(rubro.getId()).get();

        if(rubroToUpdate == null)
            throw new RubroErrorToUpdateException();
        rubroToUpdate.setNombre(rubro.getNombre());
        rubroToUpdate.setDescripcion(rubro.getDescripcion());
        rubroToUpdate.setFechaModificacion(new Date());

        rubroToUpdate = rubroRepository.save(rubroToUpdate);

        response.setData(rubroToUpdate);
        response.setCode(200);
        response.setMsg("Actualizado");

        return response;
    }

    public Response bajaRubro(Integer id) throws RubroException {
        Response response = new Response();

        Rubro rubroToDelete =  this.rubroRepository.findById(id).get();

        if(rubroToDelete == null)
            throw new RubroErrorToDeleteException();
        rubroToDelete.setHabilitacion(0);
        rubroToDelete.setFechaBaja(new Date());
        rubroToDelete = this.rubroRepository.save(rubroToDelete);
        response.setData(rubroToDelete);
        response.setCode(200);
        response.setMsg("Eliminado");
        return response;
    }

}
