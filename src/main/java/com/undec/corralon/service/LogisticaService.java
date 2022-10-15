package com.undec.corralon.service;

import com.undec.corralon.DTO.Distancia;
import com.undec.corralon.modelo.RouteManager;
import com.undec.corralon.modelo.Ruta;
import org.springframework.stereotype.Service;

@Service
public class LogisticaService {

    public Ruta obtenerRuta(Distancia distancia) throws Exception {
        return new RouteManager(distancia.getDistancia()).getRoute();

    }
}
