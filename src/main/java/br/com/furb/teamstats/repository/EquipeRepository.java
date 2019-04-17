package br.com.furb.teamstats.repository;

import br.com.furb.teamstats.domain.Equipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Equipe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {

    @Query(value = "select distinct equipe from Equipe equipe left join fetch equipe.pessoas",
        countQuery = "select count(distinct equipe) from Equipe equipe")
    Page<Equipe> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct equipe from Equipe equipe left join fetch equipe.pessoas")
    List<Equipe> findAllWithEagerRelationships();

    @Query("select equipe from Equipe equipe left join fetch equipe.pessoas where equipe.id =:id")
    Optional<Equipe> findOneWithEagerRelationships(@Param("id") Long id);

}
