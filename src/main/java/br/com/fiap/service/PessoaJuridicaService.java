package br.com.fiap.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import br.com.fiap.controller.PessoaJuridicaController;
import br.com.fiap.model.Cadastro;
import br.com.fiap.model.Login;
import br.com.fiap.model.PessoaJuridica;
import br.com.fiap.repository.PessoaJuridicaRepository;
import br.com.fiap.request.LoginRequest;
import br.com.fiap.request.PessoaJuridicaRequest;

@Service
public class PessoaJuridicaService {

	private PessoaJuridicaRepository pessoaJuridicaRepository;
	private CadastroService cadastroService;
	private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 8, Sort.by("nomeCompleto").ascending());
	
	public PessoaJuridicaService(PessoaJuridicaRepository pessoaJuridicaRepository, CadastroService cadastroService) {
		this.pessoaJuridicaRepository = pessoaJuridicaRepository;
		this.cadastroService = cadastroService;
	}

	public PessoaJuridica buscarPessoaJuridica(int idPessoaJuridica) {
		Optional<PessoaJuridica> pessoaJuridica = pessoaJuridicaRepository.findById(idPessoaJuridica);
		return pessoaJuridica.get();
	}
	
	public PessoaJuridica gravarPessoaJuridica(PessoaJuridicaRequest pessoaJuridicaRequest) {
		Cadastro cadastro = cadastroService.buscarCadastro(pessoaJuridicaRequest.cadastro().getIdCadastro());

		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setCadastro(cadastro);
		pessoaJuridica.setCnpj(pessoaJuridicaRequest.cnpj());
		pessoaJuridica.setQuantLixoColetada(pessoaJuridicaRequest.quantLixoColetada());
		pessoaJuridica.setDataColeta(pessoaJuridicaRequest.dataColeta());

		return pessoaJuridicaRepository.save(pessoaJuridica);
	}
	
	public PessoaJuridica atualizarPessoaJuridica(PessoaJuridicaRequest pessoaJuridicaRequest, int idPessoaJuridica) {
		PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findById(idPessoaJuridica).get();
		pessoaJuridica.setCnpj(pessoaJuridicaRequest.cnpj());
		pessoaJuridica.setQuantLixoColetada(pessoaJuridicaRequest.quantLixoColetada());
		pessoaJuridica.setDataColeta(pessoaJuridicaRequest.dataColeta());
		return pessoaJuridicaRepository.save(pessoaJuridica);
	}
	
	public String deletarPessoaJuridica(int idPessoaJuridica) {
		PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findByIdPessoaJuridica(idPessoaJuridica);
		pessoaJuridicaRepository.delete(pessoaJuridica);
		return "Pessoa juridica excluída";
	}
	/*
	private PessoaJuridicaRequest toDTO(PessoaJuridica pessoaJuridica, boolean self) {
		Link link;
		if (self) {
			link = linkTo(methodOn(PessoaJuridicaController.class).buscarPessoaFisicaPorId(pessoaJuridica.getIdPessoaJuridica())).withSelfRel();
		} else {
			link = linkTo(methodOn(PessoaJuridicaController.class).buscarPessoasJuridicas()).withRel("Lista de pessoas juridicas");
		}
		return new PessoaJuridicaRequest(
				pessoaJuridica.getIdPessoaJuridica(),
				pessoaJuridica.getCnpj(),
				pessoaJuridica.getQuantLixoColetada(),
				pessoaJuridica.getDataColeta(),
				pessoaJuridica.getCadastro(),
				link);
	}*/
}
