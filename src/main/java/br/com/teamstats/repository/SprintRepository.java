package br.com.teamstats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teamstats.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long>{

}
