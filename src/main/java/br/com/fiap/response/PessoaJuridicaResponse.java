package br.com.fiap.response;

import java.sql.Date;

import br.com.fiap.model.Cadastro;
import lombok.Builder;

@Builder
public record PessoaJuridicaResponse(
		int idPessoaJuridica,
		String cnpj,
		double quantLixoColetada,
		Date dataColeta,
		Cadastro cadastro) {

}
