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

import br.com.fiap.model.Localizacao;
import br.com.fiap.request.LocalizacaoRequest;
import br.com.fiap.response.LocalizacaoResponse;
import br.com.fiap.service.LocalizacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/localizacao")
public class LocalizacaoController {

	private LocalizacaoService localizacaoService;

	public LocalizacaoController(LocalizacaoService localizacaoService) {
		this.localizacaoService = localizacaoService;
	}
	
	@Operation(summary = "Retorna todas as localizacoes em páginas de 5")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma localizacao encontrada", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@GetMapping
    public ResponseEntity<Page<LocalizacaoResponse>> buscarLocalizacoes() {
        Page<LocalizacaoResponse> localizacoesResponse = localizacaoService.buscarLocalizacoes();
        if (localizacoesResponse == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(localizacoesResponse);
        }
    }
	
	@Operation(summary = "Retorna uma localizacao específica pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma localizacao encontrada para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@GetMapping("/{idLocalizacao}")
    public ResponseEntity<LocalizacaoResponse> buscarLocalizacaoPorId(@PathVariable int idLocalizacao) {
		LocalizacaoResponse localizacaoResponse = localizacaoService.buscarLocalizacaoResponse(idLocalizacao);
        if (localizacaoResponse == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(localizacaoResponse);
        }
    }
	
	@Operation(summary = "Grava uma localizacao")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Localizacao gravada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@PostMapping
	@Transactional
	public Localizacao criarLocalizacao(@RequestBody LocalizacaoRequest localizacaoRequest) {
		return localizacaoService.gravarLocalizacao(localizacaoRequest);
	}
	
	@Operation(summary = "Atualiza uma localizacao baseada no seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Localizacao atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@PutMapping("/{idLocalizacao}")
	@Transactional
	public Localizacao atualizarLocalizacao(@RequestBody LocalizacaoRequest localizacaoRequest, @PathVariable int idLocalizacao) {
		return localizacaoService.atualizarLocalizacao(localizacaoRequest, idLocalizacao);
	}
	
	@Operation(summary = "Exclui uma localizacao com base no seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Localizacao excluída com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@DeleteMapping("/{idLocalizacao}")
	public String deletarLocalizacao(@PathVariable int idLocalizacao) {
		localizacaoService.deletarLocalizacao(idLocalizacao);
		return "Localizacao deletada";
	}
}
