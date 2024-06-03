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
@Table(name = "premio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Premio implements Serializable {

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "seq_premio")
	@SequenceGenerator(
			name = "seq_premio", 
			sequenceName = "seq_premio",
			allocationSize = 1)
	private int idPremio;
	
	@NotNull(message = "A descricao não pode ser nulo")
	@Size(min = 1, max = 100)
	@Column(name = "descricao_premio")
	private String descricaoPremio;
	
	@NotNull(message = "O nome do produto não pode ser nulo")
	@Size(min = 1, max = 70)
	@Column(name = "produto")
	private String produto;
	
	@NotNull(message = "A sku não pode ser nulo")
	@Size(min = 1, max = 80)
	@Column(name = "sku")
	private String sku;
	
	@NotNull(message = "O XP não pode ser nulo")
	@Size(min = 1, max = 99999)
	@Column(name = "xp_premio")
	private int xpPremio;
	
	private PessoaFisica pessoaFisica;
	
}
