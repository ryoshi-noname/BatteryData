package roy.batterydata;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

// This class "ChartData" reads in the chart data from the selected folder and files
// that is selected with the FileChooserGUI class. The data that is returned is "dataPairs"


public class ChartData {
    private final int rowsToSkip; // Number of rows to skip (e.g., header row)
    private final int[] columnsToRead; // Indices of columns to read (e.g., first and third column)
    public final ArrayList<Double[]> dataPairs;

    ChartData() {
        this.dataPairs = new ArrayList<>();
        this.rowsToSkip = 0;
        this.columnsToRead = new int[]{};
    }

    ChartData(String fileName, int rowsToSkip, int[] columnsToRead) throws RuntimeException {
        dataPairs = new ArrayList<>();

        this.rowsToSkip = rowsToSkip;
        this.columnsToRead = columnsToRead;
        try {
            readData(new File(fileName));
        } catch (Exception e) {
            throw new RuntimeException("Read Data Error in ChartData.java");
        }
    }

    ChartData(File file, int rowsToSkip, int[] columnsToRead) {
        this.dataPairs = new ArrayList<>();
        this.rowsToSkip = rowsToSkip;
        this.columnsToRead = columnsToRead;

        readData(file);
    }

    private void readData(File f) {

        if (!f.exists() || !f.isFile()) {
            System.out.println("File Not Found or Bad File Name");
            return;
        }

        Scanner scan;

        try {
            scan = new Scanner(f);
        } catch (Exception e) {
            return;
        }

        for (int i = 0; i < rowsToSkip; i++) {
            scan.nextLine();
        }

        String[] dataRow;

        while (scan.hasNextLine()) {
            dataRow = scan.nextLine().split(",");
            Double[] xyValues = new Double[columnsToRead.length];

            for (int i = 0; i < columnsToRead.length; i++) {
                xyValues[i] = Double.parseDouble(dataRow[columnsToRead[i]]);
            }

            dataPairs.add(xyValues);
        }
    }
}
