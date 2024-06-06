package br.com.fiap.response;

import lombok.Builder;

@Builder
public record LocalizacaoResponse(
		int idLocalizacao,
		String nomePraia,
		String cidade,
		String estado,
		String pais,
		String latitudeAtual,
		String longitudeAtual) {

}
