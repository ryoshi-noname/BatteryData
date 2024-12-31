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
    stage.setTitle("Line Chart Sample");
    //defining the axes
    final NumberAxis xAxis;
    xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Time (Seconds)");
    yAxis.setLabel("Voltage (V)");
    //creating the chart
    final LineChart<Number, Number> lineChart =
        new LineChart<>(xAxis, yAxis);

    lineChart.setTitle("Battery Test Data");
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
