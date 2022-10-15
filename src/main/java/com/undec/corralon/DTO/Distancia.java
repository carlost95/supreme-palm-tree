package com.undec.corralon.DTO;

import java.util.List;

public class Distancia {

    List<List<Long>> distancia;

    public Distancia() {
    }

    public long [][] getDistancia() {
        long [][] lista = new long[distancia.size()][distancia.size()];
        int count = 0;
        for(List<Long> dist: distancia) {
            lista[count] = dist.stream().mapToLong(l -> l).toArray();
        }
        return lista;
    }

    public void setDistancia(List<List<Long>> distancia) {
        this.distancia = distancia;
    }
}
