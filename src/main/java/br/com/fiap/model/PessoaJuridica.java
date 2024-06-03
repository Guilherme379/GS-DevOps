package br.com.fiap.model;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pessoa_juridica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaJuridica implements Serializable{

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "seq_pessoa_juridica")
	@SequenceGenerator(
			name = "seq_pessoa_juridica", 
			sequenceName = "seq_pessoa_juridica",
			allocationSize = 1)
	private int idPessoaJuridica;
	
	@NotNull(message = "O cnpj não pode ser nulo")
	@Size(min = 14, max = 14)
	@Column(name = "cnpj")
	private long cnpj;
	
	@Size(min = 1, max = 9999)
	@Column(name = "qtd_lixo_coletado")
	private double quantLixoColetada;
	
	@PastOrPresent(message = "data da coleta só pode ser no presente ou passado")
	@Column(name = "data_coleta")
	private Date dataColeta;
	
	private Cadastro cadastro;
	
}
