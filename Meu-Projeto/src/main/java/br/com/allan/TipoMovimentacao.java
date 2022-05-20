package br.com.allan;

public enum TipoMovimentacao {
	ENTRADA("Entrada"),SAIDA("Saida");

	private String valor;
	TipoMovimentacao(String string) {
		valor = string;
	}
	public String getValue(){
        return valor;
    }

}
