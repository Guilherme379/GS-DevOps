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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.model.Ranking;
import br.com.fiap.request.RankingRequest;
import br.com.fiap.response.RankingResponse;
import br.com.fiap.service.RankingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/ranking")
public class RankingController {

	private RankingService rankingService;

	public RankingController(RankingService rankingService) {
		this.rankingService = rankingService;
	}
	
	@Operation(summary = "Retorna todos os rankings em páginas de 8")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum ranking encontrado", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@GetMapping
    public ResponseEntity<Page<RankingResponse>> buscarRankings() {
        Page<RankingResponse> rankingsResponse = rankingService.buscarRankings();
        if (rankingsResponse == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(rankingsResponse);
        }
    }
	
	@Operation(summary = "Retorna um ranking específico pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum ranking encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@GetMapping("/{idRanking}")
    public ResponseEntity<RankingResponse> buscarRankingPorId(@PathVariable int idRanking) {
		RankingResponse rankinResponse = rankingService.buscarRankingResponse(idRanking);
        if (rankinResponse == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(rankinResponse);
        }
    }
	
	@Operation(summary = "Grava um ranking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ranking gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@PostMapping
	@Transactional
	public Ranking criarRanking(@RequestBody RankingRequest rankingRequest) {
		return rankingService.gravarRanking(rankingRequest);
	}
	
	@Operation(summary = "Atualiza um ranking baseado no seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ranking atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@PutMapping("/{idRanking}")
	@Transactional
	public Ranking atualizarRanking(@RequestBody RankingRequest rankingRequest, @PathVariable int idRanking) {
		return rankingService.atualizarRanking(rankingRequest, idRanking);
	}
	
	@Operation(summary = "Exclui um ranking com base no seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ranking excluído com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@DeleteMapping("/{idRanking}")
	public String deletarRanking(@PathVariable int idRanking) {
		rankingService.deletarRanking(idRanking);
		return "Ranking deletado";
	}
	
	@Operation(summary = "Retorna a informacao atualizada do ranking que a empresa/ONG está")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum ranking encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@GetMapping("/posicaoEmpresa/{idRanking}")
	public void posicaoEmpresa(@RequestParam double quantLixoColetada, String posição, int idPessoaJuridica, int idRanking) {
		rankingService.posicaoEmpresa(quantLixoColetada, posição, idPessoaJuridica, idRanking);
	}

}
