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
@Table(name = "Reporte_lixo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reporte implements Serializable{

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "seq_reporte")
	@SequenceGenerator(
			name = "seq_reporte", 
			sequenceName = "seq_reporte",
			allocationSize = 1)
	private int idReporte;
	
	@NotNull(message = "a quantidade de lixo não pode ser nulo")
	@Size(min = 5, max = 15)
	@Column(name = "qtd_lixo")
	private String quantidade;
	
	@NotNull(message = "O descrição não pode ser nulo")
	@Size(min = 5, max = 100)
	@Column(name = "descricao_reporte")
	private String descricaoReporte;
	
	private Localizacao localizacao;
}
