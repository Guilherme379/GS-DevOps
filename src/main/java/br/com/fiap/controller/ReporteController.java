package br.com.fiap.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.model.Reporte;
import br.com.fiap.request.ReporteRequest;
import br.com.fiap.response.ReporteResponse;
import br.com.fiap.service.ReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/reporte")
public class ReporteController {

	private ReporteService reporteService;

	public ReporteController(ReporteService reporteService) {
		this.reporteService = reporteService;
	}
	
	@Operation(summary = "Retorna todos os reportes em páginas de 10")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum reporte encontrado", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@GetMapping
    public ResponseEntity<Page<ReporteResponse>> buscarReportes() {
        Page<ReporteResponse> reporteResponse = reporteService.buscarReportes();
        if (reporteResponse == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(reporteResponse);
        }
    }
	
	@Operation(summary = "Retorna um reporte específico pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum reporte encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@GetMapping("/{idReporte}")
    public ResponseEntity<ReporteResponse> buscarReportePorId(@PathVariable int idReporte) {
		ReporteResponse reporteResponse = reporteService.buscarReporteResponse(idReporte);
        if (reporteResponse == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(reporteResponse);
        }
    }
	
	@Operation(summary = "Grava um reporte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reporte gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@PostMapping
	@Transactional
	public Reporte criarReporte(@RequestBody ReporteRequest reporteRequest) {
		return reporteService.gravarReporte(reporteRequest);
	}
	
	@Operation(summary = "Atualiza um reporte baseado no seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reporte atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@PutMapping("/{idReporte}")
	@Transactional
	public Reporte atualizarReporte(@RequestBody ReporteRequest reporteRequest, @PathVariable int idReporte) {
		return reporteService.atualizarReporte(reporteRequest, idReporte);
	}
	
	@Operation(summary = "Exclui um reporte com base no seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reporte excluído com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@DeleteMapping("/{idReporte}")
	public String deletarReporte(@PathVariable int idReporte) {
		reporteService.deletarReporte(idReporte);
		return "Reporte deletado";
	}
}
