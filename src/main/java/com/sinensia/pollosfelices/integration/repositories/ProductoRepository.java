package com.sinensia.pollosfelices.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sinensia.pollosfelices.business.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
