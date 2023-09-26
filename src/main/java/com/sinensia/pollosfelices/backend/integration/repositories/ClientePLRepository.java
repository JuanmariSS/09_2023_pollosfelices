package com.sinensia.pollosfelices.backend.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sinensia.pollosfelices.backend.integration.model.ClientePL;

@Repository
public interface ClientePLRepository extends JpaRepository<ClientePL, Long>{

}
