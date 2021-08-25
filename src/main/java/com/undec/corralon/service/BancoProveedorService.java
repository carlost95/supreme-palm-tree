package com.undec.corralon.service;

import com.undec.corralon.DTO.BancoProveedorDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.datosBanco.DatosBancoException;
import com.undec.corralon.excepciones.datosBanco.DatosBancoNotFoundException;
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
public class BancoProveedorService {
    @Autowired
    BancoProveedorRepository bancoProveedorRepository;
    @Autowired
    BancoRepository bancoRepository;
    @Autowired
    ProveedorRepository proveedorRepository;

    public Response obtenerBancosProveedores(){
        Response response = new Response();
        List<BancoProveedor> bancosProveedores = bancoProveedorRepository.findAll();

        response.setCode(200);
        response.setMsg("Lista de datos de bancos por proveedores");
        response.setData(bancosProveedores);
        return response;
    }
    public Response obtenerDatosBancoProveedorById(Integer id) throws DatosBancoException {
        Response response = new Response();

        Optional<BancoProveedor>datoBancoProveedor = bancoProveedorRepository.findById(id);
        if (!datoBancoProveedor.isPresent()) {
            throw new DatosBancoNotFoundException("WARNING: No se encontro el dato del banco requirido");
        }
        response.setCode(200);
        response.setData(datoBancoProveedor);
        response.setMsg("Datos de banco Proveedor por ID");
        return response;
    }
    public Response createBancoProveedor(BancoProveedorDTO bancoProveedorDTO) throws Exception{
        Response response = new Response();
        BancoProveedor bancoProveedor = mappedBancoProveedor(bancoProveedorDTO);
       if (bancoProveedorRepository.save(bancoProveedor)!= null){

       }

        response.setCode(200);
        response.setData(bancoProveedor);
        response.setMsg("datos de banco de proveedor");
        return response;
    }

    private BancoProveedor mappedBancoProveedor(BancoProveedorDTO bancoProveedorDTO) {
        BancoProveedor bancoProveedor= new BancoProveedor();
        Banco banco = bancoRepository.findById(bancoProveedorDTO.getIdBanco()).get();
        Proveedor proveedor = proveedorRepository.findById(bancoProveedorDTO.getIdProveedor()).get();

        bancoProveedor.setTitularCuenta(bancoProveedorDTO.getTitularCuenta());
        bancoProveedor.setNumeroCuenta(bancoProveedorDTO.getNumeroCuenta());
        bancoProveedor.setCbu(bancoProveedorDTO.getCbu());
        bancoProveedor.setBancoByIdBanco(banco);
        bancoProveedor.setProveedorByIdProveedor(proveedor);
        return bancoProveedor;
    }
}
