package br.com.softblue.sbpay;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SBPayService {
	
	private static final String AUTH_TOKEN = "r2d2";

	@PostMapping(path = "/pay", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentResponse> pagar(
			@RequestHeader("Token") String token,
			@Valid @RequestBody DadosCartao dadosCartao,
			Errors errors){
		
		if (!AUTH_TOKEN.equals(token)) {
			return ResponseEntity.badRequest().body(new PaymentResponse("Token invalido"));
		}
		
		if (errors.hasErrors()) {
			return ResponseEntity.ok().body(new PaymentResponse(StatusPagamento.CartaoInvalido));
		}
		
		String numCartao = dadosCartao.getNumCartao();
		
		StatusPagamento status = numCartao.startsWith("1111") ? StatusPagamento.Autorizado : StatusPagamento.NaoAutorizado;
		return ResponseEntity.ok().body(new PaymentResponse(status));
		
	}
}
