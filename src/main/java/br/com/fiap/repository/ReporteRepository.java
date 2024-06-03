package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.model.Reporte;

public interface ReporteRepository extends JpaRepository<Reporte, Integer>{

	Reporte findByIdReporte(int idReporte);

}
