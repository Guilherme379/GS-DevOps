package br.com.fiap.response;

import java.sql.Date;

import br.com.fiap.model.Cadastro;
import lombok.Builder;

@Builder
public record PessoaFisicaResponse(
		int idPessoaFisica,
		Date dataNasc,
		String cpf,
		int xp,
		Cadastro cadastro) {

}
