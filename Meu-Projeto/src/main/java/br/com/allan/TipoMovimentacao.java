package br.com.allan;

public enum TipoMovimentacao {
	ENTRADA("Entrada"),SAIDA("Saida");

	public String valor;
	TipoMovimentacao(String string) {
		valor = string;
	}

}
