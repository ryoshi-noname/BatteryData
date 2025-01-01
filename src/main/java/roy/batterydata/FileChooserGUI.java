package roy.batterydata;

import java.io.File;           // For the File class
import java.util.List;         // For the List interface

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label; // Import for Label
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;



public class FileChooserGUI extends Application {

  @Override
  public void start(Stage primaryStage) {




    // Create a button to trigger the FileChooser
    Button selectFilesButton = new Button("Select Files to Open");
    selectFilesButton.setOnAction(event -> {
      // Create a FileChooser
      FileChooser fileChooser = new FileChooser();

      // Set the initial directory (replace with your desired path)
      File initialDirectory = new File("C:\\Users\\ryosh\\Documents\\Battery_Testing_Data");
      fileChooser.setInitialDirectory(initialDirectory);

      // Set file chooser properties
      fileChooser.setTitle("Select Files");
      fileChooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
//          new FileChooser.ExtensionFilter("Text Files", "*.txt"),
//          new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
          new FileChooser.ExtensionFilter("All Files", "*.*")

      );


      // Show open multiple files dialog
      List<File> selectedFiles = fileChooser.showOpenMultipleDialog(primaryStage);


      // Handle the selected files
      if (selectedFiles != null) {
        System.out.println("Selected files:");

        // Here is where a different window or same window will display selected files
        // and ask if these are the ones to display?
        // if yes, then launch the chart with selected files
        // if no, then give option to reselect files, or cancel, or quit.
        // for if questions above, maybe use cases to avoid multiple ifs??


        for (File file : selectedFiles) {
          System.out.println(file.getAbsolutePath());
        }
      } else {
        System.out.println("No files selected.");
      }
    });

    // Set up the scene

    Label label1 = new Label("Team 2637\n");
    label1.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-font-size: 20pt;");
//    label1.setStyle("-fx-font-weight: bold;");
//    label1.setStyle("-fx-font-size: 18px;");  // Change font size to 18px
//    label1.setStyle("-fx-font-style: italic;");  // Italicize the font
    Label label2 = new Label("Battery Test Data Viewer\n\nPlease click the button below\nand navigate to the data folder");
    VBox root = new VBox(10 ,label1, label2, selectFilesButton); // 10px spacing between children
    // Set the alignment of all children to center
    root.setAlignment(Pos.CENTER);




    // Set margin for the label and button
    VBox.setMargin(label1, new Insets(10,100,1,40));
    VBox.setMargin(selectFilesButton, new Insets(1, 100, 20, 40)); // Top, Right, Bottom, Left

    Scene scene = new Scene(root, 400, 200);

    primaryStage.setTitle("Battery Data Viewer");
    primaryStage.setScene(scene);
    primaryStage.show();

  }


  public static void main(String[] args) {
    launch(args);
  }
}

