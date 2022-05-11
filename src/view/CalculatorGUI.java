package view;

import exception.MyException;
import input.Input;

import javax.swing.*;
import java.util.Locale;


public class CalculatorGUI
{
    private JPanel mainPanel;
    private JButton zeroButton;
    private JButton dotButton;
    private JButton equalsButton;
    private JButton plusButton;
    private JButton oneButton;
    private JButton twoButton;
    private JButton threeButton;
    private JButton fourButton;
    private JButton fiveButton;
    private JButton sixButton;
    private JButton multiplicationButton;
    private JButton sevenButton;
    private JButton eightButton;
    private JButton nineButton;
    private JButton divisionButton;
    private JButton minusButton;
    private JButton backspaceButton;
    private JButton clearButton;
    private JButton leftParenthesisButton;
    private JButton rightParenthesisButton;
    private JTextField ioTextField;
    private JButton exitButton;
    private String tmp = "";


    public CalculatorGUI()
    {
        //Numbers
        zeroButton.addActionListener(e ->
        {
            tmp += "0";
            ioTextField.setText(ioTextField.getText() + "0");
        });
        oneButton.addActionListener(e ->
        {
            tmp += "1";
            ioTextField.setText(ioTextField.getText() + "1");
        });
        twoButton.addActionListener(e ->
        {
            tmp += "2";
            ioTextField.setText(ioTextField.getText() + "2");
        });
        threeButton.addActionListener(e ->
        {
            tmp += "3";
            ioTextField.setText(ioTextField.getText() + "3");
        });
        fourButton.addActionListener(e ->
        {
            tmp += "4";
            ioTextField.setText(ioTextField.getText() + "4");
        });
        fiveButton.addActionListener(e ->
        {
            tmp += "5";
            ioTextField.setText(ioTextField.getText() + "5");
        });
        sixButton.addActionListener(e ->
        {
            tmp += "6";
            ioTextField.setText(ioTextField.getText() + "6");
        });
        sevenButton.addActionListener(e ->
        {
            tmp += "7";
            ioTextField.setText(ioTextField.getText() + "7");
        });
        eightButton.addActionListener(e ->
        {
            tmp += "8";
            ioTextField.setText(ioTextField.getText() + "8");
        });
        nineButton.addActionListener(e ->
        {
            tmp += "9";
            ioTextField.setText(ioTextField.getText() + "9");
        });

        dotButton.addActionListener(e ->
        {
            tmp += ".";
            ioTextField.setText(ioTextField.getText() + ".");
        });
        leftParenthesisButton.addActionListener(e ->
        {
            tmp += " ( ";
            ioTextField.setText(ioTextField.getText() + "(");
        });
        rightParenthesisButton.addActionListener(e ->
        {
            tmp += " ) ";
            ioTextField.setText(ioTextField.getText() + ")");
        });

        //Operations
        plusButton.addActionListener(e ->
        {
            tmp += " + ";
            ioTextField.setText(ioTextField.getText() + "+");
        });
        minusButton.addActionListener(e ->
        {
            tmp += " - ";
            ioTextField.setText(ioTextField.getText() + "-");
        });
        multiplicationButton.addActionListener(e ->
        {
            tmp += " * ";
            ioTextField.setText(ioTextField.getText() + "*");
        });
        divisionButton.addActionListener(e ->
        {
            tmp += " / ";
            ioTextField.setText(ioTextField.getText() + "/");
        });
        equalsButton.addActionListener(e ->
        {
            tmp += " =";
            try
            {
                Input myInput = new Input(tmp);
                ioTextField.setText(Double.toString(myInput.getResult()));
            }
            catch (MyException ex)
            {
                ioTextField.setText(ex.getMes());
            }

        });
        backspaceButton.addActionListener(e ->
        {
            if (tmp.length() > 0)
            {
                tmp = tmp.substring(0, tmp.length() - 1);
                ioTextField.setText(ioTextField.getText().substring(0, ioTextField.getText().length() - 1));
            }

        });
        clearButton.addActionListener(e ->
        {
            tmp = "";
            ioTextField.setText("");
        });

        exitButton.addActionListener(e -> System.exit(0));
    }


    public static void main(String[] args)
    {
        JFrame mainFrame = new JFrame("Calculator");
        CalculatorGUI form = new CalculatorGUI();
        mainFrame.setContentPane(form.mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
