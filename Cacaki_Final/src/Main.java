import Telas.TelaLogin;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame t = new JFrame("Tela Inicial");
        t.setContentPane(new TelaLogin().telalogin );
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t.pack();
        t.setResizable(true);
        t.setSize(450, 650);
        t.setLocationRelativeTo(null);
        t.setVisible(true);
    }
}