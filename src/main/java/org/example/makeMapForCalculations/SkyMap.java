package org.example.makeMapForCalculations;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SkyMap {
    //x[row_index][column_index]
    int[][] mapFallOne = new int[90][360];
    int[][] mapFallTwo = new int[90][360];
    int[][] mapSpring = new int[90][360];
    int[][] resultTestMap = new int[90][360];
    double[] ra;
    double[] dec;

    public static Image getImageFromArray(int[] pixels, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = (WritableRaster) image.getData();
        raster.setPixels(0, 0, width, height, pixels);
        return image;
    }

    public String fileNamingUlity(String path) {
        File file = new File(path);
        if (file.exists() && !file.isDirectory()) {
            path = path.substring(0, path.lastIndexOf('.')) + "1" + ".png";
            return fileNamingUlity(path);
        }
        return path;
    }

    public void saveMapToImage(int[][] array) {
        String path = fileNamingUlity("skyMap.png");
        BufferedImage image = new BufferedImage(360, 90, BufferedImage.TYPE_INT_RGB);
        for (int k = 0; k < array[0].length; k++) {
            for (int r = 0; r < array.length; r++) {
                if (array[r][k] > 0) {
                    image.setRGB(k, r, 233);

                }
            }
        }

        File ImageFile = new File(path);
        try {
            ImageIO.write(image, "png", ImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SkyMap(double[] ra, double[] dec) {

        this.ra = ra;
        this.dec = dec;
        int size = this.ra.length;
        for (int i = 0; i < size; i++) {
            int currentRa = (int) ra[i] % 360;
            int currentDec = (int) dec[i] % 90;
            if (currentRa < 60) {
                mapFallOne[currentDec][currentRa]++;
            } else if (300 > currentRa && currentRa > 60) {
                mapSpring[currentDec][currentRa]++;
            } else {
                mapFallTwo[currentDec][currentRa]++;
            }
        }
/*        saveMapToImage(mapFallOne);
        saveMapToImage(mapFallTwo);
        saveMapToImage(mapSpring);*/
    }


    private ArrayList<int[]> findRectangles(int[][] area) {
        ArrayList<int[]> rectangleList = new ArrayList<int[]>();
        for (int r = 0; r < 90; r++) {
            for (int k = 0; k < 360; k++) {
                if (area[r][k] > 0) {
                    rectangleList.add(pointsFinder(area, r, k));
                }
            }
        }
        return rectangleList;
    }

    //x[row_index][column_index]
    private int[] pointsFinder(int[][] area, int pointR, int pointK) {
        int newR = 0;
        int newK = 0;
        while ((area[pointR + 1 + newR][pointK] > 0) && (pointR + 1 + newR < area.length)) {
            newR++;
        }
        while ((pointK + 1 + newK < area[0].length) && (area[pointR][pointK + 1 + newK] > 0)) {

            newK++;
        }
        ArrayList<int[]> smallResultsList = new ArrayList<int[]>();
        smallResultsList.add(balancedSearch(pointR, pointK, newR, newK, area));
        smallResultsList.add(bottomSearch(pointR, pointK, newR, newK, area));
        smallResultsList.add(rightSearch(pointR, pointK, newR, newK, area));
        return findBiggestRectangle(smallResultsList);
    }
    private int[] balancedSearch(int pointR, int pointK, int newR, int newK, int[][] area){
        int rowCheck = 0;
        int columnCheck = 0;
        while ((columnCheck < newK) && (rowCheck < newR)) {
            while ((rowCheck < newR)&&(area[pointR + 1 + rowCheck][pointK + newK] > 0)  ) {
                rowCheck++;
            }
            while ( (columnCheck < newK)&&(area[pointR + newR][pointK + 1 + columnCheck] > 0)) {
                columnCheck++;
            }
            if( (columnCheck < newK)&&(rowCheck < newR)) {
                newR--;
                newK--;

                rowCheck = 0;
                columnCheck = 0;
            } else if (rowCheck < newR) {
                newK--;
                rowCheck = 0;
                columnCheck = 0;
            }else if (columnCheck < newK) {
                newR--;

                rowCheck = 0;
                columnCheck = 0;
            }
        }
        return new int[]{pointR, pointK, pointR + newR, pointK + newK};
    }
    private int[] bottomSearch(int pointR, int pointK, int newR, int newK, int[][] area){
        int rowCheck = 0;
        int columnCheck = 0;
        while ((columnCheck < newK) && (rowCheck < newR)) {
            while ((rowCheck < newR)&&(area[pointR + 1 + rowCheck][pointK + newK] > 0)  ) {
                rowCheck++;
            }
            while ( (columnCheck < newK)&&(area[pointR + newR][pointK + 1 + columnCheck] > 0)) {
                columnCheck++;
            }
            if (rowCheck < newK) {
                newR--;

                rowCheck = 0;
                columnCheck = 0;
            }else if (columnCheck < newR) {
                newK--;

                rowCheck = 0;
                columnCheck = 0;
            }
        }
        return new int[]{pointR, pointK, pointR + newR, pointK + newK};

    }
    private int[] rightSearch(int pointR, int pointK, int newR, int newK, int[][] area){
        int rowCheck = 0;
        int columnCheck = 0;
        while ((columnCheck < newK) && (rowCheck < newR)) {
            while ((rowCheck < newR)&&(area[pointR + 1 + rowCheck][pointK + newK] > 0)  ) {
                rowCheck++;
            }
            while ( (columnCheck < newK)&&(area[pointR + newR][pointK + 1 + columnCheck] > 0)) {
                columnCheck++;
            }
            if (rowCheck < newR) {
                newK--;
                rowCheck = 0;
                columnCheck = 0;
            }else if (columnCheck < newK) {
                newR--;

                rowCheck = 0;
                columnCheck = 0;
            }
        }
        return new int[]{pointR, pointK, pointR + newR, pointK + newK};
    }


        private int[] findBiggestRectangle (ArrayList < int[]>rectangleList){
            int[] biggestRectangle = rectangleList.get(0);
            int biggestRectangleSize = (Math.abs(biggestRectangle[0] - biggestRectangle[2])
                    * Math.abs(biggestRectangle[1] - biggestRectangle[3]));
            for (int[] rectangle : rectangleList) {
                int rectangleSize = (Math.abs(rectangle[0] - rectangle[2]) * Math.abs(rectangle[1] - rectangle[3]));
                if (rectangleSize > biggestRectangleSize) {
                    biggestRectangle = rectangle;
                    biggestRectangleSize = rectangleSize;
                }
            }
            System.out.println("___");

            System.out.println(biggestRectangle[0]);
            System.out.println(biggestRectangle[1]);

            return biggestRectangle;
        }

    private int countObjects(int[] rectangle, int[][] area){
        int objectCounter = 0;
        for (int r = rectangle[0]; r <= rectangle[2]; r++) {
            for (int k = rectangle[1]; k <= rectangle[3]; k++) {
                objectCounter+= area[r][k];
                resultTestMap[r][k]+=1;
            }
        }
        //saveMapToImage(resultTestMap);
        return objectCounter;
    }
    public static double calculateArea(int[] rectangle){
        System.out.println("RA: "+rectangle[0]);
        System.out.println("DEC2: "+rectangle[1]);
        System.out.println("RA2: "+rectangle[2]);
        System.out.println("DEC: "+rectangle[3]);
        return (Math.toRadians(rectangle[2])
                -Math.toRadians(rectangle[0]))
                *(Math.sin(Math.toRadians(rectangle[1]))
                -Math.sin(Math.toRadians(rectangle[3])))/3283;
    }
    public double getDensity(){
        int[] rectangleFallOne = findBiggestRectangle(findRectangles(mapFallOne));
        int[] rectangleFallTwo = findBiggestRectangle(findRectangles(mapFallTwo));
        int[] rectangleSpring = findBiggestRectangle(findRectangles(mapSpring));
        double objectAmount = countObjects(rectangleFallOne, mapFallOne)
                + countObjects(rectangleFallTwo, mapFallTwo)
                + countObjects(rectangleSpring, mapSpring);
        System.out.println("calosc"+(objectAmount));
        double objectArea = calculateArea(rectangleFallOne)
                + calculateArea(rectangleFallTwo)
                + calculateArea(rectangleSpring);
        System.out.println(objectAmount/objectArea);
        return objectAmount/objectArea;
    }
}
