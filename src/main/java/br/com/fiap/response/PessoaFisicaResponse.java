package br.com.fiap.response;

import java.sql.Date;

public record PessoaFisicaResponse(
		int idPessoaFisica,
		Date dataNasc,
		long cpf,
		int xp,
		CadastroResponse cadastroResponse) {

}
