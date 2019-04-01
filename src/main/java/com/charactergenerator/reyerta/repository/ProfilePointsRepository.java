package com.charactergenerator.reyerta.repository;

import com.charactergenerator.reyerta.domain.ProfilePoints;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProfilePoints entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfilePointsRepository extends JpaRepository<ProfilePoints, Long> {

}
