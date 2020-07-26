package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.cliente.ClienteErrorToSaveException;
import com.undec.corralon.excepciones.cliente.ClienteErrorToUpdateException;
import com.undec.corralon.excepciones.cliente.ClienteListNoFoudException;
import com.undec.corralon.excepciones.cliente.ClienteNotFounsException;
import com.undec.corralon.modelo.Cliente;
import com.undec.corralon.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;


    public Response listarTodos()throws Exception{
        Response response = new Response();
        List<Cliente> clientes = clienteRepository.findAll();

        if(clientes == null)
            throw new ClienteListNoFoudException();

        response.setCode(200);
        response.setMsg("Listado Clientes");
        response.setData(clientes);

        return response;
    }
    public Response listarTodosHabilitados() throws Exception{
        Response response = new Response();
        List<Cliente> clientes = clienteRepository.findAllByHabilitacionEquals(1);
        if(clientes == null)
            throw new ClienteListNoFoudException();

        response.setCode(200);
        response.setMsg("Listado Clientes habiltados");
        response.setData(clientes);

        return response;
    }
    public Response listarPorId(Integer id) throws Exception{
        Response response = new Response();
        Cliente cliente= clienteRepository.findById(id).get();

        if(cliente == null)
            throw new ClienteNotFounsException();

        response.setCode(200);
        response.setMsg("Departamento " + id);
        response.setData(cliente);

        return response;
    }
    public Response guardar(Cliente cliente) throws Exception {
        Response response = new Response();
        cliente.setFechaalta(LocalDate.now());
        cliente.setFechaactualizacion(LocalDate.now());
        Cliente guardado = clienteRepository.save(cliente);

        if(guardado == null)
            throw new ClienteErrorToSaveException();

        response.setCode(200);
        response.setMsg("Creado");
        response.setData(guardado);

        return response;
    }
    public Response actualizar(Cliente cliente) throws Exception {
        Response response = new Response();
        Cliente actualizar = clienteRepository.findById(cliente.getId()).get();

        if(actualizar == null)
                throw new ClienteErrorToUpdateException();

        actualizar.setNombre(cliente.getNombre());
        actualizar.setApellido(cliente.getApellido());
        actualizar.setDni(cliente.getDni());
        actualizar.setFechaactualizacion(LocalDate.now());
        actualizar.setHabilitacion(cliente.getHabilitacion());

        response.setCode(200);
        response.setMsg("actualizado");
        response.setData(clienteRepository.save(actualizar));
        return response;
    }
    public Response darDeBaja(Integer id) throws Exception{
        Response response = new Response();
        Cliente darBaja = clienteRepository.findById(id).get();

        if(darBaja == null)
            throw new ClienteErrorToUpdateException();

        darBaja.setHabilitacion(0);
        darBaja.setFechabaja(LocalDate.now());
        clienteRepository.save(darBaja);

        response.setCode(200);
        response.setMsg("Dado de baja");
        response.setData(darBaja);


        return response;
    }


}
