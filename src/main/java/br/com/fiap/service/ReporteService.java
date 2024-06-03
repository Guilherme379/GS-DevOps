package br.com.fiap.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import br.com.fiap.controller.ReporteController;
import br.com.fiap.model.Localizacao;
import br.com.fiap.model.Reporte;
import br.com.fiap.repository.ReporteRepository;
import br.com.fiap.request.ReporteRequest;

@Service
public class ReporteService {

	private ReporteRepository reporteRepository;
	private LocalizacaoService localizacaoService;
	private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 10, Sort.by("quantidade").ascending());
	
	public ReporteService(ReporteRepository reporteRepository, LocalizacaoService localizacaoService) {
		this.reporteRepository = reporteRepository;
		this.localizacaoService = localizacaoService;
	}

	public Reporte buscarReporte(int idReporte) {
		Optional<Reporte> reporte = reporteRepository.findById(idReporte);
		return reporte.get();
	}

	public Reporte gravarReporte(ReporteRequest reporteRequest) {
		Localizacao localizacao = localizacaoService.buscarLocalizacao(reporteRequest.localizacao().getIdLocalizacao());

		Reporte reporte = new Reporte();
		reporte.setLocalizacao(localizacao);
		reporte.setQuantidade(reporteRequest.quantidade());
		reporte.setDescricaoReporte(reporteRequest.descricaoReporte());

		return reporteRepository.save(reporte);
	}

	public Reporte atualizarReporte(ReporteRequest reporteRequest, int idReporte) {
		Reporte reporte = reporteRepository.findById(idReporte).get();
		reporte.setQuantidade(reporteRequest.quantidade());
		reporte.setDescricaoReporte(reporteRequest.descricaoReporte());
		return reporteRepository.save(reporte);
	}

	public String deletarReporte(int idReporte) {
		Reporte reporte = reporteRepository.findByIdReporte(idReporte);
		reporteRepository.delete(reporte);
		return "Reporte excluído";
	}
	
	/*
	private ReporteRequest toDTO(Reporte reporte, boolean self) {
		Link link;
		if (self) {
			link = linkTo(methodOn(ReporteController.class).buscarReportePorId(reporte.getIdReporte())).withSelfRel();
		} else {
			link = linkTo(methodOn(ReporteController.class).buscarReportes()).withRel("Lista de Reportes");
		}
		return new ReporteRequest(
				reporte.getIdReporte(),
				reporte.getQuantidade(),
				reporte.getDescricaoReporte(),
				reporte.getLocalizacao(),
				link);
	}*/
}
