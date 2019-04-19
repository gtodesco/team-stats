package br.com.teamstats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teamstats.model.Equipe;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {

}
