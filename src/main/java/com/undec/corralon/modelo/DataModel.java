package com.undec.corralon.modelo;

public class DataModel {
    public final long[][] distanceMatrix;
    public final int vehicleNumber = 1;
    public final int depot = 0;

    public DataModel(long[][]distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

}
