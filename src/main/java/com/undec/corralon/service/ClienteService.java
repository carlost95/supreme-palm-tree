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
        return clientes;
    }

    public Cliente getClientById(Integer id) {
        Cliente cliente = clienteRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException("Warning: No se encontro el cliente con id: " + id));
        return cliente;
    }

    public Cliente saveClient(Cliente cliente) {
        validateClient(cliente);
        cliente.setStatus(true);
        cliente = this.clienteRepository.save(cliente);
        if (cliente.toString().isEmpty()) {
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
        validateClientUpdated(clienteResponse);
        clienteResponse = this.clienteRepository.save(clienteResponse);
        if (clienteResponse.toString().isEmpty()) {
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
    private void validateClient(Cliente cliente) {
        validateIsBlank(cliente.getNombre(), "nombre");
        validateIsBlank(cliente.getApellido(), "apellido");
        validateIsBlank(cliente.getDni(), "dni");
        if (clienteRepository.existsClienteByDni(cliente.getDni())){
            throw new NotFoundException("Warning: Ya existe un cliente con el dni: " + cliente.getDni());
        }
    }

    private void validateClientUpdated(Cliente clienteResponse) {
        validateIsBlank(clienteResponse.getNombre(),"nombre");
        validateIsBlank(clienteResponse.getApellido(),"apellido");
        validateIsBlank(clienteResponse.getDni(),"dni");
       if (clienteRepository.existsClienteByDniAndIdClienteNot(clienteResponse.getDni(), clienteResponse.getIdCliente())) {
            throw new NotFoundException("Warning: Ya existe un cliente con el dni: " + clienteResponse.getDni());
        }
    }

    private void validateIsBlank(String value, String name) {
        if (value == null || value.isEmpty()) {
            throw new NotFoundException("Warning: El campo " + name + " no puede estar vacio");
        }
    }
}
