package br.com.fiap.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import br.com.fiap.controller.LocalizacaoController;
import br.com.fiap.model.Localizacao;
import br.com.fiap.repository.LocalizacaoRepository;
import br.com.fiap.request.LocalizacaoRequest;

@Service
public class LocalizacaoService {

	private LocalizacaoRepository localizacaoRepository;
	private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 5, Sort.by("nomePraia").ascending());

	public LocalizacaoService(LocalizacaoRepository localizacaoRepository) {
		this.localizacaoRepository = localizacaoRepository;
	}

	public Localizacao buscarLocalizacao(int idLocalizacao) {
		Optional<Localizacao> localizacao = localizacaoRepository.findById(idLocalizacao);
		return localizacao.get();
	}

	public Localizacao gravarLocalizacao(LocalizacaoRequest localizacaoRequest) {
		Localizacao localizacao = new Localizacao();
		localizacao.setNomePraia(localizacaoRequest.nomePraia());
		localizacao.setCidade(localizacaoRequest.cidade());
		localizacao.setEstado(localizacaoRequest.estado());
		localizacao.setPais(localizacaoRequest.pais());
		localizacao.setLatitudeAtual(localizacaoRequest.latitudeAtual());
		localizacao.setLongitudeAtual(localizacaoRequest.longitudeAtual());
		return localizacaoRepository.save(localizacao);
	}

	public Localizacao atualizarLocalizacao(LocalizacaoRequest localizacaoRequest, int idLocalizacao) {
		Localizacao localizacao = localizacaoRepository.findById(idLocalizacao).get();
		localizacao.setNomePraia(localizacaoRequest.nomePraia());
		localizacao.setCidade(localizacaoRequest.cidade());
		localizacao.setEstado(localizacaoRequest.estado());
		localizacao.setPais(localizacaoRequest.pais());
		localizacao.setLatitudeAtual(localizacaoRequest.latitudeAtual());
		localizacao.setLongitudeAtual(localizacaoRequest.longitudeAtual());
		return localizacaoRepository.save(localizacao);
	}

	public String deletarLocalizacao(int idLocalizacao) {
		Localizacao localizacao = localizacaoRepository.findByIdLocalizacao(idLocalizacao);
		localizacaoRepository.delete(localizacao);
		return "Localização excluída";
	}
/*	private LocalizacaoRequest toDTO(Localizacao localizacao, boolean self) {
		Link link;
		if (self) {
			link = linkTo(methodOn(LocalizacaoController.class).buscarLocalizacaoPorId(localizacao.getIdLocalizacao()))
					.withSelfRel();
		} else {
			link = linkTo(methodOn(LocalizacaoController.class).buscarLocalizacoes()).withRel("Lista de Localizacoes");
		}
		return new LocalizacaoRequest(localizacao.getIdLocalizacao(), localizacao.getNomePraia(),
				localizacao.getCidade(), localizacao.getEstado(), localizacao.getPais(), localizacao.getLatitudeAtual(),
				localizacao.getLongitudeAtual(), link);
	}*/

}
