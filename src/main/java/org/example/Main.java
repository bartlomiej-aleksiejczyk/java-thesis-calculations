package org.example;

import org.example.pojos.GaiaDataFrame;
import org.example.pojos.LotssDataFrame;
import org.example.pojos.ResultPojo;
import org.example.search.NeighborSearchObject;

import java.io.IOException;

import static org.example.opencsv.OpenCsv.gatGaiaDataFrame;
import static org.example.opencsv.OpenCsv.getLotssDataFrame;
import static org.example.savecsv.ResultStructure.saveObjectToCsv;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        GaiaDataFrame gaiaDataFrame = gatGaiaDataFrame();
        LotssDataFrame lotssDataFrame = getLotssDataFrame();
        NeighborSearchObject neighborSearchObject = new NeighborSearchObject(gaiaDataFrame, lotssDataFrame);
        ResultPojo resultNeighborSearch = neighborSearchObject.searchForNeightbor();
        saveObjectToCsv(resultNeighborSearch);
/*        SkyMap skyMap = new SkyMap(lotssDataFrame.lotssRa, lotssDataFrame.lotssDec);
        System.out.println(skyMap.getDensity());
        SkyMap skyMap1 = new SkyMap(gaiaDataFrame.gaiaRa, gaiaDataFrame.gaiaDec);
        System.out.println(skyMap1.getDensity());*/
    }
}