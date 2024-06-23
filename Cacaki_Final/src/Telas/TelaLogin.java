package Telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TelaLogin {
    public JPanel telalogin;
    private JTextField nomeTextField;
    private JPasswordField senhaPasswordField;
    private JButton entrarButton;
    private JButton cadastrarButton;
    private JButton sairButton;

    public TelaLogin() {
        entrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = nomeTextField.getText();
                char[] senha = senhaPasswordField.getPassword();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_senhas", "root", "root");
                    PreparedStatement pst = connection.prepareStatement("SELECT * FROM dados_senhas WHERE usuario=? AND senha=?");
                    pst.setString(1, usuario);
                    pst.setString(2, new String(senha));
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        TelaVendas telaVendas = new TelaVendas();
                        JFrame frame = new JFrame("Tela de Vendas");
                        frame.setContentPane(telaVendas.framevendas);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.pack();
                        frame.setResizable(true);
                        frame.setSize(650, 650);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");
                    }
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Cadastro cadastro = new Cadastro();
                JFrame frame = new JFrame("Cadastro");
                frame.setContentPane(cadastro.cadastroframe);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setResizable(true);
                frame.setSize(290, 440);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        sairButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new TelaLogin().telalogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(900, 950);
    }
}