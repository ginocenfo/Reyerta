package com.charactergenerator.reyerta.repository;

import com.charactergenerator.reyerta.domain.Dado;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Dado entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DadoRepository extends JpaRepository<Dado, Long> {

}
