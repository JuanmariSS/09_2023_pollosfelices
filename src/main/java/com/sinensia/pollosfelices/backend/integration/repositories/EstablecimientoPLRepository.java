package com.sinensia.pollosfelices.backend.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sinensia.pollosfelices.backend.integration.model.EstablecimientoPL;

@Repository
public interface EstablecimientoPLRepository extends JpaRepository<EstablecimientoPL, Long>{

}
