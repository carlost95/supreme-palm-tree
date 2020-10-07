package com.undec.corralon.service;

import com.undec.corralon.DTO.ClienteDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.cliente.ClienteErrorToUpdateException;
import com.undec.corralon.excepciones.cliente.ClienteListNoFoudException;
import com.undec.corralon.excepciones.cliente.ClienteNotFounsException;
import com.undec.corralon.modelo.Cliente;
import com.undec.corralon.repository.ClienteRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private ClienteRepository clienteRepository;

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
        response.setCode(200);
        response.setMsg("Creado");
        response.setData(toSave);
        logger.info("ClienteService: save");

        return response;
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
        return clienteDTO;
    }

    public Response update(ClienteDTO clienteDTO) throws Exception {
        Response response = new Response();
        Cliente toUpdate = mapperDTOData(clienteDTO);
        toUpdate.setId(clienteDTO.getId());
        toUpdate.setEstado(clienteDTO.getEstado());
        toUpdate = this.clienteRepository.save(toUpdate);
        response.setCode(200);
        response.setMsg("Actualizado");
        response.setData(toUpdate);
        logger.info("ClienteService: update");

        return response;
    }

    public Response changeStatus(Integer idCliente) throws Exception {
        Response response = new Response();
        Cliente toUpdate = this.clienteRepository.findById(idCliente).get();
        toUpdate.setEstado(!toUpdate.getEstado());
        toUpdate = this.clienteRepository.save(toUpdate);
        response.setCode(200);
        response.setMsg("Changed Status");
        response.setData(toUpdate);
        logger.info("ClienteService: Change status");
        return response;
    }

}
