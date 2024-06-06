package br.com.fiap.response;

import lombok.Builder;

@Builder
public record CadastroResponse(
		int idCadastro,
		String nomeCompleto,
		String telefone,
		String emailCad,
		String senhaCad) {

}
