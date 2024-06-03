package br.com.fiap.response;

public record ReporteResponse(
		int idReporte,
		String quantidade,
		String descricaoReporte,
		LocalizacaoResponse localizacaoResponse) {

}
