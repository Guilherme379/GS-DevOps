package br.com.fiap.request;

import org.springframework.hateoas.Link;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CadastroRequest(
		int idCadastro,
		@NotNull
        @NotBlank
        @Size(min = 3, max = 100)
		String nomeCompleto,
		@NotNull
        @NotBlank
        @Size(min = 11, max = 11)
		String telefone,
		@NotNull
        @NotBlank
        @Size(min = 13, max = 60)
		String emailCad,
		@NotNull
        @NotBlank
        @Size(min = 6, max = 50)
		String senhaCad,
		Link link) {

}
