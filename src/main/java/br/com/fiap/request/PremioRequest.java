package br.com.fiap.request;

import org.springframework.hateoas.Link;

import br.com.fiap.model.PessoaFisica;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PremioRequest(
		int idPremio,
		@NotNull
        @NotBlank
        @Size(min = 1, max = 100)
		String descricaoPremio,
		@NotNull
        @NotBlank
        @Size(min = 1, max = 70)
		String produto,
		@NotNull
        @NotBlank
        @Size(min = 1, max = 80)
		String sku,
		@NotNull
		@NotBlank
		@Min(1)
		@Max(99999)
		int xpPremio,
		@Valid
        @NotNull
		PessoaFisica pessoaFisica,
		Link link) {

}
