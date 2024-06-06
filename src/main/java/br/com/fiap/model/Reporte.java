package br.com.fiap.model;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name = "reporte_lixo")
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
	@Column(name = "id_reporte")
	private int idReporte;
	
	@NotNull(message = "a quantidade de lixo não pode ser nula")
	@Min(1)
	@Max(9999)
	@Column(name = "qtd_lixo")
	private int quantidade;
	
	@NotNull(message = "A descrição não pode ser nula")
	@Size(min = 5, max = 100)
	@Column(name = "descricao_reporte")
	private String descricaoReporte;
	
	@Column(name = "data_hora_reporte")
	private Timestamp dataHoraReporte;
	
	@ManyToOne
    @JoinColumn(name = "id_loc", nullable = false)
	private Localizacao localizacao;
}
