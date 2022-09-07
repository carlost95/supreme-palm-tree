package com.undec.corralon.service;

import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import com.undec.corralon.modelo.Ubicacion;
import com.undec.corralon.repository.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public Ubicacion findUbicationById(Integer id) {
        return ubicacionRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException("WARNING: No existe la ubicacion por este id"));
    }
    public Ubicacion findUbicationByIdDirection(Integer idDireccion) {
        Ubicacion ubicacion = ubicacionRepository.findUbicacionByIdDireccion(idDireccion);
        if (ubicacion == null) {
            throw new NotFoundException("WARNING: No existe la ubicacion por este idDireccion");
        }
        return ubicacion;
    }
    public Ubicacion saveUbicacion(Ubicacion ubicacion) {

        if (!validacionData(ubicacion)) {
            throw new BadRequestException("No se pudo tener una ubicacion con la latitud y longitud nulls");
        }
        ubicacion.setStatus(true);
        Ubicacion ubicacionSaved= this.ubicacionRepository.save(ubicacion);
        if (ubicacionSaved == null) {
            throw new NotFoundException("Error al guardar la ubicacion");
        }
        return ubicacionSaved;
    }

    public Ubicacion updateUbicacion(Ubicacion ubicacion){
        Ubicacion ubicacionDB = this.ubicacionRepository.findById(ubicacion.getIdUbicacion())
                .orElseThrow(
                        () -> new NotFoundException
                                ("Warning: No se encuentro el ID de la ubicacion a actualizar"));
        if (!validacionData(ubicacion)) {
            throw new BadRequestException("No se pudo tener una ubicacion con latitud y longitud nulls");
        }
        ubicacionDB.setIdUbicacion(ubicacion.getIdUbicacion());
        ubicacionDB.setLatitud(ubicacion.getLatitud());
        ubicacionDB.setLongitud(ubicacion.getLongitud());
        ubicacionDB.setStatus(ubicacion.getStatus());
        ubicacionDB = this.ubicacionRepository.save(ubicacionDB);
        if (ubicacionDB == null) {
            throw new NotFoundException("Error al guardar las actualizaciones de la ubicacion");
        }
        return ubicacionDB;
    }
    public Ubicacion changeStatusUbicacion(Integer idUbicacion){
        Ubicacion ubicacionDB = this.ubicacionRepository.findById(idUbicacion)
                .orElseThrow(
                        () -> new NotFoundException
                                ("Warning: No se encuentro el ID de la ubicacion a actualizar"));
        ubicacionDB.setStatus(!ubicacionDB.getStatus());
        ubicacionDB = this.ubicacionRepository.save(ubicacionDB);
        if (ubicacionDB == null) {
            throw new NotFoundException("Error al guardar las actualizaciones de la ubicacion");
        }
        return ubicacionDB;
    }
    private boolean validacionData(Ubicacion ubicacion) {
        if (ubicacion.getLatitud() == null || ubicacion.getLongitud() == null) {
            return false;
        }
        return true;
    }
}
