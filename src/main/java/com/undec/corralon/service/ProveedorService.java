package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.proveedor.ProveedorErrorToDownException;
import com.undec.corralon.excepciones.proveedor.ProveedorErrorToSaveException;
import com.undec.corralon.excepciones.proveedor.ProveedorErrorToUpdateException;
import com.undec.corralon.excepciones.proveedor.ProveedorNotFoundException;
import com.undec.corralon.modelo.Proveedor;
import com.undec.corralon.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    public Response listarTodos () throws Exception {
        Response response = new Response();
        List<Proveedor> proveedores = proveedorRepository.findAll();
        if (proveedores == null){
            throw new ProveedorNotFoundException();
        }
        response.setMsg("lista de todos los proveedores");
        response.setCode(200);
        response.setData(proveedores);

        return response;
    }
    public Response listarTodosHabilitados() throws Exception {
        Response response = new Response();
        List <Proveedor> proveedores = proveedorRepository.findAllByHabilitadoEquals((byte) 1);
        if (proveedores == null){
            throw new ProveedorNotFoundException();
        }
        response.setCode(200);
        response.setMsg("Listado de proveedores habilitados");
        response.setData(proveedores);
        return response;
    }
    public Response listarPorId(Integer id) throws Exception{
        Response response = new Response();
        Proveedor proveedor= proveedorRepository.findById(id).get();

        if(proveedor == null)
            throw new ProveedorNotFoundException();

        response.setCode(200);
        response.setMsg("proveedor " + id);
        response.setData(proveedor);

        return response;
    }
    public Response guardarProveedor(Proveedor proveedor) throws Exception {
        Response response = new Response();
        proveedor.setHabilitado((byte) 1);
        Proveedor proveedorSave = proveedorRepository.save(proveedor);
//        cliente.setFechaalta(LocalDate.now());
//        cliente.setFechaactualizacion(LocalDate.now());
//        proveedorSave.setHabilitado((byte) 1);
//        Cliente guardado = clienteRepository.save(cliente);

        if(proveedorSave == null)
            throw new ProveedorErrorToSaveException();

        response.setCode(200);
        response.setMsg("Nuevo Proveedor almacenado");
        response.setData(proveedorSave);

        return response;
    }
    public Response actualizarProveedor(Proveedor proveedor) throws Exception {
        Response response = new Response();
        Proveedor proveedorUpdate = proveedorRepository.findById(proveedor.getId()).get();

        if(proveedorUpdate == null)
            throw new ProveedorErrorToUpdateException();

//        proveedorUpdate.setId(proveedor.getId());
        proveedorUpdate.setRazonSocial(proveedor.getRazonSocial());
        proveedorUpdate.setDomicilio(proveedor.getDomicilio());
        proveedorUpdate.setMail(proveedor.getMail());
        proveedorUpdate.setCelular(proveedor.getCelular());
        proveedorUpdate.setTelefono(proveedor.getTelefono());
        proveedorUpdate.setHabilitado(proveedor.getHabilitado());

        response.setCode(200);
        response.setMsg("aproveedor ctualizado");
        response.setData(proveedorRepository.save(proveedorUpdate));
        return response;
    }
    public Response darBajaProveedor(Integer id) throws Exception{
        Response response = new Response();
        Proveedor proveedorDown = proveedorRepository.findById(id).get();

        if(proveedorDown == null)
            throw new ProveedorErrorToDownException();
        proveedorDown.setHabilitado((byte) 0);
        proveedorRepository.save(proveedorDown);

        response.setCode(200);
        response.setMsg("proveedor deshabilitado");
        response.setData(proveedorDown);


        return response;
    }
}
