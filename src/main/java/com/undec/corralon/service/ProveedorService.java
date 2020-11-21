package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.banco.BancoCambioEstadoException;
import com.undec.corralon.excepciones.proveedor.*;
import com.undec.corralon.modelo.Proveedor;
import com.undec.corralon.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        response.setMsg("Lista de todos los proveedores");
        response.setCode(200);
        response.setData(proveedores);

        return response;
    }
    public Response listarTodosHabilitados() throws Exception {
        Response response = new Response();
        List <Proveedor> proveedores = proveedorRepository.findAllByHabilitadoEquals((boolean) true);
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
        proveedor.setHabilitado((boolean) true);
        Proveedor proveedorSave = proveedorRepository.save(proveedor);

        if(proveedorSave == null)
            throw new ProveedorErrorToSaveException();

        response.setCode(200);
        response.setMsg("Proveedor creado correctamente");
        response.setData(proveedorSave);

        return response;
    }
    public Response actualizarProveedor(Proveedor proveedor) throws Exception {
        Response response = new Response();
        Proveedor proveedorUpdate = proveedorRepository.findById(proveedor.getId()).get();

        if(proveedorUpdate == null)
            throw new ProveedorErrorToUpdateException();

        proveedorUpdate.setRazonSocial(proveedor.getRazonSocial());
        proveedorUpdate.setDomicilio(proveedor.getDomicilio());
        proveedorUpdate.setMail(proveedor.getMail());
        proveedorUpdate.setCelular(proveedor.getCelular());
        proveedorUpdate.setTelefono(proveedor.getTelefono());

        response.setCode(200);
        response.setMsg("Proveedor actualizado correctamente");
        response.setData(proveedorRepository.save(proveedorUpdate));
        return response;
    }

    public Response cambiarHabilitacion(Integer id) throws ProveedorCambioEstadoException {
        Response response = new Response();

        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
        if (!proveedorOptional.isPresent()){
            throw new ProveedorCambioEstadoException();
        }
        Proveedor proveedor = proveedorOptional.get();
        proveedor.setHabilitado(!proveedor.getHabilitado());
        proveedorRepository.save(proveedor);

        response.setCode(200);
        response.setMsg("El proveedor cambio el estado");
        response.setData(proveedor);
        return response;
    }
}
