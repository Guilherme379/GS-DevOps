package br.com.fiap.request;

import java.sql.Date;

import org.springframework.hateoas.Link;

import br.com.fiap.model.Cadastro;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record PessoaJuridicaRequest(
		int idPessoaJuridica,
		@NotNull
        @NotBlank
        @Size(min = 14, max = 14)
		long cnpj,
		@Size(min = 5, max = 15)
		double quantLixoColetada,
		@PastOrPresent
		Date dataColeta,
		@Valid
        @NotNull
		Cadastro cadastro,
		Link link) {

}
