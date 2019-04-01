package com.charactergenerator.reyerta.repository;

import com.charactergenerator.reyerta.domain.Arma;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Arma entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArmaRepository extends JpaRepository<Arma, Long> {

}
