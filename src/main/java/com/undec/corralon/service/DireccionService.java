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
import com.undec.corralon.repository.UbicacionRepository;
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

    @Autowired
    UbicacionRepository ubicacionRepository;

    protected final Log logger = LogFactory.getLog(this.getClass());

    public Response buscarDireccionPorCliente(Integer idCliente) throws DireccionErrorToSaveException {

        Response response = new Response();
        List<Direccion> direcciones = direccionRepository.findDireccionByClienteByIdCliente(idCliente);
        if( direcciones == null)
            throw new DireccionErrorToSaveException();
        List<DireccionDTO> direccionDTOS = new ArrayList<>();

        for (Direccion direccion: direcciones) {
            DireccionDTO direccionDTO = new DireccionDTO();
            direccionDTO.setIdDireccion(direccion.getIdDireccion());
            direccionDTO.setIdDistrito(direccion.getDistritoByIdDistrito().getIdDistrito());
            direccionDTO.setCalle(direccion.getCalle());
            direccionDTO.setNumerocalle(direccion.getNumeroCalle());
            direccionDTO.setNumerocalle(direccion.getNumeroCalle());
            direccionDTO.setEntreCalle(direccion.getEntreCalle());
            direccionDTO.setBarrio(direccion.getBarrio());
            direccionDTO.setHabilitado(direccion.getHabilitado());
            direccionDTO.setDescripcion(direccion.getDescripcion());
            direccionDTO.setIdCliente(direccion.getClienteByIdCliente().getIdCliente());
            direccionDTO.setIdUbicacion(direccion.getUbicacionByIdUbicacion().getIdUbicacion());
            direccionDTOS.add(direccionDTO);
        }

        response.setMsg("Buscar por cliente");
        response.setCode(200);
        response.setData(direccionDTOS);
        return response;

    }

    private UbicacionDTO ubicacionDTO(Ubicacion ubicacion) {
        UbicacionDTO ubicacionDTO = new UbicacionDTO();
        ubicacionDTO.setIdUbicacion(ubicacion.getIdUbicacion());
        ubicacionDTO.setHabilitado(ubicacion.getHabilitado());
        ubicacionDTO.setLatitud(ubicacion.getLatitud());
        ubicacionDTO.setLongitud(ubicacion.getLongitud());
        return ubicacionDTO;
    }

    public Response guardarDireccion(DireccionDTO direccionDTO) throws Exception {
        Response response = new Response();
        Direccion direccion =  mapDTOtoEntity(direccionDTO);
        Ubicacion ubicacionDB =this.ubicacionRepository.findById(direccionDTO.getIdUbicacion()).get();
        UbicacionDTO ubicacionDTO = this.ubicacionDTO(ubicacionDB);
        Ubicacion ubicacion =  this.ubicacionServiceData.save(ubicacionDTO);

        direccion.setUbicacionByIdUbicacion(ubicacion);
        direccion.setHabilitado(true);
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

        Direccion direccionToUpdate = this.direccionRepository.findById(direccionDTO.getIdDireccion()).get();

        Ubicacion ubicacionUpdate = this.ubicacionRepository.findById(direccionDTO.getIdUbicacion()).get();
        UbicacionDTO ubicacionDTO = this.ubicacionDTO(ubicacionUpdate);
        Ubicacion ubicacion = this.updateUbicacion(direccionToUpdate.getUbicacionByIdUbicacion(), ubicacionDTO);
        direccion.setUbicacionByIdUbicacion(ubicacion);

        direccion.setHabilitado(direccionDTO.getHabilitado());
        direccion = direccionRepository.save(direccion);

        if( direccion == null)
            throw new DireccionErrorToSaveException();
        response.setMsg("Modificado");
        response.setCode(200);
        response.setData(direccion);
        return response;
    }

    private Ubicacion updateUbicacion(Ubicacion ubicacion, UbicacionDTO ubicacionDTO) throws Exception {
        ubicacionDTO.setIdUbicacion(ubicacion.getIdUbicacion());
        ubicacionDTO.setLatitud(ubicacion.getLatitud());
        ubicacionDTO.setLongitud(ubicacion.getLongitud());
        ubicacionDTO.setHabilitado(ubicacion.getHabilitado());
        ubicacion = this.ubicacionServiceData.save(ubicacionDTO);
        return ubicacion;
    }

    public Direccion mapDTOtoEntity(DireccionDTO direccionDTO){
        Direccion direccion = new Direccion();

        direccion.setCalle(direccionDTO.getCalle());
        direccion.setDescripcion(direccionDTO.getDescripcion());
        direccion.setNumeroCalle(direccionDTO.getNumerocalle());
        direccion.setEntreCalle(direccionDTO.getEntreCalle());
        direccion.setBarrio(direccionDTO.getBarrio());
        direccion.setHabilitado(direccionDTO.getHabilitado());

        Cliente cliente = this.clienteRepository.findById(direccionDTO.getIdCliente()).get();
        Distrito distrito = this.distritoRepository.findById(direccionDTO.getIdDistrito()).get();
        Ubicacion ubicacion = this.ubicacionRepository.findById(direccionDTO.getIdUbicacion()).get();

        direccion.setClienteByIdCliente(cliente);
        direccion.setDistritoByIdDistrito(distrito);
        direccion.setUbicacionByIdUbicacion(ubicacion);

        return direccion;
    }

    public Response changeStatus(DireccionDTO direccionDTO) throws Exception {
        Response response = new Response();
        Direccion updateDireccion = this.direccionRepository.findById(direccionDTO.getIdDireccion()).get();
        updateDireccion.setHabilitado(!updateDireccion.getHabilitado());
        updateDireccion = this.direccionRepository.save(updateDireccion);
        direccionDTO.setHabilitado(updateDireccion.getHabilitado());
        response.setCode(200);
        response.setMsg("Changed Status");
        response.setData(direccionDTO);
        logger.info("DireccionService: Change status");
        return response;
    }
}
