package br.com.fiap.response;

import br.com.fiap.model.PessoaFisica;
import lombok.Builder;

@Builder
public record PremioResponse(
		int idPremio,
		String descricaoPremio,
		String produto,
		String sku,
		int xpPremio,
		PessoaFisica pessoaFisica) {

}
