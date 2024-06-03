package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.model.Login;

public interface LoginRepository extends JpaRepository<Login, Integer>{

	Login findByIdLogin(int idLogin);

}
