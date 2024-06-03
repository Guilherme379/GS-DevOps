package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.model.PessoaJuridica;
import br.com.fiap.model.Ranking;

public interface RankingRepository extends JpaRepository<Ranking, Integer>{

	Ranking findByIdRanking(int idRanking);
	
	Ranking findByPessoaJuridica(PessoaJuridica pessoaJuridica);

}
