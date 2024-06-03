package br.com.fiap.service;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import br.com.fiap.controller.PessoaFisicaController;
import br.com.fiap.model.Cadastro;
import br.com.fiap.model.PessoaFisica;
import br.com.fiap.repository.PessoaFisicaRepository;
import br.com.fiap.request.LoginRequest;
import br.com.fiap.request.PessoaFisicaRequest;

@Service
public class PessoaFisicaService {

	private PessoaFisicaRepository pessoaFisicaRepository;
	private CadastroService cadastroService;
	private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 8, Sort.by("nomeCompleto").ascending());
	
	public PessoaFisicaService(PessoaFisicaRepository pessoaFisicaRepository, CadastroService cadastroService) {
		this.pessoaFisicaRepository = pessoaFisicaRepository;
		this.cadastroService = cadastroService;
	}

	public PessoaFisica buscarPessoaFisica(int idPessoaFisica) {
		Optional<PessoaFisica> pessoaFisica = pessoaFisicaRepository.findById(idPessoaFisica);
		return pessoaFisica.get();
	}
	
	public PessoaFisica gravarPessoaFisica(PessoaFisicaRequest pessoaFisicaRequest) {
		Cadastro cadastro = cadastroService.buscarCadastro(pessoaFisicaRequest.cadastro().getIdCadastro());
		
		PessoaFisica pessoaFisica = new PessoaFisica();
		pessoaFisica.setCadastro(cadastro);
		pessoaFisica.setDataNasc(pessoaFisicaRequest.dataNasc());
		pessoaFisica.setCpf(pessoaFisicaRequest.cpf());
		pessoaFisica.setXp(pessoaFisicaRequest.xp());

		return pessoaFisicaRepository.save(pessoaFisica);
	}
	
	public PessoaFisica atualizarPessoaFisica(PessoaFisicaRequest pessoaFisicaRequest, int idPessoaFisica) {
		PessoaFisica pessoaFisica = pessoaFisicaRepository.findById(idPessoaFisica).get();
		pessoaFisica.setDataNasc(pessoaFisicaRequest.dataNasc());
		pessoaFisica.setCpf(pessoaFisicaRequest.cpf());
		pessoaFisica.setXp(pessoaFisicaRequest.xp());
		return pessoaFisicaRepository.save(pessoaFisica);
	}
	
	public String deletarPessoaFisica(int idPessoaFisica) {
		PessoaFisica pessoaFisica = pessoaFisicaRepository.findByIdPessoaFisica(idPessoaFisica);
		pessoaFisicaRepository.delete(pessoaFisica);
		return "Pessoa fisica excluída";
	}
	/*
	private PessoaFisicaRequest toDTO(PessoaFisica pessoaFisica, boolean self) {
		Link link;
		if (self) {
			link = linkTo(methodOn(PessoaFisicaController.class).buscarPessoaFisicaPorId(pessoaFisica.getIdPessoaFisica())).withSelfRel();
		} else {
			link = linkTo(methodOn(PessoaFisicaController.class).buscarPessoasFisicas()).withRel("Lista de pessoas fisicas");
		}
		return new PessoaFisicaRequest(
				pessoaFisica.getIdPessoaFisica(),
				pessoaFisica.getDataNasc(),
				pessoaFisica.getCpf(),
				pessoaFisica.getXp(),
				pessoaFisica.getCadastro(),
				link);
	}*/
	
}
