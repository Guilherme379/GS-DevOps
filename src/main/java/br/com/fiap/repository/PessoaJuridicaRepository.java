package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.model.PessoaJuridica;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Integer>{

	PessoaJuridica findByIdPessoaJuridica(int idPessoaJuridica);
	
	@Query("SELECT p FROM PessoaJuridica p ORDER BY p.quantLixoColetada DESC")
    List<PessoaJuridica> findAllOrderByQuantLixoColetadaDesc();

}
