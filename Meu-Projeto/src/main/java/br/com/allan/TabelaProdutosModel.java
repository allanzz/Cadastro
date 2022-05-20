package br.com.allan;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import br.com.allan.dao.Dao;
import br.com.allan.modelos.Produto;

public class TabelaProdutosModel extends AbstractTableModel {
	
	private Dao dao = new Dao();
	private List<Produto> produtos  = dao.buscarTodos(Produto.class);	
	private String[] colunas = new String[] {"Nome","Custo","Custo Médio","Venda","Descrição","Estoque"};
	
	
	
	public int getRowCount() {
		// TODO Auto-generated method stub
		return produtos.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunas.length;
	}

	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return colunas[columnIndex];
	}

	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Produto produto = produtos.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return produto.getNome();			
		case 1:
			return retornaValorFormatado(produto.getCusto());
		case 2:
			return retornaValorFormatado(produto.getCustoMedio());
		case 3:
			return retornaValorFormatado(produto.getPrecoVenda());
		case 4:
			return produto.getDescricao();
		case 5:
			return produto.getEstoque();
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}		
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Produto produto = produtos.get(rowIndex);
		switch (columnIndex) {
		case 0:
			produto.setNome(aValue.toString());
			break;
		case 1:
			produto.setCusto(Double.parseDouble(aValue.toString()));
			break;
		case 2:
			produto.setPrecoVenda(Double.parseDouble(aValue.toString()));
			break;
		case 3:
			produto.setDescricao(aValue.toString());
			break;
		case 4:
			produto.setEstoque(Integer.parseInt(aValue.toString()));
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}
	public void removeProduto(int indiceLinha) {
	    // Remove o registro.
	    produtos.remove(indiceLinha);
	 
	    // Notifica a mudança.
	    fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	public Produto getProduto(int indiceLinha) {
	    return produtos.get(indiceLinha);
	}
	public void adicionaProduto(Produto produto) {
		produtos.add(produto);		
		fireTableDataChanged();
	}

	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
		
	}

	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}
	public String retornaValorFormatado(double valor) {
		NumberFormat formato= NumberFormat.getInstance(new Locale("en", "US"));
		formato.setMaximumFractionDigits(2);
		formato.setMinimumFractionDigits(2);
		String valorFormatado = formato.format(valor);
		return valorFormatado;
	}

}
