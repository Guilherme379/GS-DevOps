package br.com.fiap.response;

public record LocalizacaoResponse(
		int idLocalizacao,
		String nomePraia,
		String cidade,
		String estado,
		String pais,
		String latitudeAtual,
		String longitudeAtual) {

}
