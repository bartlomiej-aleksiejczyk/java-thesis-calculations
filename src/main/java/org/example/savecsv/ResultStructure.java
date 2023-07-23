package org.example.savecsv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ResultStructure {
    public static void saveObjectToCsv(String[][] resultColumns) throws IOException {
        int lenHeight = resultColumns[0].length;
        int lenWidth = resultColumns.length;

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("results.csv"));

        for(int i = 0; i<lenHeight; i++){
            for(int j = 0; j<lenWidth; j++){
                if (j!=0){
                    bufferedWriter.write(",");
                }
                try{
                    bufferedWriter.write((resultColumns[j][i]));
                } catch (NullPointerException e){
                }
            }
            if (i!=(lenHeight-1)){
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.close();
    }
}
