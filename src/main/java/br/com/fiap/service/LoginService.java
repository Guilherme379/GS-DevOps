package br.com.fiap.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiap.model.Cadastro;
import br.com.fiap.model.Login;
import br.com.fiap.repository.CadastroRepository;
import br.com.fiap.repository.LoginRepository;
import br.com.fiap.request.LoginRequest;
import jakarta.validation.ValidationException;

@Service
public class LoginService {

	private LoginRepository loginRepository;
	private CadastroService cadastroService;
	private CadastroRepository cadastroRepository;

	public LoginService(LoginRepository loginRepository, CadastroService cadastroService, CadastroRepository cadastroRepository) {
		this.loginRepository = loginRepository;
		this.cadastroService = cadastroService;
		this.cadastroRepository = cadastroRepository;
	}

	public Login buscarLogin(int idLogin) {
		Optional<Login> login = loginRepository.findById(idLogin);
		return login.get();
	}

	public Login gravarLogin(LoginRequest loginRequest) {
		Cadastro cadastro = cadastroService.buscarCadastro(loginRequest.cadastro().getIdCadastro());

		Login login = new Login();
		login.setCadastro(cadastro);
		login.setEmailLog(loginRequest.emailLog());
		login.setSenhaLog(loginRequest.senhaLog());
		login.setDataHoraLogin(loginRequest.dataHoraLogin());
		login.setDataHoraLogout(loginRequest.dataHoraLogout());
		
		return loginRepository.save(login);
	}

	public Login atualizarLogin(LoginRequest loginRequest, int idLogin) {
		Login login = loginRepository.findById(idLogin).get();
		login.setEmailLog(loginRequest.emailLog());
		login.setSenhaLog(loginRequest.senhaLog());
		login.setDataHoraLogin(loginRequest.dataHoraLogin());
		login.setDataHoraLogout(loginRequest.dataHoraLogout());
		return loginRepository.save(login);
	}

	public String deletarLogin(int idLogin) {
		Login login = loginRepository.findByIdLogin(idLogin);
		loginRepository.delete(login);
		return "Login excluído";
	}
	
	//Método que confere a existência do email cadastrado para realização do login
	public String conferirEmailCadastrado(int idCadastro, int idLogin) {
		Optional<Cadastro> cadastro = cadastroRepository.findById(idCadastro);
		Optional<Login> login = loginRepository.findById(idLogin);
		if (cadastro.isPresent() && cadastro.get().getEmailCad() == login.get().getEmailLog()) {
			return "Email cadastrado, acesso liberado";
		} else {
			throw new ValidationException("Login não pôde ser realizado");
		}
	}

}
