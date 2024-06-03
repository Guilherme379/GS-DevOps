package br.com.fiap.response;

public record CadastroResponse(
		int idCadastro,
		String nomeCompleto,
		long telefone,
		String emailCad,
		String senhaCad) {

}
