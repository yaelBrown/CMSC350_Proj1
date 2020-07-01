import sun.tools.java.SyntaxError;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI extends JPanel {

    private JLabel expression = new JLabel("Enter Expression");
    private JLabel result = new JLabel("Result");

    private JTextField expressionField = new JTextField("");
    private JTextField resultField = new JTextField();

    private JButton preToPostBtn = new JButton("Prefix to Postfix");
    ActionListener preToPostOperation = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String conversion = null;

            try {
                conversion = Conversion.pre2Post(expressionField.getText());
                resultField.setText(conversion);
            } catch (SyntaxError syntaxError) {
                syntaxError.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println("pre to post btn was pressed");
        }
    };
    private JButton postToPreBtn = new JButton("Postfix to Prefix");
    ActionListener postToPreOperation = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String conversion = null;

            try {
                conversion = Conversion.post2Pre(expressionField.getText());
                resultField.setText(conversion);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (SyntaxError syntaxError) {
                syntaxError.printStackTrace();
            }

            System.out.println("post to pre btn was pressed");
        }
    };

    public GUI() {
        setLayout(new GridLayout(3,1,5,5));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        resultField.setEditable(false);

        this.add(expression);
        this.add(expressionField);
        this.add(preToPostBtn);
        this.add(postToPreBtn);
        this.add(result);
        this.add(resultField);

        preToPostBtn.addActionListener(preToPostOperation);
        postToPreBtn.addActionListener(postToPreOperation);
    }
}
