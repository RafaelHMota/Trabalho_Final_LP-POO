package Telas;

import Metodos.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaVendas {

    public JPanel framevendas;

    public JSpinner quantBone;
    public JSpinner quantLuva;
    public JSpinner quantRifle;
    public JSpinner quantMunicao;
    public JSpinner quantCamuflagem;

    public JPanel item1;
    public JPanel item2;
    public JPanel item3;
    public JPanel item4;
    public JPanel item5;
    public JPanel item6;

    public JLabel img2;
    public JLabel img3;
    public JLabel img4;
    public JLabel img5;
    public JLabel img6;

    public JButton addcarrinhoButton;

    public JCheckBox luvaBox;
    private JCheckBox boneBox;
    private JCheckBox botaBox;
    private JCheckBox municaobox;
    private JCheckBox rifleBox;
    private JCheckBox camuflagemBox;

    private JSpinner quantBota;

    private JButton carrinhoButton;
    private JButton sairButtton;

    private ArrayList<Item> itensCarrinho = new ArrayList<>();

    public TelaVendas() {

        carrinhoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                irParaOutraTela();
            }
        });

        addcarrinhoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarItens();
            }
        });

        sairButtton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                framevendas.getTopLevelAncestor().setVisible(false);
            }
        });
    }

    private void salvarItens() {
        if (luvaBox.isSelected()) {
            itensCarrinho.add(new Item("Luva", 65, (Integer) quantLuva.getValue()));
        }
        if (boneBox.isSelected()) {
            itensCarrinho.add(new Item("Bone", 50, (Integer) quantBone.getValue()));
        }
        if (botaBox.isSelected()) {
            itensCarrinho.add(new Item("Bota", 100, (Integer) quantBota.getValue()));
        }
        if (municaobox.isSelected()) {
            itensCarrinho.add(new Item("Municao", 85, (Integer) quantMunicao.getValue()));
        }
        if (rifleBox.isSelected()) {
            itensCarrinho.add(new Item("Rifle", 450, (Integer) quantRifle.getValue()));
        }
        if (camuflagemBox.isSelected()) {
            itensCarrinho.add(new Item("Camuflagem", 235, (Integer) quantCamuflagem.getValue()));
        }
        mostrarResumoCarrinho();
    }

    private void mostrarResumoCarrinho() {
        StringBuilder resumo = new StringBuilder();
        double valorTotal = 0;

        for (Item item : itensCarrinho) {
            resumo.append(item.getQuantidade())
                    .append("x ")
                    .append(item.getNome())
                    .append(": R$")
                    .append(item.getValor() * item.getQuantidade())
                    .append("\n");
            valorTotal += item.getValor() * item.getQuantidade();
        }

        resumo.append("Valor total: R$").append(valorTotal);

        int confirm = JOptionPane.showConfirmDialog(null, resumo.toString(), "Confirmar Carrinho", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            irParaOutraTela();
        }
    }
    private void irParaOutraTela(){
        TelaCarrinho telaCarrinho = new TelaCarrinho(itensCarrinho);
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(framevendas);
        frame.setContentPane(telaCarrinho.panelCarrinho);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();
    };

    public static void main(String[] args) {
        JFrame frame = new JFrame("Vendas");
        frame.setContentPane(new TelaVendas().framevendas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(650,700);
    }
}