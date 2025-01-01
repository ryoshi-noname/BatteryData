package roy.batterydata;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DataViewer extends Application{
  ArrayList<double[]> dataPairs;
  String dataName;

  // Additional development notes
  // 1) Need a constructor(??) to find the csvFile name from the folder.
  // 2) Also need to initialize the rowsToSkip and columnsToRead here for easy maintenance.
  // 3) Somehow need a "loop" or "forEach" structure to read in multiple data sets with different names.
  // 4) It may be possible to use the filechooser in javaFX to filter the files to process.
  // 5) Try to make the axes rescalable--need to see how this is done in javaFX.



  public DataViewer(){
    ChartData chartData2 = new ChartData("C:\\Users\\ryosh\\OneDrive\\Desktop\\Squoosh.csv", 11, new int[]{0,2});

    this.dataPairs = chartData2.dataPairs;
    this.dataName = "Squoosh";
  }

  public DataViewer(ArrayList<double[]> dataPairs, String dataName){
    this.dataPairs = dataPairs;
    this.dataName = dataName;
  }

  @Override
  public void start(Stage stage) {
    stage.setTitle("Line Chart (X-Y Chart) X:Battery Voltage Y:Time");
    //defining the axes
//    final NumberAxis xAxis;
    final NumberAxis xAxis;
      xAxis = new NumberAxis(); // (no arguments) will auto scale: args set lower/upper bounds and tick unit
//    final NumberAxis yAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis(12 ,13.5, 0.1); // Lower bound, upper bound, tick unit
//    NumberAxis yAxis = new NumberAxis(); // Empty args = autoscale is enabled
    xAxis.setLabel("Time (Seconds)");
    yAxis.setLabel("Voltage (V)");





    //creating the chart
//    final LineChart<Number, Number> lineChart =
    LineChart<Number, Number> lineChart =
        new LineChart<>(xAxis, yAxis);

    lineChart.setTitle("Battery Test Data");

    // CSS style sheet in resources used to set each point size smaller--default has large circles
    lineChart.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

    //defining a series
    XYChart.Series<Number, Number> series = new XYChart.Series<>();
    series.setName("Squoosh1");





    for (int i = 0; i < dataPairs.size(); i++) {
      series.getData().add(new XYChart.Data<>(dataPairs.get(i)[0],dataPairs.get(i)[1]));

    }



    Scene scene  = new Scene(lineChart,800,600);
    lineChart.getData().add(series);

    stage.setScene(scene);
    stage.show();

  }

  public static void main(String[] args){
    launch(args);
  }

}

