package br.com.fiap.request;

import java.sql.Timestamp;

import org.springframework.hateoas.Link;

import br.com.fiap.model.Localizacao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReporteRequest(
		int idReporte,
		@NotNull
        @NotBlank
        @Min(1)
    	@Max(9999)
		int quantidade,
		@NotNull
        @NotBlank
        @Size(min = 5, max = 100)
		String descricaoReporte,
		Timestamp dataHoraReporte,
		@Valid
        @NotNull
		Localizacao localizacao,
		Link link) {

}
