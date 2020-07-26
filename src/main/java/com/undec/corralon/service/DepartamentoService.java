package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.departamento.DepartamentoErrorToSaveException;
import com.undec.corralon.excepciones.departamento.DepartamentoErrorToUpdateException;
import com.undec.corralon.excepciones.departamento.DepartamentoListNotFoundException;
import com.undec.corralon.excepciones.departamento.DepartamentoNotFoundException;
import com.undec.corralon.modelo.Departamento;
import com.undec.corralon.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        List<Departamento> departamentos = departamentoRepository.findAllByHabilitadoEquals(1);
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

    public Response guardar(Departamento departamento) throws Exception{
        Response response = new Response();
        departamento.setFechaalta(LocalDate.now());
        departamento.setFechaactualizacion(LocalDate.now());
        Departamento guardado = departamentoRepository.save(departamento);

        if(guardado == null)
            throw new DepartamentoErrorToSaveException();

        response.setCode(200);
        response.setMsg("Creado");
        response.setData(guardado);

        return response;
    }

    public Response actualizar(Departamento departamento) throws Exception{
        Response response = new Response();
        Departamento actualizar = departamentoRepository.findById(departamento.getId()).get();

        if(actualizar == null)
            throw new DepartamentoErrorToUpdateException();

        actualizar.setNombre(departamento.getNombre());
        actualizar.setAbreviatura(departamento.getAbreviatura());
        actualizar.setNombre(departamento.getNombre());
        actualizar.setFechaactualizacion(LocalDate.now());
        actualizar.setHabilitado(departamento.getHabilitado());

        response.setCode(200);
        response.setMsg("actualizado");
        response.setData(departamentoRepository.save(actualizar));

        return response;
    }

    public Response darDeBaja(Integer id) throws Exception{
        Response response = new Response();
        Departamento darBaja = departamentoRepository.findById(id).get();

        if(darBaja == null)
            throw new DepartamentoErrorToUpdateException();

        darBaja.setHabilitado(0);
        darBaja.setFechabaja(LocalDate.now());
        departamentoRepository.save(darBaja);

        response.setCode(200);
        response.setMsg("Dado de baja");
        response.setData(darBaja);


        return response;
    }



}
