package com.undec.corralon.service;

import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.Proveedor;
import com.undec.corralon.repository.BancoRepository;
import com.undec.corralon.repository.CuentaBancariaRepository;
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
    CuentaBancariaRepository cuentaBancariaRepository;

    public List<Proveedor> listOfAllSuppliers() {
        List<Proveedor> proveedores = proveedorRepository.findAll();
        if (proveedores == null) {
            throw new NotFoundException("WARNING: No existen proveedores");
        }
        return proveedores;
    }

    public List<Proveedor> listOfSuppliersHabilitation() {
        List<Proveedor> proveedores = proveedorRepository.findAllByHabilitadoEquals(true);
        if (proveedores == null) {
            throw new NotFoundException("\nWARNING: No existen proveedores habilitados");
        }
        return proveedores;
    }

    public Proveedor listSuppliersForId(Integer id) {
        Proveedor proveedor = proveedorRepository.findById(id).
                orElseThrow(() -> new NotFoundException("\nWARNING: No existe el proveedor por id"));

        return proveedor;
    }

    public Proveedor saveSupplier(Proveedor proveedor) {
        Proveedor proveedorToSave = new Proveedor();
        proveedorToSave.setRazonSocial(proveedor.getRazonSocial());
        proveedorToSave.setDomicilio(proveedor.getDomicilio());
        proveedorToSave.setEmail(proveedor.getEmail());
        proveedorToSave.setCuit(proveedor.getCuit());
        proveedorToSave.setTelefono(proveedor.getTelefono());
        proveedorToSave.setHabilitado(true);

        if (proveedorDuplicado(proveedorToSave.getCuit())) {
            throw new BadRequestException("\nWARNING: El proveedor cargado es invalido o ya existe");
        }
        proveedorToSave = proveedorRepository.save(proveedorToSave);

        if (proveedorToSave == null||proveedorToSave.toString().isEmpty()) {
            throw new NotFoundException("\nWARNING: No se puede guardar el provedor");
        }
        return proveedorToSave;
    }

    public Proveedor updatedSupplier(Proveedor proveedor) {
        Proveedor proveedorUpdate = proveedorRepository.findById(proveedor.getIdProveedor()).
                orElseThrow(() -> new NotFoundException("\nWARNING: No se encuentra el PROVEEDOR a actualizar"));
        if (validationCuitSupplierExist(proveedor.getCuit(), proveedor.getIdProveedor())) {
            throw new BadRequestException("\nWARNING: El CUIT cargado ya existe en otro proveedor");
        }
        proveedorUpdate.setRazonSocial(proveedor.getRazonSocial());
        proveedorUpdate.setDomicilio(proveedor.getDomicilio());
        proveedorUpdate.setEmail(proveedor.getEmail());
        proveedorUpdate.setCuit(proveedor.getCuit());
        proveedorUpdate.setTelefono(proveedor.getTelefono());
        proveedorUpdate.setHabilitado(proveedor.getHabilitado());

        proveedorUpdate = proveedorRepository.save(proveedorUpdate);
        if (proveedorUpdate == null)
            throw new NotFoundException("\nWARNING: No se puede actualizar datos del proveedor o no existe el proveedor");

        return proveedorUpdate;
    }

    public Proveedor habilitationChange(Integer id) {
        Proveedor proveedor = proveedorRepository.findById(id).
                orElseThrow(() -> new NotFoundException("\nWARNING: No se encuentra el proveedor para su habilitacion o deshabilitacion"));

        proveedor.setHabilitado(!proveedor.getHabilitado());
        proveedor = proveedorRepository.save(proveedor);
        if (proveedor == null)
            throw new NotFoundException("\nWARNING: No existe el probveedor que se quiere cambiar habiulitacion o es null");
        return proveedor;
    }

    private Boolean proveedorDuplicado(String cuit) {
        return proveedorRepository.existsByCuit(cuit) ? true : false;
    }
    private Boolean validationCuitSupplierExist(String cuit, Integer id) {
        return proveedorRepository.existsByCuitAndIdProveedorNot(cuit, id) ? true : false;
    }
}
