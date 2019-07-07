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

  GridBagConstraints buttonGBC;
  GridBagConstraints optionGBC;

  //BUTTONPANEL COMPONENTS
  //Buttons
  private JButton genButton = new JButton("Generate password");
  private JButton submitButton = new JButton("Submit password");

  //TextFields
  private JTextField resultField = new JTextField("Generated password appears here");

  //OPTIONPANEL COMPONENTS
  //Checkboxes
  private final JCheckBox alphanumerical = new JCheckBox("Alphanumerical");
  private final JCheckBox arithmetic = new JCheckBox("= + - *");
  private final JCheckBox underscore = new JCheckBox("_");
  private final JCheckBox specChar1 = new JCheckBox("! , . ;");
  private final JCheckBox specChar2 = new JCheckBox("{ } [ ]");
  private final JCheckBox specChar3 = new JCheckBox("| ` ^ ' \"");
  private final JCheckBox specChar4 = new JCheckBox("/ \\ < > ~ (Not recommended)");
  private final JCheckBox specChar5 = new JCheckBox("[SPACE] [TAB] (Not recommended)");

  //TextFields
  private JTextField lengthField = new JTextField("", 15);
  private JTextField titleField = new JTextField("", 15);
  private JPasswordField customPasswordField = new JPasswordField("", 15);

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
    buttonGBC.anchor = GridBagConstraints.NORTHWEST;

    buttonPanel.add(genButton, buttonGBC);
    buttonGBC.gridy++;
    resultField.setEditable(false);
    buttonPanel.add(resultField, buttonGBC);
    buttonGBC.gridx++;
    buttonGBC.gridy = 0;
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

}
