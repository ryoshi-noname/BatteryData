package roy.batterydata;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

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
    private static final Pattern FILE_NAME_PATTERN = Pattern.compile(".*\\\\(.*)\\..*");

    public static Scene generateScene(String chartName, List<File> files) {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Time (seconds)");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Voltage");
        // Set custom bounds for the Y axes
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(9); // Set lower limit of Y-axis
        yAxis.setUpperBound(13.5); // Set upper limit of Y-axis


        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(chartName);

        for(int j = 0; j < files.size(); j++){
            File cur = files.get(j);

            ChartData chartData = new ChartData(files.get(j), MainApp.ROWS_TO_SKIP, MainApp.COLUMNS_TO_READ);
            Matcher m = FILE_NAME_PATTERN.matcher(cur.getAbsolutePath());
            if(!m.matches()) {
                throw new RuntimeException("Bad file name");
            }

            String dataName = m.group(1);

            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(dataName);

            for (Double[] point : chartData.dataPairs) {
                series.getData().add(new XYChart.Data<>(point[0], point[1]));

            }

            lineChart.getData().add(series);

//            Node line = series.getNode();
//            String color = line.getStyle().replaceAll(".*-fx-stroke:\\s*([^;]+);.*", "$1");
//            Set<Node> legendItem = lineChart.lookupAll(".chart-legend-item");
//            Label legendLabel = (Label) legendItem.lookup(".chart-legend-item-symbol + Label");
////                            legendLabel.setStyle("-fx-text-fill: " + color + ";");
        }

        Scene scene = new Scene(lineChart, 1080, 640);

        // Code below lets the javaFX scene use the "styles.css" file, located in resources of the project
        // to modify elements (called nodes in javaFX) in the scene
        URL cssUrl = DataViewer.class.getResource("styles.css");
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        } else {
            System.err.println("Stylesheet not found!");
        }

        return scene;

    }
}



