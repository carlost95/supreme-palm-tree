package com.undec.corralon.DTO;

import java.util.List;

public class Distancia {

    List<List<Double>> distancia;

    public Distancia() {
    }

    public double [][] getDistancia() {
        double [][] lista = new double[distancia.size()][distancia.size()];
        int count = 0;
        for(List<Double> dist: distancia) {
            lista[count] = dist.stream().mapToDouble(l -> l).toArray();
            count++;
        }
        this.printDistance(lista);
        return lista;
    }

    public void setDistancia(List<List<Double>> distancia) {
        this.distancia = distancia;
    }

    public void printDistance(double[][] distance) {
        for (int i = 0; i < distance.length; i++ ){
            for(int j = 0; j < distance[i].length; j++) {
                System.out.print(" " + distance[i][j]+ " ");
            }
            System.out.println("");
        }
    }
}
