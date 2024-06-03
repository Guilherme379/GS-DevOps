package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.model.Localizacao;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Integer>{

	Localizacao findByIdLocalizacao(int idLocalizacao);
}
