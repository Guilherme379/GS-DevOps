package br.com.fiap.response;

public record RankingResponse(
		int idRanking,
		int posicao,
		PessoaJuridicaResponse pessoaJuridicaResponse) {

}
