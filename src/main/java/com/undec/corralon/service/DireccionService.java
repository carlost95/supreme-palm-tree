package com.undec.corralon.service;

import com.undec.corralon.DTO.DireccionDTO;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.*;
import com.undec.corralon.repository.ClienteRepository;
import com.undec.corralon.repository.DireccionRepository;
import com.undec.corralon.repository.DistritoRepository;
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


    public DireccionDTO getDirectionById(Integer idDireccion) {
        DireccionDTO direccionDTO = new DireccionDTO();
        Direccion direccion = direccionRepository.findById(idDireccion).
                orElseThrow(
                        () -> new NotFoundException("WARNING: No se encontro la direccion con id enviado"));

        direccionDTO.setIdDireccion(direccion.getIdDireccion());
        direccionDTO.setIdDistrito(direccion.getDistrito().getIdDistrito());
        direccionDTO.setCalle(direccion.getCalle());
        direccionDTO.setNumeroCalle(direccion.getNumeroCalle());
        direccionDTO.setEntreCalle(direccion.getEntreCalle());
        direccionDTO.setBarrio(direccion.getBarrio());
        direccionDTO.setStatus(direccion.getStatus());
        direccionDTO.setLatitud(direccion.getLatitud());
        direccionDTO.setLongitud(direccion.getLongitud());
        direccionDTO.setDescripcion(direccion.getDescripcion());
        direccionDTO.setIdCliente(direccion.getCliente().getIdCliente());
        return direccionDTO;
    }

    public List<DireccionDTO> getAllDirectionsByIdCliente(Integer idCliente) {
        List<DireccionDTO> direccionDTOList = new ArrayList<>();
        List<Direccion> direccionList = direccionRepository.findAllDireccionesByIdCliente(idCliente);
        if (direccionList.isEmpty()) {
            throw new NotFoundException("\nWARNING: No existen direcciones registradas para este cliente");
        }
        for (Direccion direccion : direccionList) {
            direccionDTOList.add(mappingEntityToDireccionDTO(direccion));
        }
        if (direccionDTOList.isEmpty()) {
            throw new NotFoundException("\nWARNING: No se puede mapear los datos de la direccion");
        }
        return direccionDTOList;
    }
    public List<DireccionDTO>getAllEnabledDirectionsByIdCliente(Integer idCliente) {
        List<DireccionDTO> direccionDTOList = new ArrayList<>();
        List<Direccion> direccionList = direccionRepository.findAllEnabledDireccionesByIdCliente(idCliente);
        if (direccionList.isEmpty()) {
            throw new NotFoundException("\nWARNING: No existen direcciones habilitadas registradas para este cliente");
        }
        for (Direccion direccion : direccionList) {
            direccionDTOList.add(mappingEntityToDireccionDTO(direccion));
        }
        if (direccionDTOList.isEmpty()) {
            throw new NotFoundException("\nWARNING: No se puede mapear los datos de la direccion");
        }
        return direccionDTOList;
    }

    public DireccionDTO saveDirecction(DireccionDTO direccionDTO) {
        Direccion direccion = mappingDireccionDTOToEntity(direccionDTO);
        direccion.setStatus(true);
        direccion = direccionRepository.save(direccion);
        if (direccion == null) {
            throw new NotFoundException("\nWARNING: No se pudo guardar la direccion");
        }
        return mappingEntityToDireccionDTO(direccion);
    }

    public DireccionDTO updateDirecction(DireccionDTO direccionDTO) {
        Direccion direccionUpdated = direccionRepository.findById(direccionDTO.getIdDireccion()).
                orElseThrow(
                        () -> new NotFoundException("WARNING: No se encontro la direccion con id enviado"));
        Cliente cliente = clienteRepository.findById(direccionDTO.getIdCliente()).
                orElseThrow(
                        () -> new NotFoundException("WARNING: No se encontro el cliente con id enviado"));
        Distrito distrito = distritoRepository.findById(direccionDTO.getIdDistrito()).
                orElseThrow(
                        () -> new NotFoundException("WARNING: No se encontro el distrito con id enviado"));
        direccionUpdated.setIdDireccion(direccionDTO.getIdDireccion());
        direccionUpdated.setCliente(cliente);
        direccionUpdated.setDistrito(distrito);
        direccionUpdated.setCalle(direccionDTO.getCalle());
        direccionUpdated.setNumeroCalle(direccionDTO.getNumeroCalle());
        direccionUpdated.setEntreCalle(direccionDTO.getEntreCalle());
        direccionUpdated.setBarrio(direccionDTO.getBarrio());
        direccionUpdated.setStatus(direccionDTO.getStatus());
        direccionUpdated.setLatitud(direccionDTO.getLatitud());
        direccionUpdated.setLongitud(direccionDTO.getLongitud());
        direccionUpdated.setDescripcion(direccionDTO.getDescripcion());
        return mappingEntityToDireccionDTO(direccionRepository.save(direccionUpdated));
    }

    public DireccionDTO changeStatusDirection(Integer idDireccion) {
        Direccion direccion = direccionRepository.findById(idDireccion).
                orElseThrow(
                        () -> new NotFoundException("WARNING: No se encontro la direccion con id enviado"));
        direccion.setStatus(!direccion.getStatus());
        return mappingEntityToDireccionDTO(direccionRepository.save(direccion));
    }

    private DireccionDTO mappingEntityToDireccionDTO(Direccion direccion) {
        DireccionDTO direccionDTO = new DireccionDTO();
        direccionDTO.setIdDireccion(direccion.getIdDireccion());
        direccionDTO.setCalle(direccion.getCalle());
        direccionDTO.setNumeroCalle(direccion.getNumeroCalle());
        direccionDTO.setEntreCalle(direccion.getEntreCalle());
        direccionDTO.setBarrio(direccion.getBarrio());
        direccionDTO.setStatus(direccion.getStatus());
        direccionDTO.setLatitud(direccion.getLatitud());
        direccionDTO.setLongitud(direccion.getLongitud());
        direccionDTO.setDescripcion(direccion.getDescripcion());
        direccionDTO.setIdCliente(direccion.getCliente().getIdCliente());
        direccionDTO.setIdDistrito(direccion.getDistrito().getIdDistrito());
        return direccionDTO;
    }

    private Direccion mappingDireccionDTOToEntity(DireccionDTO direccionDTO) {
        Direccion direccion = new Direccion();
        direccion.setCalle(direccionDTO.getCalle());
        direccion.setNumeroCalle(direccionDTO.getNumeroCalle());
        direccion.setEntreCalle(direccionDTO.getEntreCalle());
        direccion.setBarrio(direccionDTO.getBarrio());
        direccion.setStatus(direccionDTO.getStatus());
        direccion.setLatitud(direccionDTO.getLatitud());
        direccion.setLongitud(direccionDTO.getLongitud());
        direccion.setDescripcion(direccionDTO.getDescripcion());
        direccion.setCliente(clienteRepository.findById(direccionDTO.getIdCliente()).orElseThrow(
                () -> new NotFoundException("WARNING: No se encontro el cliente con id enviado")));
        direccion.setDistrito(distritoRepository.findById(direccionDTO.getIdDistrito()).orElseThrow(
                () -> new NotFoundException("WARNING: No se encontro el distrito con id enviado")));
        return direccion;
    }

}
