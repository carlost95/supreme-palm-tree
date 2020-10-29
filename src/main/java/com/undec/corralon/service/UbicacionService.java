package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.DTO.UbicacionDTO;
import com.undec.corralon.modelo.Ubicacion;
import com.undec.corralon.repository.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public Response save(UbicacionDTO ubicacionDTO){
        Response response = new Response();
        Ubicacion ubicacion = new Ubicacion();

        ubicacion.setLatitud(ubicacionDTO.getLat());
        ubicacion.setLongitud(ubicacionDTO.getLng());
        ubicacion.setEstado(true);

        ubicacion = this.ubicacionRepository.save(ubicacion);

        response.setMsg("Guardado Exitosamente");
        response.setCode(200);
        response.setData(ubicacion);

        return response;

    }
}
