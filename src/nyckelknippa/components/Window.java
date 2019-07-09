package nyckelknippa.components;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

import java.util.ArrayList;
import java.util.Date;

import java.text.SimpleDateFormat;

import nyckelknippa.tools.*;

/**
OPTIONS
- Checkboxes
  - Alphanumerical [a-z][0-9]
  - Capitalisation [A-Z]
  - Include special Characters
    - = + - *
    - _
    - # $ % & ( ) : ? @"
    - ! , . ;
    - { } [ ]
    - | ` ^ ' "
    - / \ < > ~ (Not recommended)
    - [SPACE] [TAB] (Not recommended)

- TextFields
  - Length
  - Password name
  - Custom password

BUTTONS
- Generate password
- Submit password
**/
public class Window extends JFrame {

  private JPanel buttonPanel;
  private JPanel optionPanel;
  private JPanel tableOptionPanel;
  private JScrollPane tablePanel;

  private GridBagConstraints buttonGBC;
  private GridBagConstraints optionGBC;
  private GridBagConstraints tableOptionGBC;

  //BUTTONPANEL COMPONENTS
  //Buttons
  private JButton genButton = new JButton("Generate password");
  private JButton submitButton = new JButton("Submit password");
  private JButton showHidePassword = new JButton("Hide generated password");

  //TextFields
  private JTextField resultField = new JTextField("Generated password appears here", 30);
  private JLabel submitLabel = new JLabel("");

  //OPTIONPANEL COMPONENTS
  //Checkboxes
  private final JCheckBox alphanumerical = new JCheckBox("Alphanumerical");
  private final JCheckBox capitalisation = new JCheckBox("Capitalisation");
  private final JCheckBox arithmetic = new JCheckBox("= + - *");
  private final JCheckBox underscore = new JCheckBox("_");
  private final JCheckBox specChar1 = new JCheckBox("! , . ;");
  private final JCheckBox specChar2 = new JCheckBox("{ } [ ]");
  private final JCheckBox specChar3 = new JCheckBox("| ` ^ ' \"");
  private final JCheckBox specChar4 = new JCheckBox("/ \\ < > ~ (Not recommended)");
  private final JCheckBox specChar5 = new JCheckBox("[SPACE] [TAB] (Not recommended)");

  //TextFields
  private JTextField lengthField = new JTextField("", 20);
  private JTextField titleField = new JTextField("", 20);
  private JPasswordField customPasswordField = new JPasswordField("", 20);

  //TABLEOPTIONPANEL
  //Buttons
  private JButton refreshButton = new JButton("Refresh");
  private JButton showHideTable = new JButton("Show table");


  //TABLEPANEL
  private JTable table;
  private String[] columns = new String[] {"Title", "Password"};
  private String[] saveData;
  private String[][] data;

  // nyckelknippa
  private final PasswordGenerator passwordGenerator = new PasswordGenerator();
  private final ReadWrite readWrite = new ReadWrite();

  //
  private String password = "";

  private char[] alphanumericalArray = new char[] {
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
    'u', 'v', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
  };
  private char[] capitalisationArray = new char[] {
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
    'U', 'V', 'X', 'Y', 'Z'
  };
  private char[] arithmeticArray = new char[] {
    '=', '+', '-', '*'
  };
  private char[] specChar1Array = new char[] {
    '!', ',',  '.', ';'
  };
  private char[] specChar2Array = new char[] {
    '{', '}', '[', ']'
  };
  private char[] specChar3Array = new char[] {
    '|', '`', '^', '\'', '\"'
  };
  private char[] specChar4Array = new char[] {
    '/', '\\', '<', '>', '~'
  };
  private char[] specChar5Array = new char[] {
    ' ', '\t'
  };

  public Window() {
    setResizable(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(800, 400));

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridBagLayout());
    populateButtonPanel();

    optionPanel = new JPanel();
    optionPanel.setLayout(new GridBagLayout());
    optionPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
    populateOptionPanel();

    tableOptionPanel = new JPanel();
    tableOptionPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
    tableOptionPanel.setLayout(new GridBagLayout());
    populateTableOptionPanel();

    populateTablePanel();
    tablePanel.setVisible(false);

