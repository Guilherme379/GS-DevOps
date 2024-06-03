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
@Table(name = "Localizacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Localizacao implements Serializable{

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "seq_localizacao")
	@SequenceGenerator(
			name = "seq_localizacao", 
			sequenceName = "seq_localizacao",
			allocationSize = 1)
	private int idLocalizacao;
	
	@Size(min = 1, max = 100)
	@Column(name = "praia")
	private String nomePraia;
	
	@NotNull(message = "A cidade não pode ser nula")
	@Size(min = 1, max = 70)
	@Column(name = "cidade")
	private String cidade;
	
	@NotNull(message = "O estado não pode ser nulo")
	@Size(min = 1, max = 100)
	@Column(name = "estado")
	private String estado;
	
	@NotNull(message = "O pais não pode ser nulo")
	@Size(min = 1, max = 100)
	@Column(name = "pais")
	private String pais;
	
	@NotNull(message = "A latitude não pode ser nula")
	@Size(min = 1, max = 100)
	@Column(name = "latitude_atual")
	private String latitudeAtual;
	
	@NotNull(message = "A longitude não pode ser nula")
	@Size(min = 1, max = 100)
	@Column(name = "longitude_atual")
	private String longitudeAtual;
	
}
