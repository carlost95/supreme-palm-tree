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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    protected final Log logger = LogFactory.getLog(this.getClass());

    public Response buscarDireccionPorCliente(Integer idCliente) throws DireccionErrorToSaveException {

        Response response = new Response();
        List<Direccion> direcciones = direccionRepository.findDireccionByClienteByIdCliente(idCliente);
        if( direcciones == null)
            throw new DireccionErrorToSaveException();
        List<DireccionDTO> direccionDTOS = new ArrayList<>();

        for (Direccion direccion: direcciones) {
            DireccionDTO direccionDTO = new DireccionDTO();
            direccionDTO.setId(direccion.getIdDireccion());
            direccionDTO.setDistritoId(direccion.getDistritoByIdDistrito().getIdDistrito());
            direccionDTO.setCalle(direccion.getCalle());
            direccionDTO.setNumerocalle(direccion.getNumeroCalle());
            direccionDTO.setEstado(direccion.getHabilitado());
            direccionDTO.setDescripcion(direccion.getDescripcion());
            direccionDTO.setClienteId(direccion.getClienteByIdCliente().getIdCliente());
            direccionDTO.setUbicacion(this.ubicacionDTO(direccion.getUbicacionByIdUbicacion()));
            direccionDTOS.add(direccionDTO);
        }

        response.setMsg("Buscar por cliente");
        response.setCode(200);
        response.setData(direccionDTOS);
        return response;

    }

    private UbicacionDTO ubicacionDTO(Ubicacion ubicacion) {
        UbicacionDTO ubicacionDTO = new UbicacionDTO();
        ubicacionDTO.setId(ubicacion.getIdUbicacion());
        ubicacionDTO.setEstado(ubicacion.getHabilitado());
        ubicacionDTO.setLat(ubicacion.getLatitud());
        ubicacionDTO.setLng(ubicacion.getLongitud());
        return ubicacionDTO;
    }

    public Response guardarDireccion(DireccionDTO direccionDTO) throws Exception {
        Response response = new Response();
        Direccion direccion =  mapDTOtoEntity(direccionDTO);

        Ubicacion ubicacion = this.ubicacionServiceData.save(direccionDTO.getUbicacion());
        direccion.setUbicacionByIdUbicacion(ubicacion);
        direccion.setHabilitado(
                true);
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

        Ubicacion ubicacion = this.updateUbicacion(direccionToUpdate.getUbicacionByIdUbicacion(), direccionDTO.getUbicacion());
        direccion.setUbicacionByIdUbicacion(ubicacion);

        direccion.setHabilitado(direccionDTO.getEstado());
        direccion = direccionRepository.save(direccion);

        if( direccion == null)
            throw new DireccionErrorToSaveException();
        response.setMsg("Creado");
        response.setCode(200);
        response.setData(direccion);
        return response;
    }

    private Ubicacion updateUbicacion(Ubicacion ubicacion, UbicacionDTO ubicacionDTO) throws Exception {
        ubicacionDTO.setId(ubicacion.getIdUbicacion());
        ubicacionDTO.setEstado(ubicacion.getHabilitado());
        ubicacion = this.ubicacionServiceData.save(ubicacionDTO);
        return ubicacion;
    }

    public Direccion mapDTOtoEntity(DireccionDTO direccionDTO){
        Direccion direccion = new Direccion();

        direccion.setCalle(direccionDTO.getCalle());
        direccion.setDescripcion(direccionDTO.getDescripcion());
        direccion.setNumeroCalle(direccionDTO.getNumerocalle());

        Cliente cliente = this.clienteRepository.findById(direccionDTO.getClienteId()).get();
        Distrito distrito = this.distritoRepository.findById(direccionDTO.getDistritoId()).get();

        direccion.setClienteByIdCliente(cliente);
        direccion.setDistritoByIdDistrito(distrito);

        return direccion;
    }

    public Response changeStatus(DireccionDTO direccionDTO) throws Exception {
        Response response = new Response();
        Direccion toUpdate = this.direccionRepository.findById(direccionDTO.getId()).get();
        toUpdate.setHabilitado(!toUpdate.getHabilitado());
        toUpdate = this.direccionRepository.save(toUpdate);
        direccionDTO.setEstado(toUpdate.getHabilitado());
        response.setCode(200);
        response.setMsg("Changed Status");
        response.setData(direccionDTO);
        logger.info("DireccionService: Change status");
        return response;
    }
}
