package org.example.pojos;

import java.util.ArrayList;

public class GaiaDataFrame {
    public GaiaDataFrame(ArrayList<Double> gaiaRa, ArrayList<Double> gaiaPmra, ArrayList<Double> gaiaDec, ArrayList<Double> gaiaPmdec, ArrayList<String> gaiaSourceId, ArrayList<Double> gaiaRaE, ArrayList<Double> gaiaDecE) {
        this.gaiaRa = gaiaRa.stream().mapToDouble(Double::doubleValue).toArray();
        this.gaiaPmra = gaiaPmra.stream().mapToDouble(Double::doubleValue).toArray();
        this.gaiaDec = gaiaDec.stream().mapToDouble(Double::doubleValue).toArray();
        this.gaiaPmdec = gaiaPmdec.stream().mapToDouble(Double::doubleValue).toArray();
        this.gaiaSourceId = gaiaSourceId.stream().toArray(String[]::new);
        this.gaiaRaE = gaiaRaE.stream().mapToDouble(Double::doubleValue).toArray();
        this.gaiaDecE = gaiaDecE.stream().mapToDouble(Double::doubleValue).toArray();
    }

    public double[] gaiaRa;
    public double[] gaiaPmra;
    public double[] gaiaDec;
    public double[] gaiaPmdec;
    public String[] gaiaSourceId;
    public double[] gaiaRaE;
    public double[] gaiaDecE;


}
