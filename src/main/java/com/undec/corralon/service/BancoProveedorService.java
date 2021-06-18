package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.modelo.BancoProveedor;
import com.undec.corralon.repository.BancoProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoProveedorService {
    @Autowired
    BancoProveedorRepository bancoProveedorRepository;

    public Response obtenerBancosProveedores() throws Exception{
        Response response = new Response();
        List<BancoProveedor> bancosProveedores = bancoProveedorRepository.findAll();

        response.setCode(200);
        response.setMsg("Lista de datos de bancos por proveedores");
        response.setData(bancosProveedores);
        return response;
    }
}
