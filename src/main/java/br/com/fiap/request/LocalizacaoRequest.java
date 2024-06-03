package br.com.fiap.request;

import org.springframework.hateoas.Link;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LocalizacaoRequest(
		int idLocalizacao,
		@Size(min = 1, max = 100)
		String nomePraia,
		@NotNull
        @NotBlank
        @Size(min = 1, max = 70)
		String cidade,
		@NotNull
        @NotBlank
        @Size(min = 1, max = 100)
		String estado,
		@NotNull
        @NotBlank
        @Size(min = 1, max = 100)
		String pais,
		@NotNull
        @NotBlank
        @Size(min = 1, max = 100)
		String latitudeAtual,
		@NotNull
        @NotBlank
        @Size(min = 1, max = 100)
		String longitudeAtual,
		Link link) {

}
