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

import br.com.fiap.model.PessoaJuridica;
import br.com.fiap.request.PessoaJuridicaRequest;
import br.com.fiap.response.PessoaJuridicaResponse;
import br.com.fiap.service.PessoaJuridicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/pessoaJuridica")
public class PessoaJuridicaController {

	private PessoaJuridicaService pessoaJuridicaService;

	public PessoaJuridicaController(PessoaJuridicaService pessoaJuridicaService) {
		this.pessoaJuridicaService = pessoaJuridicaService;
	}
	
	@Operation(summary = "Retorna todas as pessoas juridicas em páginas de 8")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma pessoa juridica encontrada", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@GetMapping
    public ResponseEntity<Page<PessoaJuridicaResponse>> buscarPessoasJuridicas() {
        Page<PessoaJuridicaResponse> pessoasJuridicasResponse = pessoaJuridicaService.buscarPessoasJuridicas();
        if (pessoasJuridicasResponse == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(pessoasJuridicasResponse);
        }
    }
	
	@Operation(summary = "Retorna uma pessoa juridica específica pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma pessoa juridica encontrada para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@GetMapping("/{idPessoaJuridica}")
    public ResponseEntity<PessoaJuridicaResponse> buscarPessoaJuridicaPorId(@PathVariable int idPessoaJuridica) {
		PessoaJuridicaResponse pessoaJuridicaResponse = pessoaJuridicaService.buscarPessoaJuridicaResponse(idPessoaJuridica);
        if (pessoaJuridicaResponse == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(pessoaJuridicaResponse);
        }
    }
	
	@Operation(summary = "Grava uma pessoa juridica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa juridica gravada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@PostMapping
	@Transactional
	public PessoaJuridica criarPessoaJuridica(@RequestBody PessoaJuridicaRequest pessoaJuridicaRequest) {
		return pessoaJuridicaService.gravarPessoaJuridica(pessoaJuridicaRequest);
	}
	
	@Operation(summary = "Atualiza uma pessoa juridica baseada no seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa juridica atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@PutMapping("/{idPessoaJuridica}")
	@Transactional
	public PessoaJuridica atualizarPessoaJuridica(@RequestBody PessoaJuridicaRequest pessoaJuridicaRequest, @PathVariable int idPessoaJuridica) {
		return pessoaJuridicaService.atualizarPessoaJuridica(pessoaJuridicaRequest, idPessoaJuridica);
	}
	
	@Operation(summary = "Exclui uma pessoa juridica com base no seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pessoa juridica excluído com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@DeleteMapping("/{idPessoaJuridica}")
	public String deletarPessoaJuridica(@PathVariable int idPessoaJuridica) {
		pessoaJuridicaService.deletarPessoaJuridica(idPessoaJuridica);
		return "Pessoa juridica deletada";
	}
	
}
