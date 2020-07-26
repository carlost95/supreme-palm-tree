package com.undec.corralon.service;

import com.undec.corralon.DTO.DistritoDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.distrito.DistritoErrorToSaveException;
import com.undec.corralon.excepciones.distrito.DistritoErrorToUpdateException;
import com.undec.corralon.excepciones.distrito.DistritoListNotFoundException;
import com.undec.corralon.excepciones.distrito.DistritoNotFoundException;
import com.undec.corralon.modelo.Distrito;
import com.undec.corralon.repository.DepartamentoRepository;
import com.undec.corralon.repository.DistritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DistritoService {

    @Autowired
    DistritoRepository distritoRepository;

    @Autowired
    DepartamentoRepository departamentoRepository;

    public Response listarTodos() throws Exception{
        Response response = new Response();
        List<Distrito> distrito = distritoRepository.findAll();

        if(distrito == null)
            throw new DistritoListNotFoundException();

        response.setCode(200);
        response.setMsg("Listado distrito");
        response.setData(distrito);

        return response;
    }

    public Response listarTodosHabilitados() throws Exception{
        Response response = new Response();
        List<Distrito> distritos = distritoRepository.findAllByHabilitadoEquals(1);

        if(distritos == null)
            throw new DistritoListNotFoundException();

        response.setCode(200);
        response.setMsg("Listado distritos habilitados");
        response.setData(distritos);

        return response;
    }

    public Response listarPorId(Integer id) throws Exception{
        Response response = new Response();
        Distrito distrito = distritoRepository.findById(id).get();

        if(distrito == null)
            throw new DistritoNotFoundException();

        response.setCode(200);
        response.setMsg("Departamento " + id);
        response.setData(distrito);

        return response;
    }

    public Response guardar(DistritoDTO distritoDTO) throws Exception{
        Response response = new Response();
        Distrito distrito = dtoToDistrito(distritoDTO);

        distrito.setFechaalta(LocalDate.now());
        distrito.setFechaactualizacion(LocalDate.now());
        distrito.setHabilitado( 1);
        Distrito guardado = distritoRepository.save(distrito);

        if(guardado == null)
            throw new DistritoErrorToSaveException();

        response.setCode(200);
        response.setMsg("Creado");
        response.setData(guardado);

        return response;
    }


    public Response actualizar(DistritoDTO distritoDTO) throws Exception{
        Response response = new Response();
        Distrito actualizar = distritoRepository.findById(distritoDTO.getId()).get();

        if(distritoDTO.getHabilitacion()==null)
            actualizar.setHabilitado(1);
        else
            actualizar.setHabilitado(distritoDTO.getHabilitacion());

        if(actualizar == null)
            throw new DistritoErrorToUpdateException();

        actualizar.setId(distritoDTO.getId());
        actualizar.setNombre(distritoDTO.getNombre());
        actualizar.setAbreviatura(distritoDTO.getAbreviatura());
        actualizar.setNombre(distritoDTO.getNombre());
        actualizar.setFechaactualizacion(LocalDate.now());
        actualizar.setDepartamentoByFkdepartamentosid(departamentoRepository.findById(distritoDTO.getIdDepartamento()).get());
        distritoRepository.save(actualizar);
        response.setCode(200);
        response.setMsg("actualizado");
        response.setData(actualizar);

        return response;
    }

    public Response darDeBaja(Integer id) throws Exception{
        Response response = new Response();
        Distrito darBaja = distritoRepository.findById(id).get();

        if(darBaja == null)
            throw new DistritoErrorToUpdateException();

        darBaja.setHabilitado(0);
        darBaja.setFechabaja(LocalDate.now());

        response.setCode(200);
        response.setMsg("Dado de baja");
        response.setData(distritoRepository.save(darBaja));

        return response;
    }


    private Distrito dtoToDistrito(DistritoDTO distritoDTO) {
        Distrito distrito = new Distrito();
        distrito.setNombre(distritoDTO.getNombre());
        distrito.setAbreviatura(distritoDTO.getAbreviatura());
        distrito.setFechaactualizacion(LocalDate.now());
        distrito.setFechaalta(LocalDate.now());
        distrito.setDepartamentoByFkdepartamentosid(departamentoRepository.findById(distritoDTO.getIdDepartamento()).get());

        return distrito;
    }
}
