package br.com.fiap.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import br.com.fiap.controller.CadastroController;
import br.com.fiap.model.Cadastro;
import br.com.fiap.repository.CadastroRepository;
import br.com.fiap.request.CadastroRequest;
import br.com.fiap.response.CadastroResponse;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CadastroService {

	private CadastroRepository cadastroRepository;
	private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 8, Sort.by("nomeCompleto").ascending());

	public CadastroService(CadastroRepository cadastroRepository) {
		this.cadastroRepository = cadastroRepository;
	}
	
	public Page<CadastroResponse> buscarCadastros() {
		return cadastroRepository.findAll(paginacaoPersonalizada).map(cadastro -> toDTO (cadastro, true));
	}

	public CadastroResponse buscarCadastroResponse(int idCadastro) {
		Cadastro cadastro = this.buscarCadastro(idCadastro);
		CadastroResponse cadastroResponse = CadastroResponse.builder()
				.idCadastro(cadastro.getIdCadastro())
				.nomeCompleto(cadastro.getNomeCompleto())
				.telefone(cadastro.getTelefone())
				.emailCad(cadastro.getEmailCad())
				.senhaCad(cadastro.getSenhaCad())
				.build();
		return cadastroResponse;
	}
	
	public Cadastro buscarCadastro(int idCadastro) {
		Optional<Cadastro> cadastro = cadastroRepository.findById(idCadastro);
		return cadastro.get();
	}

	public Cadastro gravarCadastro(CadastroRequest cadastroRequest) {
		Cadastro cadastro = new Cadastro();
		cadastro.setNomeCompleto(cadastroRequest.nomeCompleto());
		cadastro.setTelefone(cadastroRequest.telefone());
		cadastro.setEmailCad(cadastroRequest.emailCad());
		cadastro.setSenhaCad(cadastroRequest.senhaCad());
		return cadastroRepository.save(cadastro);
	}

	public Cadastro atualizarCadastro(CadastroRequest cadastroRequest, int idCadastro) {
		Cadastro cadastro = cadastroRepository.findById(idCadastro).get();
		cadastro.setNomeCompleto(cadastroRequest.nomeCompleto());
		cadastro.setTelefone(cadastroRequest.telefone());
		cadastro.setEmailCad(cadastroRequest.emailCad());
		cadastro.setSenhaCad(cadastroRequest.senhaCad());
		return cadastroRepository.save(cadastro);
	}

	public String deletarCadastro(int idCadastro) {
		Cadastro cadastro = cadastroRepository.findByIdCadastro(idCadastro);
		cadastroRepository.delete(cadastro);
		return "Cadastro excluído";
	}

	private CadastroResponse toDTO(Cadastro cadastro, boolean self) {
		Link link;
		if (self) {
			link = linkTo(methodOn(CadastroController.class).buscarCadastroPorId(cadastro.getIdCadastro()))
					.withSelfRel();
		} else {
			link = linkTo(methodOn(CadastroController.class).buscarCadastros()).withRel("Lista de Cadastros");
		}
		return new CadastroResponse(cadastro.getIdCadastro(), cadastro.getNomeCompleto(), cadastro.getTelefone(),
				cadastro.getEmailCad(), cadastro.getSenhaCad());
	}

}
