package br.com.fiap.response;

import java.security.Timestamp;

import br.com.fiap.model.Cadastro;
import lombok.Builder;

@Builder
public record LoginResponse(
		int idLogin,
		String emailLog,
		String senhaLog,
		Timestamp dataHoraLogin,
        Timestamp dataHoraLogout,
		Cadastro cadastro) {

}
