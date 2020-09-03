package br.com.softblue.sbpay;

import javax.validation.constraints.Pattern;

public class DadosCartao {

	@Pattern(regexp = "\\d{16}", message = "O Numero do cartao e invalido")
	private String numCartao;
	
	public String getNumCartao() {
		return numCartao;
	}
	
	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}
}
