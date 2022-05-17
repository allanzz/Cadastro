package br.com.allan;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import br.com.allan.modelos.Produto;

public class Saida implements Movimentacao{
	private int id;
	private DateTime data;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DateTime getData() {
		return data;
	}

	public void setData(DateTime data) {
		this.data = data;
	}

	public Produto movimentar(Produto produto,int quantidade) {
		try {
			if (quantidade>0) {
				produto.setEstoque(produto.getEstoque() - quantidade);								
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return produto;
	}
	

	public Produto movimentar(Produto produto, int quantidade, double custo) {
		// TODO Auto-generated method stub
		return null;
	}

}
