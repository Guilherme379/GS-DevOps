package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.model.PessoaFisica;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Integer>{

	PessoaFisica findByIdPessoaFisica(int idPessoaFisica);

}
