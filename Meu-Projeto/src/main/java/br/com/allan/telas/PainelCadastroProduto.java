package br.com.allan.telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.allan.dao.Dao;
import br.com.allan.modelos.Produto;
import java.awt.Cursor;

public class PainelCadastroProduto extends JPanel {
	private JTextField txtProduto;
	private JTextField txtCusto;
	private JTextField txtVenda;

	/**
	 * Create the panel.
	 */
	public PainelCadastroProduto() {
		setForeground(new Color(255, 127, 80));
		setBackground(new Color(255, 255, 0));
		setLayout(null);
		
		JLabel lblNome = new JLabel("Produto:");
		lblNome.setForeground(new Color(255, 153, 51));
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNome.setBackground(new Color(0, 0, 0));
		lblNome.setBounds(42, 84, 89, 14);
		add(lblNome);
		
		JLabel lblCusto = new JLabel("Custo:");
		lblCusto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCusto.setForeground(new Color(255, 153, 0));
		lblCusto.setBounds(42, 139, 91, 14);
		add(lblCusto);
		
		JLabel lblVenda = new JLabel("Venda:");
		lblVenda.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblVenda.setForeground(new Color(255, 153, 0));
		lblVenda.setBounds(44, 180, 89, 14);
		add(lblVenda);
		
		txtProduto = new JTextField();
		txtProduto.setForeground(new Color(255, 153, 0));
		txtProduto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtProduto.setBounds(124, 76, 521, 31);
		add(txtProduto);
		txtProduto.setColumns(10);
		
		txtCusto = new JTextField();
		txtCusto.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		txtCusto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCusto.setForeground(new Color(255, 153, 0));
		txtCusto.setBounds(124, 131, 89, 31);
		add(txtCusto);
		txtCusto.setColumns(10);
		
		txtVenda = new JTextField();
		txtVenda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtVenda.setForeground(new Color(255, 153, 0));
		txtVenda.setBounds(124, 173, 89, 30);
		add(txtVenda);
		txtVenda.setColumns(10);
		
		final JTextArea textAreaDescricao = new JTextArea();
		textAreaDescricao.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textAreaDescricao.setText("Descri\u00E7\u00E3o do produto...");
		textAreaDescricao.setForeground(new Color(255, 153, 0));
		textAreaDescricao.setBounds(42, 246, 603, 132);
		add(textAreaDescricao);
		
		JLabel lblNewLabel_3 = new JLabel("Descri\u00E7\u00E3o: ");
		lblNewLabel_3.setForeground(new Color(255, 153, 0));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(42, 221, 91, 14);
		add(lblNewLabel_3);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = new Produto();
				produto.setNome(txtProduto.getText());
				produto.setCusto(Double.parseDouble(txtCusto.getText()));
				produto.setPrecoVenda(Double.parseDouble(txtVenda.getText()));
				produto.setDescricao(textAreaDescricao.getText());
				Dao dao = new Dao();
				dao.salvar(produto);
			}
		});
		btnCadastrar.setForeground(new Color(255, 153, 0));
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCadastrar.setBackground(Color.WHITE);
		btnCadastrar.setBounds(42, 413, 115, 23);
		add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtVenda.setText("");
				txtCusto.setText("");
				txtProduto.setText("");
				textAreaDescricao.setText("");
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLimpar.setForeground(new Color(255, 153, 0));
		btnLimpar.setBackground(Color.WHITE);
		btnLimpar.setBounds(179, 413, 115, 23);
		add(btnLimpar);
		
		JLabel lblTitulo = new JLabel("Cadastro de Produto");
		lblTitulo.setForeground(new Color(255, 153, 0));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(258, 30, 187, 31);
		add(lblTitulo);

	}
}
