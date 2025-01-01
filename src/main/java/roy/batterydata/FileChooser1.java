package roy.batterydata;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.File;

public class FileChooser extends Application {

  @Override
  public void start(Stage primaryStage) {
    // Create a button to trigger the FileChooser
    Button openFileButton = new Button("Open File");
    openFileButton.setOnAction(event -> {
      // Create a FileChooser
      FileChooser fileChooser = new FileChooser();

      // Set file chooser properties
      fileChooser.setTitle("Open Resource File");
      fileChooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
          new FileChooser.ExtensionFilter("Text Files", "*.txt"),
          new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
          new FileChooser.ExtensionFilter("All Files", "*.*")

      );

      // Show open file dialog
      File selectedFile = fileChooser.showOpenDialog(primaryStage);
      if (selectedFile != null) {
        System.out.println("File selected: " + selectedFile.getAbsolutePath());
      } else {
        System.out.println("No file selected.");
      }
    });

    // Set up the scene
    VBox root = new VBox(10, openFileButton);
    Scene scene = new Scene(root, 400, 200);

    primaryStage.setTitle("JavaFX FileChooser Example");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}

