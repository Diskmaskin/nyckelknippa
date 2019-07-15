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

  private FileReader fileReader;
  private BufferedReader bufferedReader;
  private FileWriter fileWriter;
  private BufferedWriter bufferedWriter;

  private String saveFile = "./resources/passwords";
  private String saveData = "";

  public ReadWrite() {
    readSavedPassword();
  }

  public String[] getSavedPasswords() {
    checkForSaveFile();
    readSavedPassword();
    return saveData.split("¤");
  }

  public void savePassword(String password, String title) {
    checkForSaveFile();
    readSavedPassword();
    try {
      fileWriter = new FileWriter(saveFile);
      bufferedWriter = new BufferedWriter(fileWriter);
      if (saveData.equals("") || saveData.equals("null") || saveData == null) {
        bufferedWriter.write((title + "¨" + password));
      } else {
        bufferedWriter.write(saveData + "¤" + title + "¨" + password);
      }

      bufferedWriter.close();
    } catch(IOException ioe) {
      System.out.println("Error writing to file '" + saveFile + "'");
    }
  }

  private void readSavedPassword() {
    try {
        fileReader = new FileReader(saveFile);
        bufferedReader = new BufferedReader(fileReader);

        saveData = bufferedReader.readLine();
        if (saveData == null) saveData = "";

        bufferedReader.close();
    }
    catch(FileNotFoundException fnfe) {
        System.err.println("Unable to open file '" + saveFile + "'");
    } catch(IOException ieo) {
        System.err.println("Error reading file '" + saveFile + "'");
    }
  }

  public void deletePassword(String title) {
    if (title == null) return;
    checkForSaveFile();
    readSavedPassword();
    String[] passwords = getSavedPasswords();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < passwords.length; i++) {
      if (passwords[i].split("¨")[0].equals(title)) {
        for (int j = 0; j < passwords.length; j++) {
          if (j == i) {
            continue;
          } else {
            if (j > 0) sb.append("¤");
            sb.append(passwords[j]);
          }
        }
        break;
      }
    }

    saveData = sb.toString();
    try {
      fileWriter = new FileWriter(saveFile);
      bufferedWriter = new BufferedWriter(fileWriter);

      bufferedWriter.write(saveData);

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
