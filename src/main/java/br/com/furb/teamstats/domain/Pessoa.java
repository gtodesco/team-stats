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
 * A Pessoa.
 */
@Entity
@Table(name = "pessoa")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "jhi_password")
    private String password;

    @Column(name = "ativ_concluidas")
    private Integer ativConcluidas;

    @Column(name = "pont_entregue")
    private Integer pontEntregue;

    @ManyToMany(mappedBy = "pessoas")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Equipe> equipes = new HashSet<>();

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

    public Pessoa nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public Pessoa email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public Pessoa password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAtivConcluidas() {
        return ativConcluidas;
    }

    public Pessoa ativConcluidas(Integer ativConcluidas) {
        this.ativConcluidas = ativConcluidas;
        return this;
    }

    public void setAtivConcluidas(Integer ativConcluidas) {
        this.ativConcluidas = ativConcluidas;
    }

    public Integer getPontEntregue() {
        return pontEntregue;
    }

    public Pessoa pontEntregue(Integer pontEntregue) {
        this.pontEntregue = pontEntregue;
        return this;
    }

    public void setPontEntregue(Integer pontEntregue) {
        this.pontEntregue = pontEntregue;
    }

    public Set<Equipe> getEquipes() {
        return equipes;
    }

    public Pessoa equipes(Set<Equipe> equipes) {
        this.equipes = equipes;
        return this;
    }

    public Pessoa addEquipes(Equipe equipe) {
        this.equipes.add(equipe);
        equipe.getPessoas().add(this);
        return this;
    }

    public Pessoa removeEquipes(Equipe equipe) {
        this.equipes.remove(equipe);
        equipe.getPessoas().remove(this);
        return this;
    }

    public void setEquipes(Set<Equipe> equipes) {
        this.equipes = equipes;
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
        Pessoa pessoa = (Pessoa) o;
        if (pessoa.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pessoa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pessoa{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", ativConcluidas=" + getAtivConcluidas() +
            ", pontEntregue=" + getPontEntregue() +
            "}";
    }
}
