package br.com.fiap.response;

import java.security.Timestamp;

public record LoginResponse(
		int idLogin,
		String emailLog,
		String senhaLog,
		Timestamp dataHoraLogin,
        Timestamp dataHoraLogout,
		CadastroResponse cadastroResponse) {

}
