package br.com.fiap.response;

public record PremioResponse(
		int idPremio,
		String descricaoPremio,
		String produto,
		String sku,
		int xpPremio,
		PessoaFisicaResponse pessoaFisicaResponse) {

}
