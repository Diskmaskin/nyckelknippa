package nyckelknippa.tools;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

// ¤ ¨

public class ReadWrite {

  private String saveFile = "../resources/passwords";
  private String line = null;

  public ReadWrite() {
    // This will reference one line at a time

  //   try {
  //       FileReader fileReader = new FileReader(saveFile);
  //       BufferedReader bufferedReader = new BufferedReader(fileReader);
  //
  //       while((line = bufferedReader.readLine()) != null) {
  //           System.out.println(line);
  //       }
  //
  //       bufferedReader.close();
  //   }
  //   catch(FileNotFoundException ex) {
  //       System.err.println("Unable to open file '" + fileName + "'");
  //   }
  //   catch(IOException ex) {
  //       System.err.println("Error reading file '" + fileName + "'");
  //   }
  }

  public String[] getSavedPasswords() {
    checkForSaveFile();

    return null;
  }

  public void savePassword(String password, String title) {
    checkForSaveFile();

  }

  private void checkForSaveFile() {
    try {
      File tmp = new File(saveFile);
      if (!tmp.exists()) {
        tmp.createNewFile();
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }

}
