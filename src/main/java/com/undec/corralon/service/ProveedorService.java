package com.undec.corralon.service;

import com.undec.corralon.DTO.ProveedorDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFounException;
import com.undec.corralon.excepciones.proveedor.*;
import com.undec.corralon.modelo.Banco;
import com.undec.corralon.modelo.BancoProveedor;
import com.undec.corralon.modelo.Proveedor;
import com.undec.corralon.repository.BancoProveedorRepository;
import com.undec.corralon.repository.BancoRepository;
import com.undec.corralon.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    @Autowired
    BancoRepository bancoRepository;

    @Autowired
    BancoProveedorRepository bancoProveedorRepository;

    public Response listOfAllSuppliers() {
        Response response = new Response();
        List<Proveedor> proveedores = proveedorRepository.findAll();
        if (proveedores == null) {
            throw new NotFounException("WARNING: No existen proveedore");
        }
        response.setMsg("Lista de Proveedores");
        response.setCode(200);
        response.setData(proveedores);

        return response;
    }

    public Response listOfSuppliersHabilitation(){
        Response response = new Response();
        List<Proveedor> proveedores = proveedorRepository.findAllByHabilitadoEquals(true);
        if (proveedores == null) {
            throw new NotFounException("WARNING: No existen proveedores habilitados");
        }
        response.setCode(200);
        response.setMsg("Listado de proveedores habilitados");
        response.setData(proveedores);
        return response;
    }

    public Response listSuppliersForId(Integer id){
        Response response = new Response();
        Proveedor proveedor = proveedorRepository.findById(id).
                orElseThrow(() ->new NotFounException("WARNING: No existe el proveedor por id"));


        response.setCode(200);
        response.setMsg("proveedor " + id);
        response.setData(proveedor);
        return  response;
    }

    public Response saveSupplier(ProveedorDTO proveedorDTO) {
        Response response = new Response();
        Proveedor proveedor = mappedOfSupplier(proveedorDTO);
        Proveedor supplierSave = proveedorRepository.save(proveedor);

        if (supplierSave == null) {
            throw new NotFounException("WARNING: No se puede guardar el provedor");
        }
        mappedDateOfSupplier(supplierSave, proveedorDTO);

        response.setCode(200);
        response.setMsg("Proveedor creado correctamente");
        response.setData(supplierSave);

        return response;
    }

    public Response updatedSupplier(Proveedor proveedor) {
        Response response = new Response();
        Proveedor proveedorUpdate = proveedorRepository.findById(proveedor.getIdProveedor()).get();

        if (proveedorUpdate == null)
            throw new ProveedorErrorToUpdateException();

        proveedorUpdate.setRazonSocial(proveedor.getRazonSocial());
        proveedorUpdate.setDomicilio(proveedor.getDomicilio());
        proveedorUpdate.setEmail(proveedor.getEmail());
        proveedorUpdate.setTelefono(proveedor.getTelefono());

        response.setCode(200);
        response.setMsg("Proveedor actualizado correctamente");
        response.setData(proveedorRepository.save(proveedorUpdate));
        return response;
    }

    public Response habilitationChange(Integer id) throws ProveedorCambioEstadoException {
        Response response = new Response();

        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
        if (!proveedorOptional.isPresent()) {
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

    private Proveedor mappedOfSupplier(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = new Proveedor();

        proveedor.setRazonSocial(proveedorDTO.getRazonSocial());
        proveedor.setEmail(proveedorDTO.getEmail());
        proveedor.setTelefono(proveedorDTO.getTelefono());
        proveedor.setDomicilio(proveedorDTO.getDomicilio());
        proveedor.setHabilitado(true);

        if(proveedorDuplicado(proveedor))
            throw new BadRequestException("WARNING: El proveedor cargado es invalido o ya existe");
        else
            return proveedor;
    }

    private BancoProveedor mappedDateOfSupplier(Proveedor proveedor, ProveedorDTO proveedorDTO) {
        BancoProveedor datosProveedor = new BancoProveedor();
        Banco banco = proveedorDTO.getBanco();

        if (banco.getIdBanco()== null)
            throw new BadRequestException("WARNING: No se cargaron los datos del banco para el proveedor");

        datosProveedor.setTitularCuenta(proveedorDTO.getTitularCuenta());
        datosProveedor.setNumeroCuenta(proveedorDTO.getNumeroCuenta());
        datosProveedor.setCbu(proveedorDTO.getCbu());
        datosProveedor.setProveedorByIdProveedor(proveedor);
        datosProveedor.setBancoByIdBanco(banco);

        if(bancoProveedorRepository.save(datosProveedor) == null)
            throw new NotFounException("WARNING: No se puede guardar los datos del banco del proveedor");
        else{
            BancoProveedor dateSupplier = bancoProveedorRepository.save(datosProveedor);
            return dateSupplier;
        }
    }
    private Boolean proveedorDuplicado(Proveedor prov){
        if (proveedorRepository.existsByRazonSocialAndEmail(prov.getRazonSocial(), prov.getEmail()))
            return true;
        return false;
    }
}