package br.com.furb.teamstats.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Sprint.
 */
@Entity
@Table(name = "sprint")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Sprint implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private String numero;

    @Column(name = "pont_planejada")
    private String pontPlanejada;

    @Column(name = "pont_concluidas")
    private String pontConcluidas;

    @Column(name = "qtd_dias")
    private String qtdDias;

    @Column(name = "finalizada")
    private Boolean finalizada;

    @ManyToOne
    @JsonIgnoreProperties("sprints")
    private Equipe equipe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public Sprint numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPontPlanejada() {
        return pontPlanejada;
    }

    public Sprint pontPlanejada(String pontPlanejada) {
        this.pontPlanejada = pontPlanejada;
        return this;
    }

    public void setPontPlanejada(String pontPlanejada) {
        this.pontPlanejada = pontPlanejada;
    }

    public String getPontConcluidas() {
        return pontConcluidas;
    }

    public Sprint pontConcluidas(String pontConcluidas) {
        this.pontConcluidas = pontConcluidas;
        return this;
    }

    public void setPontConcluidas(String pontConcluidas) {
        this.pontConcluidas = pontConcluidas;
    }

    public String getQtdDias() {
        return qtdDias;
    }

    public Sprint qtdDias(String qtdDias) {
        this.qtdDias = qtdDias;
        return this;
    }

    public void setQtdDias(String qtdDias) {
        this.qtdDias = qtdDias;
    }

    public Boolean isFinalizada() {
        return finalizada;
    }

    public Sprint finalizada(Boolean finalizada) {
        this.finalizada = finalizada;
        return this;
    }

    public void setFinalizada(Boolean finalizada) {
        this.finalizada = finalizada;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public Sprint equipe(Equipe equipe) {
        this.equipe = equipe;
        return this;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sprint sprint = (Sprint) o;
        if (sprint.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sprint.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Sprint{" +
            "id=" + getId() +
            ", numero='" + getNumero() + "'" +
            ", pontPlanejada='" + getPontPlanejada() + "'" +
            ", pontConcluidas='" + getPontConcluidas() + "'" +
            ", qtdDias='" + getQtdDias() + "'" +
            ", finalizada='" + isFinalizada() + "'" +
            "}";
    }
}
