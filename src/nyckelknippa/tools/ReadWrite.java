package nyckelknippa.tools;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

// ¤ ¨

public class ReadWrite {

  private String saveFile = "../resources/passwords";

  public ReadWrite() {

  }

  public String[] getSavedPasswords() {
    checkForSaveFile();

    try {
        FileReader fileReader = new FileReader(saveFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String[] passwords = bufferedReader.readLine().split("¤");

        bufferedReader.close();
    }
    catch(FileNotFoundException fnfe) {
        System.err.println("Unable to open file '" + saveFile + "'");
    } catch(IOException ieo) {
        System.err.println("Error reading file '" + saveFile + "'");
    }
    return null;
  }

  public void savePassword(String password, String title) {
    checkForSaveFile();
    try {
      FileWriter fileWriter = new FileWriter(saveFile);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

      bufferedWriter.write((title + "¨" + password));

      bufferedWriter.close();
    } catch(IOException ioe) {
      System.out.println("Error writing to file '" + saveFile + "'");
    }
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
