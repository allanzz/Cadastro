package br.com.allan.telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.allan.Movimentacao;
import br.com.allan.TabelaProdutosModel;
import br.com.allan.dao.Dao;
import br.com.allan.modelos.Produto;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TelaProdutosFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textCusto;
	private JTextField textVenda;
	private JTable table;
	TabelaProdutosModel modelo;
	TelaMovimentacaoDialog telaMovimentacao = new TelaMovimentacaoDialog();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaProdutosFrame frame = new TelaProdutosFrame();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaProdutosFrame() {
		setTitle("Produtos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setInheritsPopupMenu(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setInheritsPopupMenu(true);
		scrollPane.setBounds(10, 37, 764, 219);
		contentPane.add(scrollPane);

		table = new JTable();

		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		table.setRowHeight(24);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		modelo = new TabelaProdutosModel();
		table.setModel(modelo);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBounds(10, 267, 764, 283);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 24, 101, 35);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Custo:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 70, 101, 35);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Venda:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(10, 116, 101, 35);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Descri\u00E7\u00E3o");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(10, 162, 101, 35);
		panel.add(lblNewLabel_3);

		textNome = new JTextField();
		textNome.setBounds(121, 24, 633, 35);
		panel.add(textNome);
		textNome.setColumns(10);

		textCusto = new JTextField();
		textCusto.setEditable(false);
		textCusto.setBounds(121, 77, 86, 35);
		panel.add(textCusto);
		textCusto.setColumns(10);

		textVenda = new JTextField();
		textVenda.setBounds(121, 123, 86, 35);
		panel.add(textVenda);
		textVenda.setColumns(10);
		textVenda.setText("0");

		final JTextArea textAreaDescricao = new JTextArea();
		textAreaDescricao.setBounds(121, 167, 633, 55);
		panel.add(textAreaDescricao);

		

		// CRIA O BOTÃO REMOVER
		JButton btnNewButton = new JButton("Remover");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				Dao dao = new Dao();
				Produto produto = modelo.getProduto(linha);
				System.out.println(produto);
				if (dao.remove(produto)) {
					modelo.removeProduto(linha);
					modelo.fireTableRowsDeleted(linha, linha);
					table.updateUI();
				} else {
					JOptionPane.showMessageDialog(null, "Não foi possível remover o produto", "", 1);
				}

			}
		});
		btnNewButton.setBounds(322, 233, 130, 39);
		panel.add(btnNewButton);

		// CRIA BOTÃO ATUALIZAR
		JButton btnNewButton_1 = new JButton("Atualizar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					Produto produto = modelo.getProduto(table.getSelectedRow());
					produto.setNome(textNome.getText());
					produto.setCusto(Double.parseDouble(textCusto.getText()));
					produto.setPrecoVenda(Double.parseDouble(textVenda.getText()));
					produto.setDescricao(textAreaDescricao.getText());
					Dao dao = new Dao();
					dao.atualizar(produto);
					modelo.fireTableDataChanged();
					table.updateUI();
				} else {
					JOptionPane.showMessageDialog(table, "Selecione um item para ser atualizado");
				}

			}
		});
		btnNewButton_1.setBounds(182, 233, 130, 39);
		panel.add(btnNewButton_1);

		// CRIA BOTÃO INSERIR
		JButton btnNewButton_2 = new JButton("Inserir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (verificarCampoNome(textNome.getText())&&verificarCampoNome(textVenda.getText())) {
						Produto produto = new Produto();
						produto.setNome(textNome.getText());
						produto.setPrecoVenda(Double.parseDouble(textVenda.getText()));
						produto.setDescricao(textAreaDescricao.getText());
						Dao dao = new Dao();
						dao.salvar(produto);
						modelo.adicionaProduto(produto);
						modelo.fireTableDataChanged();
						table.updateUI();
					}else {
						JOptionPane.showMessageDialog(null,"Preencha todos os campos corretamente");
					}

				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_2.setBounds(33, 233, 130, 39);
		panel.add(btnNewButton_2);

		// ACÃO BOTÃO MOVIMENTAR
		JButton btnNewButton_3 = new JButton("Movimentar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaMovimentacao.setVisible(rootPaneCheckingEnabled);
				Produto produto = modelo.getProduto(table.getSelectedRow());
				String tipoMovimentacao = telaMovimentacao.getTipoMovimentacao();
				int quantidade = telaMovimentacao.getQuantidade();
				double custo = telaMovimentacao.getCusto();
				try {
					Class<?> classe = Class.forName("br.com.allan." + tipoMovimentacao);
					Movimentacao movimentacao = (Movimentacao) classe.getDeclaredConstructor(null).newInstance();
					produto = movimentacao.movimentar(produto, quantidade,custo);
					Dao dao = new Dao();
					dao.atualizar(produto);
					System.out.println(produto.toString());
					modelo.fireTableDataChanged();
					table.updateUI();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}

			
		});
		btnNewButton_3.setBounds(462, 233, 130, 39);
		panel.add(btnNewButton_3);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				textNome.setText((String) table.getValueAt(table.getSelectedRow(), 0));
				textCusto.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				textVenda.setText((String) table.getValueAt(table.getSelectedRow(), 3).toString());
				textAreaDescricao.setText((String) table.getValueAt(table.getSelectedRow(), 4));
			}
		});

	}

	public boolean verificarCampoNome(String campo) {
		if (campo==null || campo.trim().equals("")) {
			return false;
		} else {
			return true;
		}

	}
}
