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

import br.com.fiap.model.PessoaFisica;
import br.com.fiap.request.PessoaFisicaRequest;
import br.com.fiap.response.PessoaFisicaResponse;
import br.com.fiap.service.PessoaFisicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/pessoaFisica")
public class PessoaFisicaController {

	private PessoaFisicaService pessoaFisicaService;

	public PessoaFisicaController(PessoaFisicaService pessoaFisicaService) {
		this.pessoaFisicaService = pessoaFisicaService;
	}
	
	@Operation(summary = "Retorna todas as pessoas fisicas em páginas de 8")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma pessoa fisica encontrada", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@GetMapping
    public ResponseEntity<Page<PessoaFisicaResponse>> buscarPessoasFisicas() {
        Page<PessoaFisicaResponse> pessoasFisicasResponse = pessoaFisicaService.buscarPessoasFisicas();
        if (pessoasFisicasResponse == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(pessoasFisicasResponse);
        }
    }
	
	@Operation(summary = "Retorna uma pessoa fisica específica pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma pessoa fisica encontrada para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@GetMapping("/{idPessoaFisica}")
    public ResponseEntity<PessoaFisicaResponse> buscarPessoaFisicaPorId(@PathVariable int idPessoaFisica) {
		PessoaFisicaResponse pessoaFisicaResponse = pessoaFisicaService.buscarPessoaFisicaResponse(idPessoaFisica);
        if (pessoaFisicaResponse == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(pessoaFisicaResponse);
        }
    }
	
	@Operation(summary = "Grava uma pessoa fisica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa fisica gravada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@PostMapping
	@Transactional
	public PessoaFisica criarPessoaFisica(@RequestBody PessoaFisicaRequest pessoaFisicaRequest) {
		return pessoaFisicaService.gravarPessoaFisica(pessoaFisicaRequest);
	}
	
	@Operation(summary = "Atualiza um uma pessoa fisica baseada no seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa fisica atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@PutMapping("/{idPessoaFisica}")
	@Transactional
	public PessoaFisica atualizarPessoaFisica(@RequestBody PessoaFisicaRequest pessoaFisicaRequest, @PathVariable int idPessoaFisica) {
		return pessoaFisicaService.atualizarPessoaFisica(pessoaFisicaRequest, idPessoaFisica);
	}
	
	@Operation(summary = "Exclui uma pessoa fisica com base no seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pessoa fisica excluída com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@DeleteMapping("/{idPessoaFisica}")
	public String deletarPessoaFisica(@PathVariable int idPessoaFisica) {
		pessoaFisicaService.deletarPessoaFisica(idPessoaFisica);
		return "Pessoa física deletada";
	}
	
}
