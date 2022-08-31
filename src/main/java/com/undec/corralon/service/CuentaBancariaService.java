package com.undec.corralon.service;

import com.undec.corralon.DTO.CuentaBancariaDTO;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.Banco;
import com.undec.corralon.modelo.CuentaBancaria;
import com.undec.corralon.modelo.Proveedor;
import com.undec.corralon.repository.BancoRepository;
import com.undec.corralon.repository.CuentaBancariaRepository;
import com.undec.corralon.repository.ProveedorRepository;
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
        CuentaBancaria cuentaBancaria = cuentaBancariaRepository.findById(id).
                orElseThrow(() -> new NotFoundException("WARNING: No existe la cuenta bancaria por este id"));
        CuentaBancariaDTO cuentaBancariaDTO = mappedAccountBanckToDTO(cuentaBancaria);
        return cuentaBancariaDTO;
    }

    public List<CuentaBancariaDTO> findAccountByIdProveedor(Integer id) {
        List<CuentaBancariaDTO> cuentasBancariasDTO = new ArrayList<CuentaBancariaDTO>();
        List<CuentaBancaria> cuentasBancarias = cuentaBancariaRepository.findAllCuentaBancariaByIdProveedor(id);
        if (cuentasBancarias.isEmpty()) {
            throw new NotFoundException("\nWARNING: No existen cuentas bancarias registradas para este proveedor");
        }
        for ( CuentaBancaria cuentaBancaria : cuentasBancarias) {
            cuentasBancariasDTO.add(mappedAccountBanckToDTO(cuentaBancaria));
        }
        return cuentasBancariasDTO;
    }

    public CuentaBancariaDTO createAccountBank(CuentaBancariaDTO cuentaBancariaDTO) {
        CuentaBancaria cuentaBancaria = mappedCuentaBancariaDTO(cuentaBancariaDTO);
        cuentaBancaria.setHabilitado(true);
        System.out.println("Muestra de campo habilitado de cuenta bancaria  ");
        System.out.println(cuentaBancaria.getHabilitado());
        CuentaBancaria cuentaBancariaToSave = cuentaBancariaRepository.save(cuentaBancaria);

        if (cuentaBancariaToSave == null) {
            throw new NotFoundException("\nWARNING: No se puede guardar la cuenta bancaria");
        }
        cuentaBancariaDTO = mappedAccountBanckToDTO(cuentaBancariaToSave);
        return cuentaBancariaDTO;

    }
    public CuentaBancariaDTO updateAccountBank(CuentaBancariaDTO cuentaBancariaDTO) {
        CuentaBancaria cuentaBancaria = cuentaBancariaRepository.findById(cuentaBancariaDTO.getId())
                .orElseThrow(() -> new NotFoundException("WARNING: No existe la cuenta bancaria por este id"));

        cuentaBancaria.setNumero(cuentaBancariaDTO.getNumero());
        cuentaBancaria.setTitular(cuentaBancariaDTO.getTitular());
        cuentaBancaria.setCbu(cuentaBancariaDTO.getCbu());
        cuentaBancaria.setAlias(cuentaBancariaDTO.getAlias());
        cuentaBancaria.setHabilitado(cuentaBancariaDTO.getHabilitado());
        cuentaBancaria = cuentaBancariaRepository.save(cuentaBancaria);
        if (cuentaBancaria == null) {
            throw new NotFoundException("\nWARNING: No se puede actualizar la cuenta bancaria");
        }
        cuentaBancariaDTO = mappedAccountBanckToDTO(cuentaBancaria);
        return cuentaBancariaDTO;
    }
    public CuentaBancariaDTO changeStatusAccountBank(Integer id) {
        CuentaBancaria cuentaBancaria = cuentaBancariaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("WARNING: No existe la cuenta bancaria por este id"));

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

    private CuentaBancaria mappedCuentaBancariaDTO(CuentaBancariaDTO cuentaBancariaDTO) {
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


}
