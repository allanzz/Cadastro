package br.com.allan;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import br.com.allan.modelos.Produto;

public class Entrada implements Movimentacao {
	private int id;
	private LocalDate data;
	
	public Entrada() {
		data = LocalDate.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalDate getData() {
		return data;
	}

	public Produto movimentar(Produto produto,int quantidade,double custo) {
		try {
			if (quantidade>0) {
				double totalAnterior = produto.getCustoMedio()*produto.getEstoque();
				double totalEntrada = quantidade*custo;	
				produto.setEstoque(produto.getEstoque() + quantidade);
				produto.setCusto(custo);
				double custoMedio = (totalEntrada+totalAnterior)/produto.getEstoque();
				NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
				nf.setMaximumFractionDigits(2);
				custoMedio = Double.parseDouble(nf.format(custoMedio));
				produto.setCustoMedio(custoMedio);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return produto;
	}
	public Produto movimentar(Produto produto,int quantidade) {
		// TODO Auto-generated method stub
		return produto;
	}
	@Override
	public String toString() {
		return "Entrada [id=" + id + ", data=" + data + "]";
	}

	

	

}
