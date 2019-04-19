package br.com.teamstats.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pessoa")
@EntityListeners(AuditingEntityListener.class)
public class Pessoa{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotBlank
	private String nome;
	
	@Column
	@NotBlank
	private String email;
	
	@Column
	@NotBlank
	private String password;
	
	@Column
	private String ativ_Concluidas;
	
	@Column
	private String pont_Entregue;
	
	@ManyToOne
	@JsonIgnoreProperties("pessoas")
	private Equipe equipe;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAtiv_Concluidas() {
		return ativ_Concluidas;
	}

	public void setAtiv_Concluidas(String ativ_Concluidas) {
		this.ativ_Concluidas = ativ_Concluidas;
	}

	public String getPont_Entregue() {
		return pont_Entregue;
	}

	public void setPont_Entregue(String pont_Entregue) {
		this.pont_Entregue = pont_Entregue;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}



}
