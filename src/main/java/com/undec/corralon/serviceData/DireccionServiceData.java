package com.undec.corralon.serviceData;

import com.undec.corralon.DTO.DireccionDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.DireccionErrorToSaveException;
import com.undec.corralon.modelo.Direccion;
import com.undec.corralon.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DireccionServiceData {

    @Autowired
    private DireccionService direccionService;

    public Direccion save(DireccionDTO direccionDTO) throws Exception {
        Direccion direccion = new Direccion();
        try {
            Response response = this.direccionService.guardarDireccion(direccionDTO);
            if(response.getCode() == 200){
                direccion = (Direccion) response.getData();
            } else {
                throw new Exception();
            }

        } catch (DireccionErrorToSaveException e) {
            e.printStackTrace();
        }

        return direccion;

    }

}
