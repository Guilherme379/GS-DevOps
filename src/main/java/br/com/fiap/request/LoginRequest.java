package br.com.fiap.request;

import java.security.Timestamp;

import org.springframework.hateoas.Link;

import br.com.fiap.model.Cadastro;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginRequest(
		int idLogin,
		@NotNull
        @NotBlank
        @Size(min = 13, max = 60)
		String emailLog,
		@NotNull
        @NotBlank
        @Size(min = 6, max = 50)
		String senhaLog,
		@Valid
        @NotNull
        Timestamp dataHoraLogin,
        Timestamp dataHoraLogout,
		Cadastro cadastro,
		Link link) {

}
