package br.com.fiap.response;

import java.sql.Date;

public record PessoaJuridicaResponse(
		int idPessoaJuridica,
		long cnpj,
		double quantLixoColetada,
		Date dataColeta,
		CadastroResponse cadastroResponse) {

}
