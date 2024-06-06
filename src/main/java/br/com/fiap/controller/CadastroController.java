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

import br.com.fiap.model.Cadastro;
import br.com.fiap.request.CadastroRequest;
import br.com.fiap.response.CadastroResponse;
import br.com.fiap.service.CadastroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

	private CadastroService cadastroService;

	public CadastroController(CadastroService cadastroService) {
		this.cadastroService = cadastroService;
	}
	
	@Operation(summary = "Retorna todos os cadastros em páginas de 8")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum cadastro encontrado", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@GetMapping
    public ResponseEntity<Page<CadastroResponse>> buscarCadastros() {
        Page<CadastroResponse> cadastrosResponse = cadastroService.buscarCadastros();
        if (cadastrosResponse == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(cadastrosResponse);
        }
    }
	
	@Operation(summary = "Retorna um cadastro específico pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum cadastro encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@GetMapping("/{idCadastro}")
    public ResponseEntity<CadastroResponse> buscarCadastroPorId(@PathVariable int idCadastro) {
		CadastroResponse cadastroResponse = cadastroService.buscarCadastroResponse(idCadastro);
        if (cadastroResponse == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(cadastroResponse);
        }
    }
	
	@Operation(summary = "Grava um cadastro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@PostMapping
	@Transactional
	public Cadastro criarCadastro(@RequestBody CadastroRequest cadastroRequest) {
		return cadastroService.gravarCadastro(cadastroRequest);
	}
	
	@Operation(summary = "Atualiza um cadastro baseado no seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@PutMapping("/{idCadastro}")
	@Transactional
	public Cadastro atualizarCadastro(@RequestBody CadastroRequest cadastroRequest, @PathVariable int idCadastro) {
		return cadastroService.atualizarCadastro(cadastroRequest, idCadastro);
	}
	
	@Operation(summary = "Exclui um cadastro com base no seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cadastro excluído com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@DeleteMapping("/{idCadastro}")
	public String deletarCadastro(@PathVariable int idCadastro) {
		cadastroService.deletarCadastro(idCadastro);
		return "Cadastro deletado";
	}

}
