package roy.batterydata;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataViewer {
    private static final Pattern FILE_NAME_PATTERN = Pattern.compile(".*\\\\(.*)\\..*");

    public static Scene generateScene(String chartName, List<File> files) {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Time (seconds)");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Voltage");
        // Set custom bounds for the axes
        xAxis.setLowerBound(10); // Set lower limit of X-axis
        xAxis.setUpperBound(13.5); // Set upper limit of X-axis

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
        }

        return new Scene(lineChart, 1080, 640);
    }
}



