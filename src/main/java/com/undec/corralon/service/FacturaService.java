package com.undec.corralon.service;

import com.undec.corralon.DTO.FacturaDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.modelo.Cliente;
import com.undec.corralon.modelo.Factura;
import com.undec.corralon.repository.ClienteRepository;
import com.undec.corralon.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public Response save(FacturaDTO facturaDTO) {
        Response response = new Response();
        Factura factura = new Factura();
        factura.setId(facturaDTO.getId());
        factura.setMonto(facturaDTO.getMonto());
        factura.setDescuento(facturaDTO.getMonto());
        factura.setTotal(facturaDTO.getTotal());
        factura.setFecha(new Date());
        Cliente cliente = this.clienteRepository.findById(facturaDTO.getCliente()).get();
        factura.setCliente(cliente);
        factura = this.facturaRepository.save(factura);

        response.setCode(200);
        response.setData(factura);
        response.setMsg("Factura guardada correctamente !!!");
        return response;
    }
}
