package br.com.fiap.model;

import java.io.Serializable;

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
@Table(name = "ranking")
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
	@Column(name = "id_ranking")
	private int idRanking;
	
	@NotNull(message = "A posicao não pode ser nula")
	@Min(1)
	@Max(9999)
	@Column(name = "posicao")
	private int posicao;
	
	@ManyToOne
    @JoinColumn(name = "id_pj", nullable = false)
	private PessoaJuridica pessoaJuridica;
	
}
