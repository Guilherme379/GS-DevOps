package br.com.fiap.request;

import org.springframework.hateoas.Link;

import br.com.fiap.model.Localizacao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReporteRequest(
		int idReporte,
		@NotNull
        @NotBlank
        @Size(min = 5, max = 15)
		String quantidade,
		@NotNull
        @NotBlank
        @Size(min = 5, max = 100)
		String descricaoReporte,
		@Valid
        @NotNull
		Localizacao localizacao,
		Link link) {

}
