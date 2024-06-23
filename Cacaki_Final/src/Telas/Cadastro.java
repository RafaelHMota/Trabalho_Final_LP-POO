package Telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;

public class Cadastro {
    public JPanel cadastroframe;
    private JButton salvarButton;
    private JTextField textnome;
    private JTextField textemail;
    private JTextField textsenha;
    private JCheckBox cpfCheckBox;
    private JTextField cnpjtextField;
    private JCheckBox cnpjCheckBox;
    private JButton voltarButton;
    private JTextField cpfTextFild;

    public Cadastro() {
    salvarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String usuario = textnome.getText();
            String email = textemail.getText();
            String senha = textsenha.getText();
            String cpf = cpfTextFild.getText();
            String cnpj = cnpjtextField.getText();
            LocalDateTime dataCadastro = LocalDateTime.now();

            if (usuario.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome, email e senha não podem estar vazios.");
                return;
            }
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_senhas", "root", "root");

                String sql = "INSERT INTO dados_senhas (usuario, email, senha, CPF, CNPJ, data_cadastro) VALUES (?, ?, ?, ?, ?, ?)";

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, usuario);
                statement.setString(2, email);
                statement.setString(3, senha);


                statement.setString(4, cpf);
                statement.setString(5, cnpj);
                statement.setTimestamp(6, Timestamp.valueOf(dataCadastro));

                if (cpfCheckBox.isSelected()) {
                    statement.setString(4, cpfTextFild.getText());
                    statement.setNull(5, java.sql.Types.INTEGER);
                } else if (cnpjCheckBox.isSelected()) {
                    statement.setNull(4, java.sql.Types.INTEGER);
                    statement.setString(5, cnpjtextField.getText());
                }


                int addedRows = statement.executeUpdate();
                if (addedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(cadastroframe);
                    topFrame.dispose();
                }

                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel telaLoginFrame = new TelaLogin().telalogin;
                JFrame cadastroFrame = (JFrame) SwingUtilities.getWindowAncestor(cadastroframe);
                cadastroFrame.dispose();
                telaLoginFrame.setVisible(true);
            }
        });
}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cadastro");
        frame.setContentPane(new Cadastro().cadastroframe);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(290, 440);
    }
}