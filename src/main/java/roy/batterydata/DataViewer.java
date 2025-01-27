package roy.batterydata;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// This class generates the X-Y chart for viewing the data by using the javaFX API.


public class DataViewer {

    // regex expression to capture the data file name to use as labels for each set of data
    // regex is filtering out the windows path name and capturing only the filename without ".csv" extension
    private static final Pattern FILE_NAME_PATTERN = Pattern.compile(
        "(.*?)([^\\\\.]+)(\\.[^\\\\.]*)?");

    public static Scene generateScene(String chartName, List<File> files) {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Time (seconds)");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Battery Voltage (volts)");
        // Set custom bounds for the Y axes
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(11.75); // Set lower limit of Y-axis
        yAxis.setUpperBound(13.25); // Set upper limit of Y-axis
//        yAxis.setUpperBound(13.75); // Set upper limit of Y-axis -- useful setting for initial battery state
        yAxis.setTickUnit(0.1);
//        xAxis.setAutoRanging(false);  // xAxis code useful for zooming in on initial battery state
//        xAxis.setLowerBound(0);
//        xAxis.setUpperBound(10);
//        xAxis.setTickUnit(1);

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(chartName);

        for(int j = 0; j < files.size(); j++){
            File cur = files.get(j);

            ChartData chartData = new ChartData(files.get(j), MainApp.ROWS_TO_SKIP, MainApp.COLUMNS_TO_READ);
            Matcher m = FILE_NAME_PATTERN.matcher(cur.getAbsolutePath());
            if(!m.matches()) {
                throw new RuntimeException("Bad file name");
            }

            String dataName = m.group(2);

            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(dataName);

//            int x = 0;

            for (Double[] point : chartData.dataPairs) {
                series.getData().add(new XYChart.Data<>(point[0], point[1]));

            }

            lineChart.getData().add(series);

        }

        Scene scene = new Scene(lineChart, 1080, 640);

        // Code below lets the javaFX scene use the "styles.css" file, located in resources of the project
        // to modify elements (called nodes in javaFX) in the scene

            URL cssUrl = DataViewer.class.getResource("styles.css");
        if (cssUrl != null) {
            System.out.println("using CSS style sheet");
            scene.getStylesheets().add(cssUrl.toExternalForm());
        } else {
            System.err.println("Stylesheet not found!");
        }

        return scene;

    }
}



