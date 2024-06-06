package br.com.fiap.request;

import java.sql.Date;

import org.springframework.hateoas.Link;

import br.com.fiap.model.Cadastro;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
		String cpf,
		@NotNull
        @NotBlank
        @Min(1)
        @Max(99999)
		int xp,
		@Valid
        @NotNull
		Cadastro cadastro,
		Link link) {

}
