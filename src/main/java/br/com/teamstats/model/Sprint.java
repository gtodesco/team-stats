package br.com.teamstats.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sprint")
@EntityListeners(AuditingEntityListener.class)
public class Sprint{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotNull
	private String numero;
	
	@Column
	@NotNull
	private String pontPlanejada;
	
	@Column
	private String pontUltimaSprint;
	
	@Column
	private String pontConcluidas;
	
	@Column
	private String pontNaoTerminadas;
	
	@Column
	private String pontPlus;
	
	@Column
	private String pontInterrupcoes;
	
	@Column
	@NotNull
	private String qtdDias;
	
	@Column
	@NotNull
	private boolean finalizada;

	@ManyToOne
	@JsonIgnoreProperties("sprints")
	private Equipe equipe;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPontPlanejada() {
		return pontPlanejada;
	}

	public void setPontPlanejada(String pontPlanejada) {
		this.pontPlanejada = pontPlanejada;
	}

	public String getPontUltimaSprint() {
		return pontUltimaSprint;
	}

	public void setPontUltimaSprint(String pontUltimaSprint) {
		this.pontUltimaSprint = pontUltimaSprint;
	}

	public String getPontConcluidas() {
		return pontConcluidas;
	}

	public void setPontConcluidas(String pontConcluidas) {
		this.pontConcluidas = pontConcluidas;
	}

	public String getPontNaoTerminadas() {
		return pontNaoTerminadas;
	}

	public void setPontNaoTerminadas(String pontNaoTerminadas) {
		this.pontNaoTerminadas = pontNaoTerminadas;
	}

	public String getPontPlus() {
		return pontPlus;
	}

	public void setPontPlus(String pontPlus) {
		this.pontPlus = pontPlus;
	}

	public String getPontInterrupcoes() {
		return pontInterrupcoes;
	}

	public void setPontInterrupcoes(String pontInterrupcoes) {
		this.pontInterrupcoes = pontInterrupcoes;
	}

	public String getQtdDias() {
		return qtdDias;
	}

	public void setQtdDias(String qtdDias) {
		this.qtdDias = qtdDias;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	
}
