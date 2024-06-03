package br.com.fiap.service;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.fiap.model.PessoaFisica;
import br.com.fiap.model.Premio;
import br.com.fiap.repository.PessoaFisicaRepository;
import br.com.fiap.repository.PremioRepository;
import br.com.fiap.request.PremioRequest;
import jakarta.validation.ValidationException;

@Service
public class PremioService {

	private PremioRepository premioRepository;
	private PessoaFisicaService pessoaFisicaService;
	private PessoaFisicaRepository pessoaFisicaRepository;
	private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 8, Sort.by("produto").ascending());
	
	public PremioService(PremioRepository premioRepository, PessoaFisicaService pessoaFisicaService,
			PessoaFisicaRepository pessoaFisicaRepository) {
		this.premioRepository = premioRepository;
		this.pessoaFisicaService = pessoaFisicaService;
		this.pessoaFisicaRepository = pessoaFisicaRepository;
	}

	public Premio buscarPremio(int idPremio) {
		Optional<Premio> premio = premioRepository.findById(idPremio);
		return premio.get();
	}
	
	public Premio gravarPremio(PremioRequest premioRequest) {
		PessoaFisica pessoaFisica = pessoaFisicaService.buscarPessoaFisica(premioRequest.pessoaFisica().getIdPessoaFisica());

		Premio premio = new Premio();
		premio.setPessoaFisica(pessoaFisica);
		premio.setDescricaoPremio(premioRequest.descricaoPremio());
		premio.setProduto(premioRequest.produto());
		premio.setSku(premioRequest.sku());
		premio.setXpPremio(premioRequest.xpPremio());

		return premioRepository.save(premio);
	}
	
	public Premio atualizarPremio(PremioRequest premioRequest, int idPremio) {
		Premio premio = premioRepository.findById(idPremio).get();
		premio.setDescricaoPremio(premioRequest.descricaoPremio());
		premio.setProduto(premioRequest.produto());
		premio.setSku(premioRequest.sku());
		premio.setXpPremio(premioRequest.xpPremio());
		return premioRepository.save(premio);
	}
	
	public String deletarPremio(int idPremio) {
		Premio premio = premioRepository.findByIdPremio(idPremio);
		premioRepository.delete(premio);
		return "Premio excluído";
	}
	
	//Acesso aos prêmios
	public String acessoPremio(int idPremio, int idPessoaFisica) {
		//xp da pessoa == xp do premio = premio liberado
		Optional<PessoaFisica> pessoaFisica = pessoaFisicaRepository.findById(idPessoaFisica);
		Optional<Premio> premio = premioRepository.findById(idPremio);
		if (pessoaFisica.get().getXp() == premio.get().getXpPremio()) {
			return "Acesso liberado ao premio";
		} else {
			throw new ValidationException("Acesso bloqueado: você não ainda não alcançou a quantidade de XP para esse prêmio");
		}
	}
	
	
	/*
	private PremioRequest toDTO(Premio premio, boolean self) {
		Link link;
		if (self) {
			link = linkTo(methodOn(PremioController.class).buscarPremioPorId(premio.getIdPremio())).withSelfRel();
		} else {
			link = linkTo(methodOn(PremioController.class).buscarPremios()).withRel("Lista de Premios");
		}
		return new PremioRequest(
				premio.getIdPremio(),
				premio.getDescricaoPremio(),
				premio.getProduto(),
				premio.getSku(),
				premio.getXpPremio(),
				premio.getPessoaFisica(),
				link);
	}*/
}
