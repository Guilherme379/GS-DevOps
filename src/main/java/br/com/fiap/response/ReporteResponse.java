package br.com.fiap.response;

import java.sql.Timestamp;

import br.com.fiap.model.Localizacao;
import lombok.Builder;

@Builder
public record ReporteResponse(
		int idReporte,
		int quantidade,
		String descricaoReporte,
		Timestamp dataHoraReporte,
		Localizacao localizacao) {

}
