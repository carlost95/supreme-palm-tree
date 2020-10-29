package com.undec.corralon.service;

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
        List<DireccionDTO> direccionDTOS = new ArrayList<>();

        for (Direccion direccion: direcciones) {
            DireccionDTO direccionDTO = new DireccionDTO();
            direccionDTO.setId(direccion.getId());
            direccionDTO.setDistritoId(direccion.getDistrito().getId());
            direccionDTO.setCalle(direccion.getCalle());
            direccionDTO.setNumerocalle(direccion.getNumerocalle());
            direccionDTO.setEstado(direccion.getEstado());
            direccionDTO.setDescripcion(direccion.getDescripcion());
            direccionDTO.setClienteId(direccion.getCliente().getId());
            direccionDTO.setUbicacion(this.ubicacionDTO(direccion.getUbicacion()));
            direccionDTOS.add(direccionDTO);
        }

        response.setMsg("Buscar por cliente");
        response.setCode(200);
        response.setData(direccionDTOS);
        return response;

    }

    private UbicacionDTO ubicacionDTO(Ubicacion ubicacion) {
        UbicacionDTO ubicacionDTO = new UbicacionDTO();
        ubicacionDTO.setId(ubicacion.getId());
        ubicacionDTO.setEstado(ubicacion.getEstado());
        ubicacionDTO.setLat(ubicacion.getLatitud());
        ubicacionDTO.setLng(ubicacion.getLongitud());
        return ubicacionDTO;
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


    public Response modificarDireccion(DireccionDTO direccionDTO) throws Exception {

        Response response = new Response();
        Direccion direccion =  mapDTOtoEntity(direccionDTO);

        Direccion direccionToUpdate = this.direccionRepository.findById(direccionDTO.getId()).get();

        Ubicacion ubicacion = this.updateUbicacion(direccionToUpdate.getUbicacion(), direccionDTO.getUbicacion());
        direccion.setId(direccionDTO.getId());
        direccion.setUbicacion(ubicacion);

        direccion.setEstado(direccionDTO.getEstado());
        direccion = direccionRepository.save(direccion);

        if( direccion == null)
            throw new DireccionErrorToSaveException();
        response.setMsg("Creado");
        response.setCode(200);
        response.setData(direccion);
        return response;
    }

    private Ubicacion updateUbicacion(Ubicacion ubicacion, UbicacionDTO ubicacionDTO) throws Exception {
        ubicacionDTO.setId(ubicacion.getId());
        ubicacionDTO.setEstado(ubicacion.getEstado());
        ubicacion = this.ubicacionServiceData.save(ubicacionDTO);
        return ubicacion;
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
