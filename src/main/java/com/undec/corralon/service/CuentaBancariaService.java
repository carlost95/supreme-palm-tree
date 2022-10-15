package com.undec.corralon.service;

import com.undec.corralon.DTO.CuentaBancariaDTO;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.Banco;
import com.undec.corralon.modelo.CuentaBancaria;
import com.undec.corralon.modelo.Proveedor;
import com.undec.corralon.repository.BancoRepository;
import com.undec.corralon.repository.CuentaBancariaRepository;
import com.undec.corralon.repository.ProveedorRepository;
import org.hibernate.tuple.IdentifierAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuentaBancariaService {
    @Autowired
    CuentaBancariaRepository cuentaBancariaRepository;
    @Autowired
    BancoRepository bancoRepository;
    @Autowired
    ProveedorRepository proveedorRepository;

    public CuentaBancariaDTO findAccountById(Integer id) {
        CuentaBancaria cuentaBancaria = cuentaBancariaRepository.findById(id).orElseThrow(() -> new NotFoundException("WARNING: No existe la cuenta bancaria por este id"));
        CuentaBancariaDTO cuentaBancariaDTO = mappedAccountBanckToDTO(cuentaBancaria);
        return cuentaBancariaDTO;
    }

    public List<CuentaBancariaDTO> findAccountByIdProveedor(Integer id) {
        List<CuentaBancariaDTO> cuentasBancariasDTO = new ArrayList<CuentaBancariaDTO>();
        List<CuentaBancaria> cuentasBancarias = cuentaBancariaRepository.findAllCuentaBancariaByIdProveedor(id);
        if (cuentasBancarias.isEmpty()) {
            throw new NotFoundException("\nWARNING: No existen cuentas bancarias registradas para este proveedor");
        }
        for (CuentaBancaria cuentaBancaria : cuentasBancarias) {
            cuentasBancariasDTO.add(mappedAccountBanckToDTO(cuentaBancaria));
        }
        return cuentasBancariasDTO;
    }

    public CuentaBancariaDTO createAccountBank(CuentaBancariaDTO cuentaBancariaDTO) {
        CuentaBancaria cuentaBancaria = mappingCuentaBancariaDTO(cuentaBancariaDTO);
        validatedDataAccountBank(cuentaBancaria);
        cuentaBancaria.setHabilitado(true);
        cuentaBancaria = cuentaBancariaRepository.save(cuentaBancaria);

        if (cuentaBancaria.toString().isEmpty()) {
            throw new NotFoundException("\nWARNING: No se puede guardar la cuenta bancaria");
        }
        cuentaBancariaDTO = mappedAccountBanckToDTO(cuentaBancaria);
        return cuentaBancariaDTO;

    }

    public CuentaBancariaDTO updateAccountBank(CuentaBancariaDTO cuentaBancariaDTO) {
        Banco banco = bancoRepository.findById(
                        cuentaBancariaDTO.getIdBanco())
                .orElseThrow(
                        () -> new NotFoundException("WARNING: No existe el banco por este id para la cuenta bancaria"));
        Proveedor proveedor = proveedorRepository.findById(
                        cuentaBancariaDTO.getIdProveedor())
                .orElseThrow(
                        () -> new NotFoundException("WARNING: No existe el proveedor por este id para la cuenta bancaria"));
        CuentaBancaria cuentaBancariaUpdated = cuentaBancariaRepository.findById(
                        cuentaBancariaDTO.getId())
                .orElseThrow(
                        () -> new NotFoundException("WARNING: No existe la cuenta bancaria con este id"));

        validateExistenceOfAlias(cuentaBancariaDTO.getAlias(), cuentaBancariaDTO.getId());
        validateExistenceOfNumber(cuentaBancariaDTO.getNumero(), cuentaBancariaDTO.getId());
        validateExistenceOfCbu(cuentaBancariaDTO.getCbu(), cuentaBancariaDTO.getId());

        cuentaBancariaUpdated.setNumero(cuentaBancariaDTO.getNumero());
        cuentaBancariaUpdated.setTitular(cuentaBancariaDTO.getTitular());
        cuentaBancariaUpdated.setCbu(cuentaBancariaDTO.getCbu());
        cuentaBancariaUpdated.setAlias(cuentaBancariaDTO.getAlias());
        cuentaBancariaUpdated.setHabilitado(cuentaBancariaDTO.getHabilitado());
        cuentaBancariaUpdated.setBanco(banco);
        cuentaBancariaUpdated.setProveedor(proveedor);

        cuentaBancariaUpdated = cuentaBancariaRepository.save(cuentaBancariaUpdated);
        if (cuentaBancariaUpdated == null) {
            throw new NotFoundException("\nWARNING: No se puede actualizar la cuenta bancaria");
        }
        cuentaBancariaDTO = mappedAccountBanckToDTO(cuentaBancariaUpdated);
        return cuentaBancariaDTO;
    }

    public CuentaBancariaDTO changeStatusAccountBank(Integer id) {
        CuentaBancaria cuentaBancaria = cuentaBancariaRepository.findById(id).orElseThrow(() -> new NotFoundException("WARNING: No existe la cuenta bancaria por este id"));

        cuentaBancaria.setHabilitado(!cuentaBancaria.getHabilitado());
        cuentaBancaria = cuentaBancariaRepository.save(cuentaBancaria);
        if (cuentaBancaria == null) {
            throw new NotFoundException("\nWARNING: No se puede actualizar la cuenta bancaria");
        }
        CuentaBancariaDTO cuentaBancariaDTO = mappedAccountBanckToDTO(cuentaBancaria);
        return cuentaBancariaDTO;
    }

    private CuentaBancariaDTO mappedAccountBanckToDTO(CuentaBancaria cuentaBancaria) {
        CuentaBancariaDTO cuentaBancariaDTO = new CuentaBancariaDTO();
        cuentaBancariaDTO.setId(cuentaBancaria.getId());
        cuentaBancariaDTO.setNumero(cuentaBancaria.getNumero());
        cuentaBancariaDTO.setTitular(cuentaBancaria.getTitular());
        cuentaBancariaDTO.setCbu(cuentaBancaria.getCbu());
        cuentaBancariaDTO.setAlias(cuentaBancaria.getAlias());
        cuentaBancariaDTO.setHabilitado(cuentaBancaria.getHabilitado());
        cuentaBancariaDTO.setIdBanco(cuentaBancaria.getBanco().getIdBanco());
        cuentaBancariaDTO.setIdProveedor(cuentaBancaria.getProveedor().getIdProveedor());
        return cuentaBancariaDTO;
    }

    private CuentaBancaria mappingCuentaBancariaDTO(CuentaBancariaDTO cuentaBancariaDTO) {
        CuentaBancaria cuentaBancaria = new CuentaBancaria();
        Banco banco = bancoRepository.findById(cuentaBancariaDTO.getIdBanco()).get();
        Proveedor proveedor = proveedorRepository.findById(cuentaBancariaDTO.getIdProveedor()).get();

        cuentaBancaria.setNumero(cuentaBancariaDTO.getNumero());
        cuentaBancaria.setTitular(cuentaBancariaDTO.getTitular());
        cuentaBancaria.setCbu(cuentaBancariaDTO.getCbu());
        cuentaBancaria.setAlias(cuentaBancariaDTO.getAlias());
        cuentaBancaria.setHabilitado(cuentaBancariaDTO.getHabilitado());
        cuentaBancaria.setBanco(banco);
        cuentaBancaria.setProveedor(proveedor);
        return cuentaBancaria;
    }

    private void validatedDataAccountBank(CuentaBancaria cuentaBancaria) {
        validateEmptyDataAccountBank(cuentaBancaria.getAlias());
        validateEmptyDataAccountBank(cuentaBancaria.getNumero());
        validateEmptyDataAccountBank(cuentaBancaria.getTitular());
        validateEmptyDataAccountBank(cuentaBancaria.getCbu());
        validateEmptyDataAccountBank(cuentaBancaria.getBanco().getIdBanco().toString());
        validateEmptyDataAccountBank(cuentaBancaria.getProveedor().getIdProveedor().toString());

        validarAlias(cuentaBancaria.getAlias());
        validarNumeroCuenta(cuentaBancaria.getNumero());
        validarCbu(cuentaBancaria.getCbu());

    }

    private void validateEmptyDataAccountBank(String data) {
        if (data.isEmpty()) {
            throw new NotFoundException("WARNING: " + data + " no puede estar vacio");
        }
    }

    private void validarAlias(String alias) {
        if (cuentaBancariaRepository.existsCuentaBancariaByAlias(alias)) {
            throw new NotFoundException("WARNING: El alias "
                    + alias + " se encuentra registrado en otra cuanta bancaria");
        }
    }

    private void validarNumeroCuenta(String numero) {
        if (cuentaBancariaRepository.existsCuentaBancariaByNumero(numero)) {
            throw new NotFoundException("WARNING: El numero de cuenta "
                    + numero + " se encuentra registrado en otra cuanta bancaria");
        }
    }

    private void validarCbu(String cbu) {
        if (cuentaBancariaRepository.existsCuentaBancariaByCbu(cbu)) {
            throw new NotFoundException("WARNING: El cbu "
                    + cbu + " se encuentra registrado en otra cuanta bancaria");
        }
    }
    private void validateExistenceOfAlias(String alias, Integer id) {
        if (cuentaBancariaRepository.existsCuentaBancariasByAliasAndIdIsNot(alias, id)) {
            throw new NotFoundException("WARNING: El alias "
                    + alias + " se encuentra registrado en otra cuanta bancaria");
        }
    }
    private void validateExistenceOfNumber(String number, Integer id) {
        if (cuentaBancariaRepository.existsCuentaBancariaByNumeroAndIdNot(number, id)) {
            throw new NotFoundException("WARNING: El numero de cuenta "
                    + number + " se encuentra registrado en otra cuanta bancaria");
        }
    }
    private void validateExistenceOfCbu(String cbu, Integer id) {
        if (cuentaBancariaRepository.existsCuentaBancariaByCbuAndIdNot(cbu, id)) {
            throw new NotFoundException("WARNING: El cbu "
                    + cbu + " se encuentra registrado en otra cuanta bancaria");
        }
    }

}
