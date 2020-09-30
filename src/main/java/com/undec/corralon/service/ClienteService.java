package com.undec.corralon.service;

import com.undec.corralon.DTO.ClienteDTO;
import com.undec.corralon.DTO.DireccionDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.cliente.ClienteErrorToUpdateException;
import com.undec.corralon.excepciones.cliente.ClienteListNoFoudException;
import com.undec.corralon.excepciones.cliente.ClienteNotFounsException;
import com.undec.corralon.modelo.Cliente;
import com.undec.corralon.modelo.Direccion;
import com.undec.corralon.repository.ClienteRepository;
import com.undec.corralon.serviceData.DireccionServiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DireccionServiceData direccionServiceData;

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
        List<Cliente> clientes = clienteRepository.findAllByEstadoTrue();
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

    public Response save(ClienteDTO clienteDTO) throws Exception {

        Response response = new Response();
        Cliente toSave = mapperDTOData(clienteDTO);
        toSave.setEstado(true);
        toSave = this.clienteRepository.save(toSave);
//        clienteDTO.setId(toSave.getId());

//        if(toSave == null){
//            throw new ClienteErrorToSaveException();
//        }
//        toSave.setDirecciones(saveDirections(clienteDTO));
        response.setCode(200);
        response.setMsg("Creado");
        response.setData(toSave);

        return response;
    }

    private List<Direccion> saveDirections(ClienteDTO clienteDTO) throws Exception {
        List<Direccion> direcciones = new ArrayList<>();
        for (DireccionDTO direccionDTO: clienteDTO.getDirecciones()) {
            direccionDTO.setClienteId(clienteDTO.getId());
            direcciones.add(this.direccionServiceData.save(direccionDTO));
        }
        return direcciones;
    }

    private Cliente mapperDTOData(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setDni(clienteDTO.getDni());
        cliente.setMail(clienteDTO.getMail());
        return cliente;
    }

    private ClienteDTO entityToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setId(cliente.getId());
        clienteDTO.setApellido(cliente.getApellido());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setDni(cliente.getDni());
//        clienteDTO.setDirecciones(cliente.getDirecciones());
        return clienteDTO;
    }

    public Response actualizar(Cliente cliente) throws Exception {
        Response response = new Response();
        Cliente actualizar = clienteRepository.findById(cliente.getId()).get();

        if(actualizar == null)
                throw new ClienteErrorToUpdateException();

        actualizar.setNombre(cliente.getNombre());
        actualizar.setApellido(cliente.getApellido());
        actualizar.setDni(cliente.getDni());

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

        clienteRepository.save(darBaja);

        response.setCode(200);
        response.setMsg("Dado de baja");
        response.setData(darBaja);

        return response;
    }

}
