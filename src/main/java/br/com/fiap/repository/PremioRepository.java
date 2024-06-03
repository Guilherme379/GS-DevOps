package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.model.Premio;

public interface PremioRepository extends JpaRepository<Premio, Integer>{

	Premio findByIdPremio(int idPremio);

}
