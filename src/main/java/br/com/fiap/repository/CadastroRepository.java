package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.model.Cadastro;

public interface CadastroRepository extends JpaRepository<Cadastro, Integer>{

	Cadastro findByIdCadastro(int idCadastro);
}
