package nyckelknippa.components;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.border.EtchedBorder;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

  private GridBagConstraints buttonGBC;
  private GridBagConstraints optionGBC;

  //BUTTONPANEL COMPONENTS
  //Buttons
  private JButton genButton = new JButton("Generate password");
  private JButton submitButton = new JButton("Submit password");

  //TextFields
  private JTextField resultField = new JTextField("Generated password appears here", 30);

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
    setMinimumSize(new Dimension(560, 350));
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridBagLayout());
    populateButtonPanel();
    optionPanel = new JPanel();
    optionPanel.setLayout(new GridBagLayout());
    optionPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
    populateOptionPanel();

    add(buttonPanel, BorderLayout.NORTH);
    add(optionPanel, BorderLayout.SOUTH);
    pack();
    setVisible(true);
  }

  private void populateButtonPanel() {
    buttonGBC = new GridBagConstraints();
    buttonGBC.gridx = 0;
    buttonGBC.gridy = 0;
    buttonGBC.anchor = GridBagConstraints.CENTER;
    genButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        generatePassword();
      }
    });
    buttonPanel.add(genButton, buttonGBC);
    buttonGBC.gridy++;
    resultField.setEditable(false);
    buttonPanel.add(resultField, buttonGBC);
    buttonGBC.gridx++;
    buttonGBC.gridy = 0;
    submitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        submitPassword();
      }
    });
    buttonPanel.add(submitButton, buttonGBC);
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
    System.out.println(dateFormat.format(date));
    readWrite.savePassword(password, (titleField.getText() + " " + dateFormat.format(date)));
  }

}
