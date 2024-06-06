package br.com.fiap.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import br.com.fiap.controller.RankingController;
import br.com.fiap.model.PessoaJuridica;
import br.com.fiap.model.Ranking;
import br.com.fiap.repository.PessoaJuridicaRepository;
import br.com.fiap.repository.RankingRepository;
import br.com.fiap.request.RankingRequest;
import br.com.fiap.response.RankingResponse;

@Service
public class RankingService {

	private RankingRepository rankingRepository;
	private PessoaJuridicaService pessoaJuridicaService;
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 8, Sort.by("posicao").ascending());
	
	public RankingService(RankingRepository rankingRepository, PessoaJuridicaService pessoaJuridicaService,
			PessoaJuridicaRepository pessoaJuridicaRepository) {
		this.rankingRepository = rankingRepository;
		this.pessoaJuridicaService = pessoaJuridicaService;
		this.pessoaJuridicaRepository = pessoaJuridicaRepository;
	}
	
	public Page<RankingResponse> buscarRankings() {
		return rankingRepository.findAll(paginacaoPersonalizada).map(ranking -> toDTO(ranking, true));
	}
	
	public RankingResponse buscarRankingResponse(int idRanking) {
		Ranking ranking = this.buscarRanking(idRanking);
		RankingResponse rankingResponse = RankingResponse.builder()
				.idRanking(ranking.getIdRanking())
				.posicao(ranking.getPosicao())
				.pessoaJuridica(ranking.getPessoaJuridica())
				.build();
		return rankingResponse;
	}

	public Ranking buscarRanking(int idRanking) {
		Optional<Ranking> ranking = rankingRepository.findById(idRanking);
		return ranking.get();
	}

	public Ranking gravarRanking(RankingRequest rankingRequest) {
		PessoaJuridica pessoaJuridica = pessoaJuridicaService.buscarPessoaJuridica(rankingRequest.pessoaJuridica().getIdPessoaJuridica());

		Ranking ranking = new Ranking();
		ranking.setPessoaJuridica(pessoaJuridica);
		ranking.setPosicao(rankingRequest.posicao());

		return rankingRepository.save(ranking);
	}
	
	public Ranking atualizarRanking(RankingRequest rankingRequest, int idRanking) {
		Ranking ranking = rankingRepository.findById(idRanking).get();
		ranking.setPosicao(rankingRequest.posicao());
		return rankingRepository.save(ranking);
	}

	public String deletarRanking(int idRanking) {
		Ranking ranking = rankingRepository.findByIdRanking(idRanking);
		rankingRepository.delete(ranking);
		return "Ranking excluído";
	}
	
	//Método para atualizar a posição das empresas no ranking baseado na quantidade de lixo coletada
	public String posicaoEmpresa(double quantLixoColetada, String posição, int idPessoaJuridica, int idRanking) {
		Optional<PessoaJuridica> pessoaJuridicaOpt = pessoaJuridicaRepository.findById(idPessoaJuridica);
		Optional<Ranking> rankingOpt = rankingRepository.findById(idRanking);
		if (!pessoaJuridicaOpt.isPresent() || !rankingOpt.isPresent()) {
			return "Pessoa Jurídica ou Ranking não encontrado";
		}
		 PessoaJuridica pessoaJuridica = pessoaJuridicaOpt.get();
	     pessoaJuridica.setQuantLixoColetada(quantLixoColetada);
	     pessoaJuridicaRepository.save(pessoaJuridica);
	     
	     List<PessoaJuridica> todasAsEmpresas = pessoaJuridicaRepository.findAllOrderByQuantLixoColetadaDesc();
	     
	     int posicaoAtualizada = 1;
	        for (PessoaJuridica empresa : todasAsEmpresas) {
	            Ranking ranking = rankingRepository.findByPessoaJuridica(empresa);
	            ranking.setPosicao(posicaoAtualizada);
	            rankingRepository.save(ranking);
	            posicaoAtualizada++;
	        }
	     return "Posição atualizada com sucesso";
	}

	private RankingResponse toDTO(Ranking ranking, boolean self) {
		Link link;
		if (self) {
			link = linkTo(methodOn(RankingController.class).buscarRankingPorId(ranking.getIdRanking())).withSelfRel();
		} else {
			link = linkTo(methodOn(RankingController.class).buscarRankings()).withRel("Lista de Rankings");
		}
		return new RankingResponse(
				ranking.getIdRanking(),
				ranking.getPosicao(),
				ranking.getPessoaJuridica());
	}

}
