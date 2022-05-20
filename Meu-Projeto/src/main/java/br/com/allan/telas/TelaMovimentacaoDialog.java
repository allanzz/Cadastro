package br.com.allan.telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.allan.TipoMovimentacao;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalityType;
import java.awt.Font;

public class TelaMovimentacaoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textQtd;
	private JTextField textCusto;
	private JComboBox comboBox;
	private int qtd;
	private double custo;
	boolean confirm=false;
	TipoMovimentacao tipoMov;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaMovimentacaoDialog dialog = new TelaMovimentacaoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setModalityType(ModalityType.APPLICATION_MODAL);
			dialog.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaMovimentacaoDialog() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tipo de Movimenta\u00E7\u00E3o:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 209, 33);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Quantidade:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 55, 148, 33);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Custo:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(10, 99, 148, 33);
		contentPanel.add(lblNewLabel_2);
		
		textQtd = new JTextField();
		textQtd.setBounds(168, 61, 86, 27);
		contentPanel.add(textQtd);
		textQtd.setColumns(10);
		
		textCusto = new JTextField();
		textCusto.setBounds(168, 99, 86, 33);
		contentPanel.add(textCusto);
		textCusto.setColumns(10);
		
		comboBox = new JComboBox(TipoMovimentacao.values());
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setBounds(248, 18, 148, 26);
		contentPanel.add(comboBox);
		//AÇÃO BOTÃO ALTERAR
		JButton btnNewButton = new JButton("Alterar");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qtd = Integer.parseInt(textQtd.getText());
				tipoMov =(TipoMovimentacao) comboBox.getSelectedItem();
				if(tipoMov.getValue().equals("Entrada")) {
					custo = Double.parseDouble(textCusto.getText());
				}
				textCusto.setText("");
				textQtd.setText("");
				setVisible(false);
				confirm = true;
				
			}
		});
		btnNewButton.setBounds(10, 157, 110, 33);
		contentPanel.add(btnNewButton);
		
		//AÇÃO BOTÃO CANCELAR
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textCusto.setText("");
				textQtd.setText("");
				setVisible(false);	
				confirm=false;
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(144, 159, 110, 33);
		contentPanel.add(btnNewButton_1);
		
	}
	public int getQuantidade() {
		return qtd;
	}
	public double getCusto() {
		return custo;
	}
	public String getTipoMovimentacao(){
		return tipoMov.getValue();		
	}
}
