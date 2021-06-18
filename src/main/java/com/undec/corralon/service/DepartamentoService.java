package com.undec.corralon.service;

import com.undec.corralon.DTO.DepartamentoDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.departamento.DepartamentoErrorToSaveException;
import com.undec.corralon.excepciones.departamento.DepartamentoErrorToUpdateException;
import com.undec.corralon.excepciones.departamento.DepartamentoListNotFoundException;
import com.undec.corralon.excepciones.departamento.DepartamentoNotFoundException;
import com.undec.corralon.modelo.Departamento;
import com.undec.corralon.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    DepartamentoRepository departamentoRepository;

    public Response listarTodos() throws Exception{
        Response response = new Response();
        List<Departamento> departamentos = departamentoRepository.findAll();

        if(departamentos == null)
            throw new DepartamentoListNotFoundException();

        response.setCode(200);
        response.setMsg("Listado departamentos");
        response.setData(departamentos);

        return response;
    }

    public Response listarTodosHabilitados() throws Exception{
        Response response = new Response();
        List<Departamento> departamentos = departamentoRepository.findByHabilitadoEquals(true);
        if(departamentos == null)
            throw new DepartamentoListNotFoundException();

        response.setCode(200);
        response.setMsg("Listado departamentos habilitados");
        response.setData(departamentos);

        return response;
    }

    public Response listarPorId(Integer id) throws Exception{
        Response response = new Response();
        Departamento departamento = departamentoRepository.findById(id).get();

        if(departamento == null)
            throw new DepartamentoNotFoundException();

        response.setCode(200);
        response.setMsg("Departamento " + id);
        response.setData(departamento);

        return response;
    }

    public Response guardar(DepartamentoDTO departamentoDTO) throws Exception{
        Response response = new Response();
        Departamento toSave = this.departamentoDTOToEntity(departamentoDTO);
        toSave.setHabilitado(true);
        toSave = departamentoRepository.save(toSave);

        if(toSave == null)
            throw new DepartamentoErrorToSaveException();

        response.setCode(200);
        response.setMsg("Guardado exitosamente!!!");
        response.setData(toSave);

        return response;
    }

    private Departamento departamentoDTOToEntity(DepartamentoDTO departamentoDTO) {
        Departamento departamento = new Departamento();
        departamento.setNombre(departamentoDTO.getNombre());
        departamento.setAbreviatura(departamentoDTO.getAbreviatura());
        return departamento;
    }

    public Response actualizar(DepartamentoDTO departamentoDTO) throws Exception{
        Response response = new Response();
        Departamento actualizar = departamentoRepository.findById(departamentoDTO.getIdDepartamento()).get();

        if(actualizar == null)
            throw new DepartamentoErrorToUpdateException();

        actualizar.setNombre(departamentoDTO.getNombre());
        actualizar.setAbreviatura(departamentoDTO.getAbreviatura());
        actualizar.setNombre(departamentoDTO.getNombre());
        actualizar.setHabilitado(departamentoDTO.getHabilitado());

        response.setCode(200);
        response.setMsg("Actualizado exitosamente!!!");
        response.setData(departamentoRepository.save(actualizar));

        return response;
    }

    public Response changeStatus(Integer id) throws Exception{

        Response response = new Response();
        Departamento darBaja = departamentoRepository.findById(id).get();

        if(darBaja == null)
            throw new DepartamentoErrorToUpdateException();

        darBaja.setHabilitado(!darBaja.getHabilitado());
        departamentoRepository.save(darBaja);

        response.setCode(200);
        response.setMsg("Se cambio estado exitosamente");
        response.setData(darBaja);


        return response;
    }

}
