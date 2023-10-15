package org.example.savecsv;

import org.example.pojos.ResultPojo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ResultStructure {
    public static void saveObjectToCsv(ResultPojo resultPojo) throws IOException {
        String[][] resultColumns = resultPojo.data;
        String[] headers = resultPojo.headers;
        int lenHeight = resultColumns[0].length;
        int lenWidth = resultColumns.length;

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("results.csv"));

        for(int h = 0; h<lenWidth; h++){
            if (h != 0) {
                bufferedWriter.write(",");
            }
            bufferedWriter.write((headers[h]));
            System.out.println(headers[h]);
        }
        bufferedWriter.newLine();

        for(int i = 0; i<lenHeight; i++){
            for(int j = 0; j<lenWidth; j++){
                if (j!=0){
                    bufferedWriter.write(",");
                }
                try{
                    bufferedWriter.write((resultColumns[j][i]));
                } catch (NullPointerException ignored){
                }
            }
            if (i!=(lenHeight-1)){
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.close();
    }
}
