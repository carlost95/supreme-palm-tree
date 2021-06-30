package com.undec.corralon.service;

import com.undec.corralon.DTO.ProveedorDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
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
            throw new NotFoundException("WARNING: No existen proveedores");
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
            throw new NotFoundException("WARNING: No existen proveedores habilitados");
        }
        response.setCode(200);
        response.setMsg("Listado de proveedores habilitados");
        response.setData(proveedores);
        return response;
    }

    public Proveedor listSuppliersForId(Integer id) {
        Proveedor proveedor = proveedorRepository.findById(id).
                orElseThrow(() -> new NotFoundException("WARNING: No existe el proveedor por id"));

        return proveedor;
    }

    public Proveedor saveSupplier(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = mappedOfSupplier(proveedorDTO);
        Proveedor supplierSave = proveedorRepository.save(proveedor);

        if (supplierSave == null) {
            throw new NotFoundException("WARNING: No se puede guardar el provedor");
        }
        mappedDateOfSupplier(supplierSave, proveedorDTO);

        return proveedor;
    }

    public Proveedor updatedSupplier(ProveedorDTO proveedorDTO) {
        Proveedor proveedorUpdate = proveedorRepository.findById(proveedorDTO.getIdProveedor()).
                orElseThrow(() -> new NotFoundException("WARNING: No se encuentra el proveedorDTO a actualizar"));

        proveedorUpdate.setRazonSocial(proveedorDTO.getRazonSocial());
        proveedorUpdate.setDomicilio(proveedorDTO.getDomicilio());
        proveedorUpdate.setEmail(proveedorDTO.getEmail());
        proveedorUpdate.setTelefono(proveedorDTO.getTelefono());
        proveedorUpdate.setHabilitado(proveedorDTO.getHabilitado());

        proveedorUpdate = proveedorRepository.save(proveedorUpdate);
        if (proveedorUpdate == null)
            throw new NotFoundException("WARNING: No se puede actualizar datos del proveedor o no existe el proveedor");

        mappedDateOfSupplier(proveedorUpdate, proveedorDTO);

        return proveedorUpdate;
    }

    public Proveedor habilitationChange(Integer id) {
        Proveedor proveedorOptional = proveedorRepository.findById(id).
                orElseThrow(() -> new NotFoundException("WARNING: No se encuentra el proveedor para su habilitacion o deshabilitacion"));
        Proveedor proveedor = proveedorOptional;

        proveedor.setHabilitado(!proveedor.getHabilitado());
        if (proveedorRepository.save(proveedor) == null)
            throw new NotFoundException("WARNING: No existe el probveedor que se quiere cambiar habiulitacion o es null");

        return proveedor;
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
            throw new NotFoundException("WARNING: No se puede guardar los datos de banco del proveedor");
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