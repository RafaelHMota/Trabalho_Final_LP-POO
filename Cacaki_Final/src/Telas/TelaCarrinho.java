
package Telas;

import Metodos.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaCarrinho {

    public JPanel panelCarrinho;

    private JRadioButton debitoRadioButton;
    private JRadioButton creditoRadioButton;
    private JRadioButton pixRadioButton;

    private JButton verificarButton;
    private JButton pagarButton;
    private JButton voltarButton;
    private JTextField textField1;

    private ButtonGroup grupoPagamento;

    public TelaCarrinho(ArrayList<Item> itensCarrinho) {
        grupoPagamento = new ButtonGroup();
        grupoPagamento.add(debitoRadioButton);
        grupoPagamento.add(creditoRadioButton);
        grupoPagamento.add(pixRadioButton);

        verificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (grupoPagamento.getSelection() != null) {
                    double total = 0;
                    StringBuilder sb = new StringBuilder();
                    for (Item item : itensCarrinho) {
                        sb.append(item.getNome()).append(": R$ ").append(item.getValor()).append(" x ").append(item.getQuantidade()).append("\n");
                        total += item.getValor() * item.getQuantidade();
                    }
                    sb.append("Total: R$ ").append(total);
                    JOptionPane.showMessageDialog(panelCarrinho, sb.toString(), "Itens do Carrinho", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(panelCarrinho, "Por favor, selecione uma forma de pagamento.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "Compra finalizada!");
                TelaVendas telaVendas = new TelaVendas();
                JFrame frame = new JFrame("Tela de Vendas");
                frame.setContentPane(telaVendas.framevendas);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setResizable(true);
                frame.setSize(650, 650);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                panelCarrinho.getTopLevelAncestor().setVisible(false);
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaVendas telaVendas = new TelaVendas();

                JFrame frame = new JFrame("Tela de Vendas");
                frame.setContentPane(telaVendas.framevendas);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setResizable(true);
                frame.setSize(650, 650);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                panelCarrinho.getTopLevelAncestor().setVisible(false);
            }
        });
    }
}