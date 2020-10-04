package com.undec.corralon.service;

import com.undec.corralon.DTO.Response;
import com.undec.corralon.modelo.Ajustes;
import com.undec.corralon.repository.AjusteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AjusteService {
    @Autowired
    AjusteRepository ajusteRepository;

    public Response obtenerTodosAjustes(){
        Response response = new Response();
        List<Ajustes> ajustes = ajusteRepository.findAll();

        response.setCode(200);
        response.setMsg("Todos los ajustes: ");
        response.setData(ajustes);
        return response;
    }
}
