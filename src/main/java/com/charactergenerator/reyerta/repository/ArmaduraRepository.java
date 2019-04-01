package com.charactergenerator.reyerta.repository;

import com.charactergenerator.reyerta.domain.Armadura;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Armadura entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArmaduraRepository extends JpaRepository<Armadura, Long> {

}
