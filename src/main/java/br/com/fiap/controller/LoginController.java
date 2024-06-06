package br.com.fiap.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.model.Login;
import br.com.fiap.request.LoginRequest;
import br.com.fiap.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/login")
public class LoginController {

	private LoginService loginService;

	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@Operation(summary = "Retorna um login específico pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum login encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@GetMapping("/{idLogin}")
	public Login buscarLogin(@PathVariable int idLogin) {
		return loginService.buscarLogin(idLogin);
	}
	
	@Operation(summary = "Grava um login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Login gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@PostMapping
	@Transactional
	public Login criarLogin(@RequestBody LoginRequest loginRequest) {
		return loginService.gravarLogin(loginRequest);
	}
	
	@Operation(summary = "Atualiza um login baseado no seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Login atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@PutMapping("/{idLogin}")
	@Transactional
	public Login atualizarLogin(@RequestBody LoginRequest loginRequest, @PathVariable int idLogin) {
		return loginService.atualizarLogin(loginRequest, idLogin);
	}
	
	@Operation(summary = "Exclui um login com base no seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Login excluído com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
	
	@DeleteMapping("/{idLogin}")
	public String deletarLogin(@PathVariable int idLogin) {
		loginService.deletarLogin(idLogin);
		return "Login deletado";
	}
	
	@Operation(summary = "Retorna uma confirmacao de que aquele email esta cadastrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum login encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })

	@GetMapping("/conferirEmailCadastrado/{idLogin}")
	public void conferirEmailCadastrado(@RequestParam int idCadastro, int idLogin) {
		loginService.conferirEmailCadastrado(idCadastro, idLogin);
	}
}
