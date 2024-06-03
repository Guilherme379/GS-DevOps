package br.com.fiap.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Ranking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ranking implements Serializable{

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "seq_ranking")
	@SequenceGenerator(
			name = "seq_ranking", 
			sequenceName = "seq_ranking",
			allocationSize = 1)
	private int idRanking;
	
	@NotNull(message = "A posicao não pode ser nula")
	@Size(min = 1, max = 4)
	@Column(name = "posicao")
	private int posicao;
	
	private PessoaJuridica pessoaJuridica;
	
}
