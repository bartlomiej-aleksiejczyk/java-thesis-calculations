package org.example.opencsv;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.example.pojos.GaiaDataFrame;
import org.example.pojos.LotssDataFrame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class OpenCsv {
    public static GaiaDataFrame gatGaiaDataFrame () throws IOException {
        ArrayList<String> gaiaSourceId = new ArrayList<String>();
        ArrayList<Double> gaiaRa = new ArrayList<Double>();
        ArrayList<Double> gaiaDec = new ArrayList<Double>();
        ArrayList<Double> gaiaPmra = new ArrayList<Double>();
        ArrayList<Double> gaiaPmdec = new ArrayList<Double>();
        ArrayList<Double> gaiaRaE = new ArrayList<Double>();
        ArrayList<Double> gaiaDecE = new ArrayList<Double>();

        Reader input2 = new FileReader("inputdata2.csv");
        Iterable<CSVRecord> records2 = CSVFormat.EXCEL.withHeader().parse(input2);
        for (CSVRecord record2 : records2) {
            gaiaSourceId.add((record2.get("source_id")));
            gaiaRa.add(Double.valueOf(record2.get("ra")));
            gaiaDec.add(Double.valueOf(record2.get("dec")));
            gaiaPmra.add(Double.valueOf(record2.get("pmra")));
            gaiaPmdec.add(Double.valueOf(record2.get("pmdec")));
            gaiaRaE.add(Double.valueOf(record2.get("ra_error")));
            gaiaDecE.add(Double.valueOf(record2.get("dec_error")));
        }

        return new GaiaDataFrame(gaiaRa, gaiaPmra,gaiaDec,gaiaPmdec,gaiaSourceId,gaiaRaE,gaiaDecE);

    }
    public static LotssDataFrame getLotssDataFrame () throws IOException{
        ArrayList<String> lotssSourceId = new ArrayList<String>();
        ArrayList<Double> lotssRa = new ArrayList<Double>();
        ArrayList<Double> lotssDec = new ArrayList<Double>();
        ArrayList<Double> lotssSourceDate = new ArrayList<Double>();
        ArrayList<Double> lotssRaE = new ArrayList<Double>();
        ArrayList<Double> lotssDecE = new ArrayList<Double>();

        Reader input1 = new FileReader("inputdata1.csv");
        Iterable<CSVRecord> records1 = CSVFormat.EXCEL.withHeader().parse(input1);
        for (CSVRecord record1 : records1) {

            lotssSourceId.add((record1.get("source_name")));
            lotssRa.add(Double.valueOf(record1.get("ra")));
            lotssDec.add(Double.valueOf(record1.get("dec")));
            lotssSourceDate.add(Double.valueOf(record1.get("source_date")));
            lotssRaE.add(Double.valueOf(record1.get("e_ra")));
            lotssDecE.add(Double.valueOf(record1.get("e_dec")));
        }
        input1.close();
        return new LotssDataFrame(lotssRa,lotssDec,lotssSourceDate,lotssSourceId, lotssRaE, lotssDecE);
    }
}
