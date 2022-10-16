package com.undec.corralon.modelo;

import java.util.ArrayList;
import java.util.List;

public class Ruta {

    private List<Integer> parada;
    private Long distancia;

    public Ruta() {
        this.distancia = Long.valueOf(0);
        this.parada = new ArrayList<>();
    }

    public List<Integer> getParada() {
        return parada;
    }

    public void setParada(List<Integer> parada) {
        this.parada = parada;
    }

    public Long getDistancia() {
        return distancia;
    }

    public void setDistancia(Long distancia) {
        this.distancia = distancia;
    }
}