    add(buttonPanel, BorderLayout.NORTH);
    add(optionPanel, BorderLayout.CENTER);
    add(tableOptionPanel, BorderLayout.EAST);
    add(tablePanel, BorderLayout.SOUTH);
    pack();
    setVisible(true);
  }

  private void populateButtonPanel() {
    buttonGBC = new GridBagConstraints();
    buttonGBC.gridx = 0;
    buttonGBC.gridy = 0;
    buttonGBC.anchor = GridBagConstraints.CENTER;
    // buttonGBC.fill = GridBagConstraints.HORIZONTAL;
    genButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        generatePassword();
      }
    });
    buttonPanel.add(genButton, buttonGBC);
    buttonGBC.gridy++;
    resultField.setEditable(false);
    resultField.setHorizontalAlignment(JTextField.CENTER);
    resultField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
    buttonPanel.add(resultField, buttonGBC);
    buttonGBC.gridx++;
    showHidePassword.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        resultField.setVisible(!resultField.isVisible());
        showHidePassword.setText((resultField.isVisible()) ? "Hide generated password" : "Show generated password");
      }
    });
    buttonPanel.add(showHidePassword, buttonGBC);
    buttonGBC.gridx = 0;
    buttonGBC.gridy++;
    submitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        submitPassword();
      }
    });
    buttonPanel.add(submitButton, buttonGBC);
    buttonGBC.gridx = 0;
    buttonGBC.gridy++;
    submitLabel.setHorizontalAlignment(JLabel.CENTER);
    buttonPanel.add(submitLabel, buttonGBC);
  }

  private void populateOptionPanel() {
    optionGBC = new GridBagConstraints();
    optionGBC.gridx = 0;
    optionGBC.gridy = 0;
    optionGBC.anchor = GridBagConstraints.NORTHWEST;

    optionPanel.add(new JLabel("INCLUDE CHARACTERS"), optionGBC);
    optionGBC.gridy++;
    optionPanel.add(alphanumerical, optionGBC);
    optionGBC.gridy++;
    optionPanel.add(capitalisation, optionGBC);
    optionGBC.gridy++;
    optionPanel.add(arithmetic, optionGBC);
    optionGBC.gridy++;
    optionPanel.add(underscore, optionGBC);
    optionGBC.gridy++;
    optionPanel.add(specChar1, optionGBC);
    optionGBC.gridy++;
    optionPanel.add(specChar2, optionGBC);
    optionGBC.gridy++;
    optionPanel.add(specChar3, optionGBC);
    optionGBC.gridy++;
    optionPanel.add(specChar4, optionGBC);
    optionGBC.gridy++;
    optionPanel.add(specChar5, optionGBC);

    optionGBC.gridx = 1;
    optionGBC.gridy = 0;
    optionPanel.add(new JLabel("Password length"), optionGBC);
    optionGBC.gridy++;
    optionPanel.add(lengthField, optionGBC);
    optionGBC.gridy++;
    optionPanel.add(new JLabel("Password title"), optionGBC);
    optionGBC.gridy++;
    optionPanel.add(titleField, optionGBC);
    optionGBC.gridy++;
    optionPanel.add(new JLabel("Custom password (bypasses other options)"), optionGBC);
    optionGBC.gridy++;
    optionPanel.add(customPasswordField, optionGBC);
  }

  private void generatePassword() {
    ArrayList<Character> selectedChars = new ArrayList<Character>();
    int passwordLength = 16;

    try {
      passwordLength = Integer.parseInt(lengthField.getText());
    } catch (NumberFormatException nfe) {
      lengthField.setText("Input invalid. Setting to default: " + passwordLength);
    }

    if (alphanumerical.isSelected()) {
      for (char c : alphanumericalArray) {
        selectedChars.add(c);
      }
    }
    if (capitalisation.isSelected()) {
      for (char c: capitalisationArray) {
        selectedChars.add(c);
      }
    }
    if (arithmetic.isSelected()) {
      for (char c: arithmeticArray) {
        selectedChars.add(c);
      }
    }
    if (underscore.isSelected()) {
      selectedChars.add('_');
    }
    if (specChar1.isSelected()) {
      for (char c: specChar1Array) {
        selectedChars.add(c);
      }
    }
    if (specChar2.isSelected()) {
      for (char c: specChar2Array) {
        selectedChars.add(c);
      }
    }
    if (specChar3.isSelected()) {
      for (char c: specChar3Array) {
        selectedChars.add(c);
      }
    }
    if (specChar4.isSelected()) {
      for (char c: specChar4Array) {
        selectedChars.add(c);
      }
    }
    if (specChar5.isSelected()) {
      for (char c: specChar5Array) {
        selectedChars.add(c);
      }
    }

    if (selectedChars.size() <= 0) {
      resultField.setText("Please check one or more options");
    } else {
      password = passwordGenerator.generate(selectedChars, passwordLength);
      resultField.setText(password);
    }
  }

  private void submitPassword() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String customPassword = new String(customPasswordField.getPassword());
    // System.out.println(customPassword);
    if (customPassword.contains("¤") || customPassword.contains("¨")) {
      submitLabel.setText("Password not saved. Illegal character use.");
      return;
    }
    if (!customPassword.equals("") && customPassword != null && customPassword.length() != 0) {
      // System.out.println("Custom password");
      if (titleField.getText().equals("") || titleField.getText() == null || titleField.getText().equals("Please enter a password title")) {
        titleField.setText("Please enter a password title");
        submitLabel.setText("Password not saved");
      } else {
        // System.out.println("Custom password writted");
        readWrite.savePassword(customPassword, (titleField.getText() + " " + dateFormat.format(date)));
        submitLabel.setText("Password saved: " + "'" + titleField.getText() + "'");
      }
    } else {
      // System.out.println("Generated password");
      if (titleField.getText().equals("") || titleField.getText() == null || titleField.getText().equals("Please enter a password title")) {
        titleField.setText("Please enter a password title");
        submitLabel.setText("Password not saved");
      } else if (resultField.getText().equals("") || resultField.getText().equals("Generated password appears here") || resultField.getText().equals("Please generate or input custom password") || resultField.getText() == null) {
        resultField.setText("Please generate or input custom password");
        submitLabel.setText("Password not saved");
      } else {
        // System.out.println("Password generated: " + password);
        readWrite.savePassword(password, (titleField.getText() + " " + dateFormat.format(date)));
        submitLabel.setText("Password saved: " + "'" + titleField.getText() + "'");
      }
    }
    populateTablePanel();
  }

  private void populateTableOptionPanel() {
    tableOptionGBC = new GridBagConstraints();
    tableOptionGBC.gridx = 0;
    tableOptionGBC.gridy = 0;
    tableOptionGBC.anchor = GridBagConstraints.NORTHWEST;

    showHideTable.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        tablePanel.setVisible(!tablePanel.isVisible());
        refreshButton.setVisible(tablePanel.isVisible());
        showHideTable.setText((tablePanel.isVisible()) ? "Hide table" : "Show table");
        setMinimumSize((tablePanel.isVisible()) ? new Dimension(800, 800) : new Dimension(800, 400));
        setSize(getMinimumSize());
      }
    });
    tableOptionPanel.add(showHideTable, tableOptionGBC);
    refreshButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        populateTablePanel();
      }
    });
    refreshButton.setVisible(false);
    tableOptionGBC.gridy++;
    tableOptionPanel.add(refreshButton, tableOptionGBC);

  }

  private void populateTablePanel() {
    saveData = readWrite.getSavedPasswords();
    data = new String[saveData.length][2];
    for (int i = 0; i < saveData.length; i++) {
      if (saveData[i].contains("¨") && saveData[i] != null) {
        data[i][0] = saveData[i].split("¨")[0];
        data[i][1] = saveData[i].split("¨")[1];
      }
    }
    table = new JTable(data, columns);
    table.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
          switch (JOptionPane.showConfirmDialog(new JFrame(), ("Are you sure you want to delete " + data[table.getSelectedRow()][0]), "", JOptionPane.YES_NO_OPTION)) {
            case 0: readWrite.deletePassword(data[table.getSelectedRow()][0]); break;
            case 1: System.out.println("NO"); break;
          }
          populateTablePanel();
        }
      }
    });
    if (tablePanel != null) {
      remove(tablePanel);
      tablePanel = new JScrollPane(table);
      add(tablePanel, BorderLayout.SOUTH);
      showHideTable.setText("Hide table");
      setMinimumSize((tablePanel.isVisible()) ? new Dimension(800, 800) : new Dimension(800, 400));
      setSize(getMinimumSize());
      validate();
      repaint();
    } else {
      tablePanel = new JScrollPane(table);
    }
  }

}
