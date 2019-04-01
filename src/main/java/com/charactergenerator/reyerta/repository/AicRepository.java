package com.charactergenerator.reyerta.repository;

import com.charactergenerator.reyerta.domain.Aic;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Aic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AicRepository extends JpaRepository<Aic, Long> {

}
