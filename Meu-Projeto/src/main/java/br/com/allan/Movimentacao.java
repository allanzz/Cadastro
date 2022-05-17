package br.com.allan;

import br.com.allan.modelos.Produto;

public interface Movimentacao {
	public Produto movimentar(Produto produto,int quantidadae);
	public Produto movimentar(Produto produto,int quantidade,double custo);

}
