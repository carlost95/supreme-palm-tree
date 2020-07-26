package com.undec.corralon.service;

import com.undec.corralon.DTO.DireccionDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.DireccionErrorToSaveException;
import com.undec.corralon.modelo.Cliente;
import com.undec.corralon.modelo.Direccion;
import com.undec.corralon.modelo.Tipodireccion;
import com.undec.corralon.repository.ClienteRepository;
import com.undec.corralon.repository.DireccionRepository;
import com.undec.corralon.repository.DistritoRepository;
import com.undec.corralon.repository.TipodireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    TipodireccionRepository tipodireccionRepository;

    public Response guardarDireccion(DireccionDTO direccionDTO) throws DireccionErrorToSaveException {
        Response response = new Response();
        Direccion direccion =  mapDTOtoEntity(direccionDTO);
        direccion = direccionRepository.save(direccion);
        if( direccion == null)
            throw new DireccionErrorToSaveException();
        response.setMsg("Creado");
        response.setCode(200);
        response.setData(direccion);
        return response;
    }

    public Response buscarDireccionPorCliente(Integer idCliente) throws DireccionErrorToSaveException {

        Response response = new Response();
        List<Direccion> direccion = direccionRepository.findDireccionByClienteByFkclientesid_id(idCliente);
        if( direccion == null)
            throw new DireccionErrorToSaveException();
        response.setMsg("Buscar por clinete");
        response.setCode(200);
        response.setData(direccion);
        return response;

    }


    public Direccion mapDTOtoEntity(DireccionDTO direccionDTO) {
        Direccion direccion = new Direccion();
        direccion.setCalle(direccionDTO.getCalle());
        direccion.setNumerocalle(direccionDTO.getNumerocalle());
        direccion.setBarrio(direccionDTO.getBarrio());
        direccion.setEntrecalles(direccionDTO.getEntrecalles());
        direccion.setDescripcion(direccionDTO.getDescripcion());
        direccion.setUbicacion(direccionDTO.getUbicacion());
        direccion.setHabilitado(1);
        direccion.setFechaalta(LocalDate.now());
        direccion.setFechaactualizacion(LocalDate.now());

        direccion.setClienteByFkclientesid(clienteRepository.findById(direccionDTO.getClienteByFkclientesid()).get());
        direccion.setDistritoByFkdistritosid(distritoRepository.findById(direccionDTO.getDistritoByFkdistritosid()).get());
        direccion.setTipodireccionByFktipodireccionesid(tipodireccionRepository.findById(direccionDTO.getTipodireccionByFktipodireccionesid()).get());
        return direccion;
    }

}
