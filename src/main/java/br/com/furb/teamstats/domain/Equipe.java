package br.com.furb.teamstats.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Equipe.
 */
@Entity
@Table(name = "equipe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Equipe implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email_scrum_master")
    private String emailScrumMaster;

    @OneToMany(mappedBy = "equipe")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Sprint> sprints = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "equipe_pessoas",
               joinColumns = @JoinColumn(name = "equipe_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "pessoas_id", referencedColumnName = "id"))
    private Set<Pessoa> pessoas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Equipe nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmailScrumMaster() {
        return emailScrumMaster;
    }

    public Equipe emailScrumMaster(String emailScrumMaster) {
        this.emailScrumMaster = emailScrumMaster;
        return this;
    }

    public void setEmailScrumMaster(String emailScrumMaster) {
        this.emailScrumMaster = emailScrumMaster;
    }

    public Set<Sprint> getSprints() {
        return sprints;
    }

    public Equipe sprints(Set<Sprint> sprints) {
        this.sprints = sprints;
        return this;
    }

    public Equipe addSprints(Sprint sprint) {
        this.sprints.add(sprint);
        sprint.setEquipe(this);
        return this;
    }

    public Equipe removeSprints(Sprint sprint) {
        this.sprints.remove(sprint);
        sprint.setEquipe(null);
        return this;
    }

    public void setSprints(Set<Sprint> sprints) {
        this.sprints = sprints;
    }

    public Set<Pessoa> getPessoas() {
        return pessoas;
    }

    public Equipe pessoas(Set<Pessoa> pessoas) {
        this.pessoas = pessoas;
        return this;
    }

    public Equipe addPessoas(Pessoa pessoa) {
        this.pessoas.add(pessoa);
        pessoa.getEquipes().add(this);
        return this;
    }

    public Equipe removePessoas(Pessoa pessoa) {
        this.pessoas.remove(pessoa);
        pessoa.getEquipes().remove(this);
        return this;
    }

    public void setPessoas(Set<Pessoa> pessoas) {
        this.pessoas = pessoas;
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
        Equipe equipe = (Equipe) o;
        if (equipe.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), equipe.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Equipe{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", emailScrumMaster='" + getEmailScrumMaster() + "'" +
            "}";
    }
}
