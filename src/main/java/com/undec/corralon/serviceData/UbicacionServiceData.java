package com.undec.corralon.serviceData;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.DTO.UbicacionDTO;
import com.undec.corralon.modelo.Ubicacion;
import com.undec.corralon.service.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UbicacionServiceData {

    @Autowired
    private UbicacionService ubicacionService;

    public Ubicacion save(UbicacionDTO ubicacionDTO) throws Exception {
        Ubicacion ubicacion;
        try {
            Response response = this.ubicacionService.save(ubicacionDTO);
            if(response.getCode() == 200){
                ubicacion = (Ubicacion) response.getData();
            } else {
                throw new Exception();
            }
        }catch (Exception e) {
            throw new Exception();
        }
        return ubicacion;
    }

}
