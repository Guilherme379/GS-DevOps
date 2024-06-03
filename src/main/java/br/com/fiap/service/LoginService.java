package br.com.fiap.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import br.com.fiap.controller.LoginController;
import br.com.fiap.exceptions.ValidationExceptionHandler;
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
	private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 8, Sort.by("nomeCompleto").ascending());


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
	
	//Método confere a existência do email cadastrado para realização do login
	public String conferirEmailCadastrado(int idCadastro, int idLogin) {
		Optional<Cadastro> cadastro = cadastroRepository.findById(idCadastro);
		Optional<Login> login = loginRepository.findById(idLogin);
		if (cadastro.isPresent() && cadastro.get().getEmailCad() == login.get().getEmailLog()) {
			return "Email cadastrado";
		} else {
			throw new ValidationException("Login não pôde ser realizado");
		}
	}
	
	
	/*
	private LoginRequest toDTO(Login login, boolean self) {
		Link link;
		if (self) {
			link = linkTo(methodOn(LoginController.class).buscarLoginPorId(login.getIdLogin())).withSelfRel();
		} else {
			link = linkTo(methodOn(LoginController.class).buscarLogins()).withRel("Lista de Logins");
		}
		return new LoginRequest(login.getIdLogin(), login.getEmailLog(), login.getSenhaLog(), login.getDataHoraLogin(), login.getDataHoraLogout(), login.getCadastro(),
				link);
	}*/
}
