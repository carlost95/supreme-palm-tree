package com.undec.corralon.service;

import com.undec.corralon.DTO.ProveedorDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFounException;
import com.undec.corralon.modelo.Banco;
import com.undec.corralon.modelo.BancoProveedor;
import com.undec.corralon.modelo.Proveedor;
import com.undec.corralon.repository.BancoProveedorRepository;
import com.undec.corralon.repository.BancoRepository;
import com.undec.corralon.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new NotFounException("WARNING: No existen proveedores");
        }
        response.setMsg("Lista de Proveedores");
        response.setCode(200);
        response.setData(proveedores);

        return response;
    }

    public Response listOfSuppliersHabilitation() {
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

    public Response listSuppliersForId(Integer id) {
        Response response = new Response();
        Proveedor proveedor = proveedorRepository.findById(id).
                orElseThrow(() -> new NotFounException("WARNING: No existe el proveedor por id"));

        response.setCode(200);
        response.setMsg("proveedor " + id);
        response.setData(proveedor);
        return response;
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

    public Response updatedSupplier(ProveedorDTO proveedorDTO) {
        Response response = new Response();
        Proveedor proveedor = mappedOfSupplier(proveedorDTO);

        Proveedor proveedorUpdate = proveedorRepository.findById(proveedor.getIdProveedor()).
                orElseThrow(() -> new NotFounException("WARNING: No se encuentra el proveedorDTO a actualizar"));

        proveedorUpdate.setRazonSocial(proveedor.getRazonSocial());
        proveedorUpdate.setDomicilio(proveedor.getDomicilio());
        proveedorUpdate.setEmail(proveedor.getEmail());
        proveedorUpdate.setTelefono(proveedor.getTelefono());

        Proveedor proveedor1 = proveedorRepository.save(proveedorUpdate);
        if (proveedor1 ==null)
            throw new NotFounException("WARNING: No se puede actualizar datos del proveedor o no existe el proveedor");

        mappedDateOfSupplier(proveedor1, proveedorDTO);

        response.setCode(200);
        response.setMsg("Proveedor actualizado correctamente");
        response.setData(proveedor1);
        return response;
    }

    public Response habilitationChange(Integer id) {
        Response response = new Response();

        Proveedor proveedorOptional = proveedorRepository.findById(id).
                orElseThrow(() -> new NotFounException("WARNING: No se encuentra el proveedor para su habilitacion o deshabilitacion"));
        Proveedor proveedor = proveedorOptional;

        proveedor.setHabilitado(!proveedor.getHabilitado());
        if (proveedorRepository.save(proveedor)== null)
            throw new NotFounException("WARNING: No existe el probveedor que se quiere cambiar habiulitacion o es null");

        response.setCode(200);
        response.setMsg("El proveedor cambio el estado exitosamente");
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

        if (proveedorDuplicado(proveedor))
            throw new BadRequestException("WARNING: El proveedor cargado es invalido o ya existe");
        else
            return proveedor;
    }

    private BancoProveedor mappedDateOfSupplier(Proveedor proveedor, ProveedorDTO proveedorDTO) {
        BancoProveedor datosProveedor = new BancoProveedor();
        Banco banco = proveedorDTO.getBanco();

        if (banco == null || banco.getIdBanco() == null)
            throw new BadRequestException("WARNING: No se cargaron los datos del banco para el proveedor");

        datosProveedor.setTitularCuenta(proveedorDTO.getTitularCuenta());
        datosProveedor.setNumeroCuenta(proveedorDTO.getNumeroCuenta());
        datosProveedor.setCbu(proveedorDTO.getCbu());
        datosProveedor.setProveedorByIdProveedor(proveedor);
        datosProveedor.setBancoByIdBanco(banco);

        if (bancoProveedorRepository.save(datosProveedor) == null)
            throw new NotFounException("WARNING: No se puede guardar los datos de banco del proveedor");
        else {
            BancoProveedor dateSupplier = bancoProveedorRepository.save(datosProveedor);
            return dateSupplier;
        }
    }

    private Boolean proveedorDuplicado(Proveedor prov) {
        if (proveedorRepository.existsByRazonSocialAndEmail(prov.getRazonSocial(), prov.getEmail()))
            return true;
        return false;
    }
}