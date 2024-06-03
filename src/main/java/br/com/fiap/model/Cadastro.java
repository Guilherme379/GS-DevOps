package br.com.fiap.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Cadastro_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cadastro implements Serializable{

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "seq_cadastro_user")
	@SequenceGenerator(
			name = "seq_cadastro_user", 
			sequenceName = "seq_cadastro_user",
			allocationSize = 1)
	private int idCadastro;
	
	@NotNull(message = "O nome não pode ser nulo")
	@Size(min = 3, max = 100)
	@Column(name = "nome_comp")
	private String nomeCompleto;
	
	@NotNull(message = "O telefone não pode ser nulo")
	@Size(min = 11, max = 11)
	@Column(name = "telefone")
	private long telefone;
	
	@NotNull(message = "O email não pode ser nulo")
	@Size(min = 13, max = 60)
	@Column(name = "email_cad")
	private String emailCad;
	
	@NotNull(message = "A senha não pode ser nula")
	@Size(min = 6, max = 50)
	@Column(name = "senha_cad")
	private String senhaCad;

}
