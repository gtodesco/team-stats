package br.com.teamstats.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teamstats.model.ScrumMaster;

@Repository
public interface ScrumMasterRepository extends JpaRepository<ScrumMaster, Long> {

	public Optional<ScrumMaster> findByEmailIgnoreCase(String email);
}
