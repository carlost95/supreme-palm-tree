package com.undec.corralon.service;

import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.Empresa;
import com.undec.corralon.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;

    public List<Empresa> findAllEmpresas() {
        List<Empresa> empresas = empresaRepository.findAll();
        if (empresas == null) {
            throw new NotFoundException("No se encontraron empresas");
        }
        return empresas;
    }

    public List<Empresa> findAllByStatusIsTrue() {
        List<Empresa> empresas = empresaRepository.findAllByStatusEquals(true);
        if (empresas == null || empresas.isEmpty()) {
            throw new NotFoundException("No se encontraron empresas habilitadas");
        }
        return empresas;
    }

    public Empresa findEmpresaById(Integer id) {
        return empresaRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException("No se encontro la empresa o el identificador " + id + " esta mal"));
    }

    public Empresa saveEmpresa(Empresa empresa) {
        validarEmpresa(empresa);
        validateExistEmpresa(empresa);
        empresa.setStatus(true);
        empresa = empresaRepository.save(empresa);
        if (empresa == null || empresa.toString().isEmpty()) {
            throw new NotFoundException("Error al  guardar la empresa");
        }
        return empresa;
    }

    public Empresa updateEmpresa(Empresa empresaRequest) {
        Empresa empresaResponse = new Empresa();
        validarEmpresa(empresaRequest);
        validateExistEmpresaIdDistint(empresaRequest);
        empresaResponse = empresaRepository.findById(empresaRequest.getIdEmpresa())
                .map(empresa -> {
                    empresa.setIdEmpresa(empresaRequest.getIdEmpresa());
                    empresa.setRazonSocial(empresaRequest.getRazonSocial());
                    empresa.setCuit(empresaRequest.getCuit());
                    empresa.setTelefono(empresaRequest.getTelefono());
                    empresa.setEmail(empresaRequest.getEmail());
                    empresa.setDomicilio(empresaRequest.getDomicilio());
                    empresa.setStatus(empresaRequest.getStatus());
                    return empresaRepository.save(empresa);
                }).orElseThrow(
                        () -> new NotFoundException("No se encontro la empresa o el identificador " + empresaRequest.getIdEmpresa() + " esta mal"));

        return empresaResponse;
    }

    public Empresa changeStatusEmpresa(Integer id) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(() -> new NotFoundException("No se encontro la empresa o el identificador " + id + " esta mal"));
        empresa.setStatus(!empresa.getStatus());
        empresa = empresaRepository.save(empresa);
        if (empresa == null) {
            throw new NotFoundException("No se pudo cambiar el estado de la empresa");
        }
        return empresa;
    }

    private void validarEmpresa(Empresa empresa) {
        validatenullOrEmpty(empresa.getRazonSocial(), "Razon Social");
        validatenullOrEmpty(empresa.getCuit(), "Cuit");
        validatenullOrEmpty(empresa.getDomicilio(), "Domicilio");
        validatenullOrEmpty(empresa.getTelefono(), "Telefono");
        validatenullOrEmpty(empresa.getEmail(), "Email");
    }

    private void validateExistEmpresaIdDistint(Empresa empresa) {
        Empresa empresa1 = empresaRepository.findByCuit(empresa.getCuit());
        if (empresa1 != null && empresa1.getIdEmpresa() != empresa.getIdEmpresa()) {
            throw new NotFoundException("Ya existe una empresa con el cuit " + empresa.getCuit());
        }
    }

    private void validateExistEmpresa(Empresa empresa) {
        Empresa empresa1 = empresaRepository.findByCuit(empresa.getCuit());
        if (empresa1 != null) {
            throw new NotFoundException("Ya existe una empresa con el cuit " + empresa.getCuit());
        }
    }

    private void validatenullOrEmpty(String value, String campo) {
        if (value == null || value.isEmpty()) {
            throw new NotFoundException("El campo " + campo + " no puede estar vacio");
        }
    }
}
