package br.com.artkou.repository;

import br.com.artkou.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    @Modifying
    @Query("UPDATE PersonEntity p SET p.enabled = false WHERE p.id =:id")
    void disablePerson(@Param("id") Long id);
}
