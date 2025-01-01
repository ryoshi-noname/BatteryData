package roy.batterydata;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import java.io.*;

public class ChartData {
  private final int rowsToSkip; // Number of rows to skip (e.g., header row)
  private final int[] columnsToRead; // Indices of columns to read (e.g., first and third column)

  public ArrayList<double[]> dataPairs;

//  ChartData()  {
//    this("",0, new int[]{});
////    System.out.println("Printing from ChartData");
//  }

  ChartData(String csvFile, int rowsToSkip, int[] columnsToRead)  {
    System.out.println(csvFile);
    System.out.println(rowsToSkip);
    System.out.println(Arrays.toString(columnsToRead));
    System.out.println("Printing from ChartData");
    this.rowsToSkip = rowsToSkip;
    this.columnsToRead = columnsToRead;
try {
  readData(csvFile);
}
catch (Exception e) {

}
  }


  private void readData(String csvFilePath)  throws Exception{
    File fileToRead = new File(csvFilePath);

    if( !fileToRead.exists() || !fileToRead.isFile()){
      throw new RuntimeException("File Not Found or Bad File Name");
    }

    Scanner scan = new Scanner(fileToRead);

    for (int i=0; i<rowsToSkip; i++){
      scan.nextLine();
    }

    ArrayList<double[]> dataPairs = new ArrayList<>();
    String[] dataRow;

    while (scan.hasNextLine()){
      dataRow = scan.nextLine().split(",");
      double[] xyValues = new double[columnsToRead.length];

      for (int i=0; i<columnsToRead.length; i++){
        xyValues[i]= Double.parseDouble(dataRow[columnsToRead[i]]);
      }

      dataPairs.add(xyValues);

    }
    this.dataPairs = dataPairs;

  }
}
