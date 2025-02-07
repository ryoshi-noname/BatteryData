package roy.batterydata;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.File;
//import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {
    public static final int ROWS_TO_SKIP = 11;
    public static final int[] COLUMNS_TO_READ = new int[]{0,2};
    // Initialization values for reading the CSV format files from the ZKETECH EBD-A20H battery tester
    // ROWS_TO_SKIP:      File from ZKETECH EBD-A20H battery tester has "11" lines of headers that are not CSV data
    // COLUMNS_TO_READ:   1st and 3rd columns contain the time and voltage values

    @Override
    public void start(Stage primaryStage) {
            // This class will call the other classes and control the GUI window

            //JavaFX GUI code
            Button button = new Button("Select Files");
            Font font = Font.font("Arial", FontWeight.BOLD, 30);
            button.setFont(font);

            // button press calls javaFX FileChooser API to select folder & files
            button.setOnAction((ActionEvent e) -> {

                List<File> files = FileChooserGUI.openFileChooser(primaryStage);
                Label label = new Label(".........Please Wait........\nProcessing Data Files");
                label.setFont(font);
                label.setAlignment(Pos.CENTER);
                Scene scene = new Scene(label, 1080, 640);
                primaryStage.setScene(scene);

                primaryStage.setScene(DataViewer.generateScene("Team 2367\nBattery Tester Data Viewer",
                    files));
            });

            StackPane selectFilesWindow = new StackPane(button);
            Scene scene = new Scene(selectFilesWindow, 1080, 640);

            primaryStage.setTitle("Team 2367 Battery Tester Data Viewer");

        primaryStage.setScene(scene);

            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }






