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

import br.com.fiap.model.Premio;
import br.com.fiap.request.PremioRequest;
import br.com.fiap.response.PremioResponse;
import br.com.fiap.service.PremioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/premio")
public class PremioController {

	private PremioService premioService;

	public PremioController(PremioService premioService) {
		this.premioService = premioService;
	}
	
	@Operation(summary = "Retorna todos os premios em páginas de 8")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum premio encontrado", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@GetMapping
    public ResponseEntity<Page<PremioResponse>> buscarPremios() {
        Page<PremioResponse> premiosResponse = premioService.buscarPremios();
        if (premiosResponse == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(premiosResponse);
        }
    }
	
	@Operation(summary = "Retorna um premio específico pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum premio encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@GetMapping("/{idPremio}")
    public ResponseEntity<PremioResponse> buscarPremioPorId(@PathVariable int idPremio) {
		PremioResponse premioResponse = premioService.buscarPremioResponse(idPremio);
        if (premioResponse == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(premioResponse);
        }
    }
	
	@Operation(summary = "Grava um premio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Premio gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@PostMapping
	@Transactional
	public Premio criarPremio(@RequestBody PremioRequest premioRequest) {
		return premioService.gravarPremio(premioRequest);
	}
	
	@Operation(summary = "Atualiza um premio baseado no seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Premio atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@PutMapping("/{idPremio}")
	@Transactional
	public Premio atualizarPremio(@RequestBody PremioRequest premioRequest, @PathVariable int idPremio) {
		return premioService.atualizarPremio(premioRequest, idPremio);
	}
	
	@Operation(summary = "Exclui um premio com base no seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Premio excluído com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@DeleteMapping("/{idPremio}")
	public String deletarPremio(@PathVariable int idPremio) {
		premioService.deletarPremio(idPremio);
		return "Premio deletado";
	}
	
	@Operation(summary = "Retorna uma confirmacao de que o usuario tem acesso liberado ao premio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum premio encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@GetMapping("/acessoPremio/{idPremio}")
	public void acessoPremio(@RequestParam int idPremio, int idPessoaFisica) {
		premioService.acessoPremio(idPremio, idPessoaFisica);
	}

}
