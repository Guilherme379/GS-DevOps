package br.com.fiap.model;

import java.io.Serializable;
import java.security.Timestamp;

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
@Table(name = "login")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Login implements Serializable{

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "seq_login")
	@SequenceGenerator(
			name = "seq_login", 
			sequenceName = "seq_login",
			allocationSize = 1)
	private int idLogin;
	
	@NotNull(message = "O email não pode ser nulo")
	@Size(min = 13, max = 60)
	@Column(name = "email_login")
	private String emailLog;
	
	@NotNull(message = "A senha não pode ser nula")
	@Size(min = 6, max = 50)
	@Column(name = "senha_login")
	private String senhaLog;
	
	@Column(name = "data_hora_login")
	private Timestamp dataHoraLogin;
	
	@Column(name = "data_hora_logout")
	private Timestamp dataHoraLogout;
	
	private Cadastro cadastro;
	
}
