package com.undec.corralon.service;

import com.undec.corralon.excepciones.exception.NotFoundException;

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

    public List<Cliente> getAllClient() {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) {
            throw new NotFoundException("No se encontraron clientes");
        }
        if (clientes == null) throw new NotFoundException("Warning: No se encontraron clientes");

        return clientes;
    }

    public List<Cliente> getAllClientEnabled() {
        List<Cliente> clientes = clienteRepository.findByStatusEquals(true);

        if (clientes.isEmpty()) throw new NotFoundException("Warning: No se encontraron clientes habilitados");
        if (clientes == null) throw new NotFoundException("Warning: No se encontraron clientes habilitados");
        return clientes;
    }

    public Cliente getClientById(Integer id) {
        Cliente cliente = clienteRepository.findById(id).
                orElseThrow(
                () -> new NotFoundException("Warning: No se encontro el cliente con id: " + id));
        return cliente;
    }

    public Cliente saveClient(Cliente cliente) {
        cliente.setStatus(true);
        cliente = this.clienteRepository.save(cliente);
        if (cliente == null) {
            throw new NotFoundException("Warning: No se pudo guardar el cliente");
        }
        return cliente;
    }

    public Cliente updatedClient(Cliente cliente) {
        Cliente clienteResponse = this.clienteRepository.findById(cliente.getIdCliente())
                .orElseThrow(
                () -> new NotFoundException("Warning: No se encontro el cliente con id: " + cliente.getIdCliente()));
        clienteResponse.setIdCliente(cliente.getIdCliente());
        clienteResponse.setNombre(cliente.getNombre());
        clienteResponse.setApellido(cliente.getApellido());
        clienteResponse.setDni(cliente.getDni());
        clienteResponse.setEmail(cliente.getEmail());
        clienteResponse.setStatus(cliente.getStatus());
        clienteResponse = this.clienteRepository.save(clienteResponse);
        if (clienteResponse == null) {
            throw new NotFoundException("Warning: No se puede actualizar el cliente");
        }
        return clienteResponse;
    }

    public Cliente changeStatus(Integer idCliente) {
        Cliente clienteResponse = this.clienteRepository.findById(idCliente).orElseThrow(() -> new NotFoundException("Warning: No se encontro el cliente con id: " + idCliente));
        clienteResponse.setStatus(!clienteResponse.getStatus());
        clienteResponse = this.clienteRepository.save(clienteResponse);
        if (clienteResponse == null) {
            throw new NotFoundException("Warning: No se pudo actualizar el cliente");
        }
        return clienteResponse;
    }

}
