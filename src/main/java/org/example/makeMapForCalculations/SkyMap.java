package org.example.makeMapForCalculations;

import org.example.pojos.LotssDataFrame;

import java.util.ArrayList;
import java.util.Arrays;

public class SkyMap {
    //x[row_index][column_index]
    int[][] mapFallOne = new int[75][360];
    int[][] mapFallTwo = new int[75][360];
    int[][] mapSpring = new int[75][360];
    double[] ra;
    double[] dec;

    public SkyMap(double[] ra, double[] dec) {

        this.ra = ra;
        this.dec = dec;
        int size = this.ra.length;
        for (int i = 0; i < size; i++) {
            int currentRa = (int) ra[i]%360;
            int currentDec = (int) dec[i]%75;
            if (currentRa < 60) {
                mapFallOne[currentDec][currentRa]++;
            } else if (300 > currentRa && currentRa > 60) {
                mapSpring[currentDec][currentRa]++;
            } else {
                mapFallTwo[currentDec][currentRa]++;
            }
        }
    }

    private ArrayList<int[]> findRectangles(int[][] area){
        ArrayList<int[]> rectangleList = new ArrayList<int[]>();
        for (int r = 0; r < 75; r++) {
            for (int k = 0; k < 360; k++) {
                if (area[r][k] >0){
                    rectangleList.add(pointsFinder(area,r,k));
                }
            }
        }
        return rectangleList;
    }
    //x[row_index][column_index]
    private int[] pointsFinder(int[][] area, int pointR, int pointK) {
        int newR =0;
        int newK =0;
        while ((area[pointR+1+newR][pointK]>0)&&(pointR+1+newR<area.length)){
            newR++;
        }
        while ((pointK+1+newK<area[0].length)&&(area[pointR][pointK+1+newK]>0)){

            newK++;
        }
        //Check rows
        int rowCheck = 0;
        int columnCheck = 0;
        while ((area[pointR+1+rowCheck][pointK+newK]>0)&&(rowCheck<newR)){
            rowCheck++;
        }
        while ((pointK+1+columnCheck<area[0].length)&&(area[pointR+newR][pointK+1+columnCheck]>0)&&(columnCheck<newK)){
            columnCheck++;
        }
        if((rowCheck!=newR)&&(columnCheck!=newK)){
            return (rowCheck > columnCheck) ? new int[]{pointR, pointK, pointR+rowCheck, pointK+newK}
                    : new int[]{pointR, pointK, pointR+newR, pointK+columnCheck};
        }else if(rowCheck!=newR){
            return new int[]{pointR, pointK, pointR+rowCheck, pointK+newK};
        }else if(columnCheck!=newK){
            return new int[]{pointR, pointK, pointR+rowCheck, pointK+newK};
        }
        return new int[]{pointR, pointK, pointR+newR, pointK+newK};
    }
    private int[] findBiggestRectangle (ArrayList<int[]> rectangleList){
        int[] biggestRectangle = rectangleList.get(0);
        int biggestRectangleSize =(Math.abs(biggestRectangle[0]-biggestRectangle[2])
                * Math.abs(biggestRectangle[1]-biggestRectangle[3]));
        for (int[] rectangle:rectangleList){
            int rectangleSize = (Math.abs(rectangle[0]-rectangle[2])*Math.abs(rectangle[1]-rectangle[3]));
            if (rectangleSize > biggestRectangleSize){
                biggestRectangle = rectangle;
                biggestRectangleSize = rectangleSize;
            }
        }
        return biggestRectangle;
    }
    private int countObjects(int[] rectangle, int[][] area){
        int objectCounter = 0;
        for (int r = rectangle[0]; r <= rectangle[2]; r++) {
            for (int k = rectangle[1]; k <= rectangle[3]; k++) {
                objectCounter+= area[r][k];
            }
        }
        return objectCounter;
    }
    private double calculateArea(int[] rectangle){
        //Czy należy z tego wyciągać wartość bezwzględną
        return (rectangle[2]-rectangle[0])*(Math.sin(Math.toRadians(rectangle[1]))
                -Math.sin(Math.toRadians(rectangle[3])));
    }
    public double getDensity(){
        int[] rectangleFallOne = findBiggestRectangle(findRectangles(mapFallOne));
        int[] rectangleFallTwo = findBiggestRectangle(findRectangles(mapFallTwo));
        int[] rectangleSpring = findBiggestRectangle(findRectangles(mapSpring));
        System.out.println(Arrays.toString(rectangleSpring));

        double objectAmount = countObjects(rectangleFallOne, mapFallOne)
                + countObjects(rectangleFallTwo, mapFallTwo)
                + countObjects(rectangleSpring, mapSpring);
        double objectArea = calculateArea(rectangleFallOne)
                + calculateArea(rectangleFallTwo)
                + calculateArea(rectangleSpring);
        return objectAmount/objectArea;
    }
}
