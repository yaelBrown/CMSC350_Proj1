import javax.swing.*;

public class Project1 {

    public static void main(String[] args) {
        JFrame p1 = new JFrame("Expression Converter");
        p1.setSize(500,250);
        p1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p1.setLocationRelativeTo(null);
        p1.add(new GUI());
        p1.setVisible(true);
    }

}
