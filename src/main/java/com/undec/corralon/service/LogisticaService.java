package com.undec.corralon.service;

import com.undec.corralon.DTO.Distancia;
import com.undec.corralon.DTO.Parada;
import com.undec.corralon.modelo.Remito;
import com.undec.corralon.modelo.RouteManager;
import com.undec.corralon.modelo.Ruta;
import com.undec.corralon.repository.RemitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class LogisticaService {

    @Autowired
    RemitoRepository remitoRepository;

    public List<Parada> obtenerParadas() {
        List<Remito> remitos = this.remitoRepository.findAllByEntregadoEquals(false);
        return remitos.stream().map(remito -> this.remitoAParada(remito)).collect(Collectors.toList());
    }

    private Parada remitoAParada(Remito remito) {
        Parada parada = new Parada();
        parada.setCliente(
                remito.getCliente().getApellido().toUpperCase() + ", " +
                        this.capitalCase(remito.getCliente().getNombre()));
        parada.setBarrio(remito.getDireccion().getBarrio());
        parada.setLatitud(remito.getDireccion().getLatitud());
        parada.setLongitud(remito.getDireccion().getLongitud());
        parada.setDireccion(remito.getDireccion().getCalle() + " - " + remito.getDireccion().getNumeroCalle());
        return parada;
    }

    private String capitalCase(String word) {
        String cap = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
        return cap;
    }

    public Ruta obtenerRuta(Distancia distancia) throws Exception {
        return new RouteManager(distancia.getDistancia()).getRoute();

    }
}
