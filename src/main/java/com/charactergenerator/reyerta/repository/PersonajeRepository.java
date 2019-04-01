package com.charactergenerator.reyerta.repository;

import com.charactergenerator.reyerta.domain.Personaje;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Personaje entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {

}
