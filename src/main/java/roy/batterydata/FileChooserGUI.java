package roy.batterydata;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileChooserGUI {

    public static List<File> openFileChooser(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Files");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", "*.csv"));
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));

        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(stage);

        return selectedFiles != null ? selectedFiles : new ArrayList<>();
    }
}


