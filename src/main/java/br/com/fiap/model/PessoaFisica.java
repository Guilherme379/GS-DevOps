package br.com.fiap.model;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pessoa_f")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaFisica implements Serializable{

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "seq_pessoa_fisica")
	@SequenceGenerator(
			name = "seq_pessoa_fisica", 
			sequenceName = "seq_pessoa_fisica",
			allocationSize = 1)
	@Column(name = "id_pf")
	private int idPessoaFisica;
	
	@NotNull(message = "A data não pode ser nula")
	@Column(name = "data_nasc")
	private Date dataNasc;
	
	@NotNull(message = "O cpf não pode ser nulo")
	@Size(min = 11, max = 11)
	@Column(name = "cpf")
	private String cpf;
	
	@NotNull(message = "O XP não pode ser nulo")
	@Min(1)
	@Max(99999)
	@Column(name = "xp")
	private int xp;
	
	@ManyToOne
    @JoinColumn(name = "id_cad", nullable = false)
	private Cadastro cadastro;
	
}
