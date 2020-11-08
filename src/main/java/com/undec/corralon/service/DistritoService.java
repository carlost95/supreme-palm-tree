package com.undec.corralon.service;

import com.undec.corralon.DTO.DepartamentoDTO;
import com.undec.corralon.DTO.DistritoDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.distrito.DistritoErrorToSaveException;
import com.undec.corralon.excepciones.distrito.DistritoErrorToUpdateException;
import com.undec.corralon.excepciones.distrito.DistritoListNotFoundException;
import com.undec.corralon.excepciones.distrito.DistritoNotFoundException;
import com.undec.corralon.modelo.Departamento;
import com.undec.corralon.modelo.Distrito;
import com.undec.corralon.repository.DepartamentoRepository;
import com.undec.corralon.repository.DistritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        List<Distrito> distritos = distritoRepository.findAllByEstadoTrue();

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
        response.setMsg("Distrito " + id);
        response.setData(distrito);

        return response;
    }

    public Response guardar(DistritoDTO distritoDTO) throws Exception{
        Response response = new Response();
        Distrito distrito = dtoToDistrito(distritoDTO);

        Integer idDepartament = distritoDTO.getDepartamento().getId();
        Departamento departamento = this.departamentoRepository.findById(idDepartament).get();
        distrito.setDepartamento(departamento);

        Distrito guardado = distritoRepository.save(distrito);

        if(guardado == null)
            throw new DistritoErrorToSaveException();

        distritoDTO = distritoToDTO(guardado);
        response.setCode(200);
        response.setMsg("Creado");
        response.setData(distritoDTO);

        return response;
    }

    private DistritoDTO distritoToDTO(Distrito distrito) {
        DistritoDTO distritoDTO = new DistritoDTO();
        distritoDTO.setId(distrito.getId());
        distritoDTO.setNombre(distrito.getNombre());
        distritoDTO.setAbreviatura(distrito.getAbreviatura());
        distritoDTO.setEstado(distrito.getEstado());
        distritoDTO.setDepartamento(this.departamentoToDTO(distrito.getDepartamento()));

        return distritoDTO;
    }

    private DepartamentoDTO departamentoToDTO(Departamento departamento){
        DepartamentoDTO departamentoDTO = new DepartamentoDTO();

        departamentoDTO.setId(departamento.getId());
        departamentoDTO.setNombre(departamento.getNombre());
        departamentoDTO.setAbreviatura(departamento.getAbreviatura());
        departamentoDTO.setEstado(departamento.getEstado());

        return departamentoDTO;
    }


    public Response actualizar(DistritoDTO distritoDTO) throws Exception{
        Response response = new Response();
        Distrito actualizar = distritoRepository.findById(distritoDTO.getId()).get();

        if(actualizar == null)
            throw new DistritoErrorToUpdateException();

        actualizar.setId(distritoDTO.getId());
        actualizar.setNombre(distritoDTO.getNombre());
        actualizar.setAbreviatura(distritoDTO.getAbreviatura());
        actualizar.setNombre(distritoDTO.getNombre());
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

        response.setCode(200);
        response.setMsg("Dado de baja");
        response.setData(distritoRepository.save(darBaja));

        return response;
    }


    private Distrito dtoToDistrito(DistritoDTO distritoDTO) {
        Distrito distrito = new Distrito();
        distrito.setNombre(distritoDTO.getNombre());
        distrito.setAbreviatura(distritoDTO.getAbreviatura());

        return distrito;
    }
}
