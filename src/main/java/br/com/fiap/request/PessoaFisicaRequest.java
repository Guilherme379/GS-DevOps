package br.com.fiap.request;

import java.sql.Date;

import org.springframework.hateoas.Link;

import br.com.fiap.model.Cadastro;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PessoaFisicaRequest(
		int idPessoaFisica,
		@NotNull
        @NotBlank
		Date dataNasc,
		@NotNull
        @NotBlank
        @Size(min = 11, max = 11)
		long cpf,
		@NotNull
        @NotBlank
        @Size(min = 1, max = 99999)
		int xp,
		@Valid
        @NotNull
		Cadastro cadastro,
		Link link) {

}
