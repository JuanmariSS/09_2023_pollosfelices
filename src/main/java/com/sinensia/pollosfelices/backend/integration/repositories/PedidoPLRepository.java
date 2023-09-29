package com.sinensia.pollosfelices.backend.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sinensia.pollosfelices.backend.integration.model.PedidoPL;

@Repository
public interface PedidoPLRepository extends JpaRepository<PedidoPL, Long>{
	
	@Query("UPDATE PedidoPL p SET p.estado = 'EN_PROCESO' WHERE p.numero = :numero")
	@Modifying
	int procesar(long numero);
	
	@Query("UPDATE PedidoPL p SET p.estado = 'PENDIENTE_ENTREGA' WHERE p.numero = :numero")
	@Modifying
	int entregar(long numero);
	
	@Query("UPDATE PedidoPL p SET p.estado = 'SERVIDO' WHERE p.numero = :numero")
	@Modifying
	int servir(long numero);
	
	@Query("UPDATE PedidoPL p SET p.estado = 'CANCELADO' WHERE p.numero = :numero")
	@Modifying
	int cancelar(long numero);
	
}
