package roy.batterydata;

import java.io.File;

public class ReadFileNames {

    public static void main(String[] args) {
      String folderPath = "C:\\Users\\ryosh\\Documents\\Battery_Testing_Data"; // Replace with your folder path

      File folder = new File(folderPath);

      if (!folder.exists() || !folder.isDirectory()) {
        System.out.println("Invalid folder path.");
        return;
      }

      String[] fileNames = folder.list();

      if (fileNames == null) {
        System.out.println("No files found in the folder.");
        return;
      }
//System.out.println(fileNames);
      for (String fileName : fileNames) {
        System.out.println(fileName);
      }
    }
}
