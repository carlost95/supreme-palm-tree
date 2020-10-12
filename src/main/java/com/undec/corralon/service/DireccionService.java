package com.undec.corralon.service;

import com.undec.corralon.DTO.ClienteDTO;
import com.undec.corralon.DTO.DireccionDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.DTO.UbicacionDTO;
import com.undec.corralon.excepciones.DireccionErrorToSaveException;
import com.undec.corralon.modelo.Cliente;
import com.undec.corralon.modelo.Direccion;
import com.undec.corralon.modelo.Distrito;
import com.undec.corralon.modelo.Ubicacion;
import com.undec.corralon.repository.ClienteRepository;
import com.undec.corralon.repository.DireccionRepository;
import com.undec.corralon.repository.DistritoRepository;
import com.undec.corralon.serviceData.UbicacionServiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DireccionService {

    @Autowired
    DireccionRepository direccionRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    DistritoRepository distritoRepository;

    @Autowired
    UbicacionServiceData ubicacionServiceData;

    public Response buscarDireccionPorCliente(Integer idCliente) throws DireccionErrorToSaveException {

        Response response = new Response();
        List<Direccion> direcciones = direccionRepository.findDireccionByClienteId(idCliente);
        if( direcciones == null)
            throw new DireccionErrorToSaveException();
//        List<DireccionDTO> direccionDTOS = new ArrayList<>();
//
//        for (Direccion direccion: direcciones) {
//            DireccionDTO direccionDTO = direccion
//
//        }

        response.setMsg("Buscar por cliente");
        response.setCode(200);
        response.setData(direcciones);
        return response;

    }


    public Response guardarDireccion(DireccionDTO direccionDTO) throws Exception {
        Response response = new Response();
        Direccion direccion =  mapDTOtoEntity(direccionDTO);

        Ubicacion ubicacion = this.ubicacionServiceData.save(direccionDTO.getUbicacion());
        direccion.setUbicacion(ubicacion);
        direccion.setEstado(true);
        direccion = direccionRepository.save(direccion);

        if( direccion == null)
            throw new DireccionErrorToSaveException();
        response.setMsg("Creado");
        response.setCode(200);
        response.setData(direccion);
        return response;
    }




    public Direccion mapDTOtoEntity(DireccionDTO direccionDTO){
        Direccion direccion = new Direccion();

        direccion.setCalle(direccionDTO.getCalle());
        direccion.setDescripcion(direccionDTO.getDescripcion());
        direccion.setNumerocalle(direccionDTO.getNumerocalle());

        Cliente cliente = this.clienteRepository.findById(direccionDTO.getClienteId()).get();
        Distrito distrito = this.distritoRepository.findById(direccionDTO.getDistritoId()).get();

        direccion.setCliente(cliente);
        direccion.setDistrito(distrito);

        return direccion;
    }

}
