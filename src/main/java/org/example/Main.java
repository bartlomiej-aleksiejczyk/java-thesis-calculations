package org.example;

import org.example.pojos.GaiaDataFrame;
import org.example.pojos.LotssDataFrame;
import org.example.search.NeightborSearchObject;

import java.io.IOException;

import static org.example.opencsv.OpenCsv.gatGaiaDataFrame;
import static org.example.opencsv.OpenCsv.getLotssDataFrame;
import static org.example.savecsv.ResultStructure.saveObjectToCsv;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        GaiaDataFrame gaiaDataFrame = gatGaiaDataFrame();
        LotssDataFrame lotssDataFrame = getLotssDataFrame();
        NeightborSearchObject neightborSearchObject = new NeightborSearchObject(gaiaDataFrame, lotssDataFrame);
        String[][] resultNeighborSearch = neightborSearchObject.searchForNeightbor();
        saveObjectToCsv(resultNeighborSearch);

    }
}