package br.com.fiap.request;

import org.springframework.hateoas.Link;

import br.com.fiap.model.PessoaJuridica;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RankingRequest(
		int idRanking,
		@NotNull
        @NotBlank
        @Size(min = 1, max = 70)
		int posicao,
		@Valid
        @NotNull
		PessoaJuridica pessoaJuridica,
		Link link) {

}
