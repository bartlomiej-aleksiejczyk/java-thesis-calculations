package org.example.pojos;

import java.util.ArrayList;

public class LotssDataFrame {
    public LotssDataFrame(ArrayList<Double> lotssRa, ArrayList<Double> lotssDec, ArrayList<Double> lotssSourceDate, ArrayList<String> lotssSourceId, ArrayList<Double> lotssRaE, ArrayList<Double> lotssDecE) {
        this.lotssRa = lotssRa.stream().mapToDouble(Double::doubleValue).toArray();
        this.lotssDec = lotssDec.stream().mapToDouble(Double::doubleValue).toArray();
        this.lotssSourceDate = lotssSourceDate.stream().mapToDouble(Double::doubleValue).toArray();
        this.lotssSourceId = lotssSourceId.stream().toArray(String[]::new);
        this.lotssRaE = lotssRaE.stream().mapToDouble(Double::doubleValue).toArray();
        this.lotssDecE = lotssDecE.stream().mapToDouble(Double::doubleValue).toArray();

    }

    public double[] lotssRa;
    public double[] lotssDec;
    public double[] lotssSourceDate;
    public String[] lotssSourceId;
    public double[] lotssRaE;
    public double[] lotssDecE;


}
