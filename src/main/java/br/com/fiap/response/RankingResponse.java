package br.com.fiap.response;

import br.com.fiap.model.PessoaJuridica;
import lombok.Builder;

@Builder
public record RankingResponse(
		int idRanking,
		int posicao,
		PessoaJuridica pessoaJuridica) {

}
