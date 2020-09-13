package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.StringBuilder;

public class MathTutorGUI extends JFrame implements ActionListener
{

    private JTextField text;    //text fields where user enters numbers
    private JTextField answerText;  //textfield where answer is displayed
    private StringBuilder textString = new StringBuilder(); //textString is what is being appended to from text
    private InfixToPostfix eval;    //object to evaluate postfix from infix and spit out answer from postfix
    private String memoryString = "";    //to keep track of multiple operations
    MathTutorGUI()
    {
        super("Calculator");
        setSize(500, 400);
        getContentPane().setBackground(Color.GRAY);
        setLayout(new BorderLayout());
        JPanel textPanel = new JPanel(new BorderLayout());  //panel for adding textfields
        text = new JTextField();
        text.setColumns(15);
        answerText = new JTextField();
        answerText.setColumns(15);
        add(textPanel, BorderLayout.NORTH);
        textPanel.add(text, BorderLayout.NORTH);
        textPanel.add(answerText, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4)); //initializing container with layout manager
        add(buttonPanel);

        //create all the buttons
        JButton b1 = new JButton("1");
        JButton b2 = new JButton("2");
        JButton b3 = new JButton("3");
        JButton b4 = new JButton("4");
        JButton b5 = new JButton("5");
        JButton b6 = new JButton("6");
        JButton b7 = new JButton("7");
        JButton b8 = new JButton("8");
        JButton b9 = new JButton("9");
        JButton b0 = new JButton("0");
        JButton plus = new JButton("+");
        JButton minus = new JButton("-");
        JButton times = new JButton("x");
        JButton equals = new JButton("=");
        JButton clear = new JButton("Cls");
        JButton off = new JButton("OFF");

        //register listeners with all the buttons
        b1.addActionListener(this); b1.setFont(new Font("Arial", Font.PLAIN, 40));
        b2.addActionListener(this); b2.setFont(new Font("Arial", Font.PLAIN, 40));
        b3.addActionListener(this); b3.setFont(new Font("Arial", Font.PLAIN, 40));
        b4.addActionListener(this); b4.setFont(new Font("Arial", Font.PLAIN, 40));
        b5.addActionListener(this); b5.setFont(new Font("Arial", Font.PLAIN, 40));
        b6.addActionListener(this); b6.setFont(new Font("Arial", Font.PLAIN, 40));
        b7.addActionListener(this); b7.setFont(new Font("Arial", Font.PLAIN, 40));
        b8.addActionListener(this); b8.setFont(new Font("Arial", Font.PLAIN, 40));
        b9.addActionListener(this); b9.setFont(new Font("Arial", Font.PLAIN, 40));
        b0.addActionListener(this); b0.setFont(new Font("Arial", Font.PLAIN, 40));
        plus.addActionListener(this); plus.setFont(new Font("Arial", Font.PLAIN, 40));
        minus.addActionListener(this); minus.setFont(new Font("Arial", Font.PLAIN, 40));
        times.addActionListener(this); times.setFont(new Font("Arial", Font.PLAIN, 40));
        equals.addActionListener(this); equals.setFont(new Font("Arial", Font.PLAIN, 40));
        clear.addActionListener(this); clear.setFont(new Font("Arial", Font.PLAIN, 40));
        off.addActionListener(this); off.setFont(new Font("Arial", Font.PLAIN, 40));

        //add buttons to buttonPanel

        buttonPanel.add(b7);
        buttonPanel.add(b8);
        buttonPanel.add(b9);
        buttonPanel.add(plus);

        buttonPanel.add(b4);
        buttonPanel.add(b5);
        buttonPanel.add(b6);
        buttonPanel.add(minus);

        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(times);

        buttonPanel.add(clear);
        buttonPanel.add(b0);
        buttonPanel.add(off);
        buttonPanel.add(equals);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e)
    {
        String event = e.getActionCommand();

        try //put everything into one big try-catch block to catch NumberFormatException
        {
            if (event.equals("1")) {
                emptyAnswer();
                textString.append("1");
                text.setText(textString.toString());
            } else if (event.equals("2")) {
                emptyAnswer();
                textString.append("2");
                text.setText(textString.toString());
            } else if (event.equals("3")) {
                emptyAnswer();
                textString.append("3");
                text.setText(textString.toString());
            } else if (event.equals("4")) {
                emptyAnswer();
                textString.append("4");
                text.setText(textString.toString());
            } else if (event.equals("5")) {
                emptyAnswer();
                textString.append("5");
                text.setText(textString.toString());
            } else if (event.equals("6")) {
                emptyAnswer();
                textString.append("6");
                text.setText(textString.toString());
            } else if (event.equals("7")) {
                emptyAnswer();
                textString.append("7");
                text.setText(textString.toString());
            } else if (event.equals("8")) {
                emptyAnswer();
                textString.append("8");
                text.setText(textString.toString());
            } else if (event.equals("9")) {
                emptyAnswer();
                textString.append("9");
                text.setText(textString.toString());
            } else if (event.equals("0")) {
                emptyAnswer();
                textString.append("0");
                text.setText(textString.toString());
            } else if (event.equals("+")) {
                emptyAnswer();
                textString.append(" + ");
                text.setText(textString.toString());
            } else if (event.equals("-")) {
                emptyAnswer();
                textString.append(" - ");
                text.setText(textString.toString());
            } else if (event.equals("x")) {
                emptyAnswer();
                textString.append(" x ");
                text.setText(textString.toString());
            } else if (event.equals("Cls")) {
                emptyAnswer();
                memoryString = "";  //when they hit cls, we want all memory tracking cleared
                textString.setLength(0);    //this pretty much kills the entire StringBuilder
                text.setText("");
            } else if (event.equals("OFF")) {
                System.exit(0);
            } else if (event.equals("=")) {
                try {
                    emptyAnswer();
                    eval = new InfixToPostfix();
                    int answer = 0;
                    if (memoryString.isBlank()) //if blank, it's the first entry so just output data normally
                    {
                        memoryString = textString.toString();
                        eval.infixToPostfix(memoryString);
                        answer = eval.evaluatePostfix(eval.getPostfixString());
                    } else if (Character.isDigit(textString.charAt(0))) {
                        throw new NumberFirstException("ERROR");
                    } else   //else, the user has not clicked cls to clear the string
                    {      //retain the last entry as a memorystring an add it with the infix string
                        memoryString = memoryString + textString.toString();
                        eval.infixToPostfix(memoryString);
                        answer = eval.evaluatePostfix(eval.getPostfixString());
                        memoryString = String.valueOf(answer);
                    }
                    answerText.setText(String.valueOf(answer));
                    textString.setLength(0);
                    text.setText("");
                } catch (NumberFirstException e2) { //if the user enters in a digit first instead of an operator
                    answerText.setText("ERROR PRESS CLS");
                    text.setText("");
                }
            }
        }
        catch (NumberFormatException e1)
        {
            text.setText("");
        }
    }

    public void emptyAnswer()
    {
        if (!(answerText.getText().isEmpty()))
        {
            answerText.setText("");
        }
    }

    public class NumberFirstException extends Exception
    {
        public NumberFirstException(String message)
        {super(message);}
    }
}
