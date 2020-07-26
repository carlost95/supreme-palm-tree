package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.tipoDireccion.*;
import com.undec.corralon.modelo.Tipodireccion;
import com.undec.corralon.repository.TipodireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TipoDireccionService {
@Autowired
    TipodireccionRepository tipodireccionRepository;

    public Response listarTodos() throws Exception{
        Response response = new Response();
        List<Tipodireccion> tipoDirecciones = tipodireccionRepository.findAll();

        if(tipoDirecciones == null)
            throw new TipoDireccionListNotFoundException();

        response.setCode(200);
        response.setMsg("Listado departamentos");
        response.setData(tipoDirecciones);

        return response;
    }
    public Response listarTodosHabilitados() throws Exception{
        Response response = new Response();
        List<Tipodireccion> tipoDirecciones = tipodireccionRepository.findAllByHabilitadoEquals(1);

        if(tipoDirecciones == null)
            throw new TipoDireccionListNotFoundException();

        response.setCode(200);
        response.setMsg("Listado departamentos");
        response.setData(tipoDirecciones);

        return response;
    }
    public Response listarPorId(Integer id) throws Exception{
        Response response = new Response();
        Tipodireccion tipodireccion = tipodireccionRepository.findById(id).get();

        if(tipodireccion == null)
            throw new TipoDireccionNotFounException();

        response.setCode(200);
        response.setMsg("TipoDireccion " + id);
        response.setData(tipodireccion);

        return response;
    }
    public Response guardar(Tipodireccion tipodireccion) throws Exception{
        Response response = new Response();
        tipodireccion.setFechaalta(LocalDate.now());
        tipodireccion.setFechaactualizacion(LocalDate.now());
        Tipodireccion guardado = tipodireccionRepository.save(tipodireccion);

        if(guardado == null)
            throw new TipoDireccionErrorToSaveException();

        response.setCode(200);
        response.setMsg("Creado");
        response.setData(guardado);

        return response;
    }

    public Response actualizar(Tipodireccion tipodireccion) throws Exception{
        Response response = new Response();
        Tipodireccion actualizar = tipodireccionRepository.findById(tipodireccion.getId()).get();

        if(actualizar == null)
            throw new TipoDireccionErrorToUpdateException();

        actualizar.setNombre(tipodireccion.getNombre());
        actualizar.setAbreviatura(tipodireccion.getAbreviatura());
        actualizar.setFechaactualizacion(LocalDate.now());
        actualizar.setHabilitado(tipodireccion.getHabilitado());

        response.setCode(200);
        response.setMsg("actualizado");
        response.setData(tipodireccionRepository.save(actualizar));

        return response;
    }

    public Response darDeBaja(Integer id) throws Exception{
        Response response = new Response();
        Tipodireccion darBaja = tipodireccionRepository.findById(id).get();

        if(darBaja == null)
            throw new TipoDireccionErrorToDownException();

        darBaja.setHabilitado(0);
        darBaja.setFechabaja(LocalDate.now());
        tipodireccionRepository.save(darBaja);

        response.setCode(200);
        response.setMsg("Dado de baja");
        response.setData(darBaja);
        return response;
    }
}
