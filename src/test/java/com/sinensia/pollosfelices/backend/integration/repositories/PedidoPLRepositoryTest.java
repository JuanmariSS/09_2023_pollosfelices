package com.sinensia.pollosfelices.backend.integration.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.sinensia.pollosfelices.backend.integration.model.EstadoPedidoPL;
import com.sinensia.pollosfelices.backend.integration.model.PedidoPL;

@DataJpaTest
@Sql(scripts={"/data/h2/schema_test.sql","/data/h2/data_test.sql"})
public class PedidoPLRepositoryTest {

	@Autowired
	private PedidoPLRepository pedidoPLRepository;
	
	@Test
	void procesarTest() {
		
		Long numeroPedido = 1014L;
		
		int numeroEntidadesAfectadas = pedidoPLRepository.procesar(numeroPedido);
		
		Optional<PedidoPL> optional = pedidoPLRepository.findById(numeroPedido);
		EstadoPedidoPL estadoPedidoPL = optional.get().getEstado();
		
		assertEquals(1, numeroEntidadesAfectadas);
		assertEquals(estadoPedidoPL, EstadoPedidoPL.EN_PROCESO);
		
	}
	
	@Test
	void entregarTest() {
		
		Long numeroPedido = 1010L;
		
		int numeroEntidadesAfectadas = pedidoPLRepository.entregar(numeroPedido);
		
		Optional<PedidoPL> optional = pedidoPLRepository.findById(numeroPedido);
		EstadoPedidoPL estadoPedidoPL = optional.get().getEstado();
		
		assertEquals(1, numeroEntidadesAfectadas);
		assertEquals(estadoPedidoPL, EstadoPedidoPL.PENDIENTE_ENTREGA);
	}
	
	@Test
	void servirTest() {
		
		Long numeroPedido = 1012L;
		
		int numeroEntidadesAfectadas = pedidoPLRepository.servir(numeroPedido);
		
		Optional<PedidoPL> optional = pedidoPLRepository.findById(numeroPedido);
		EstadoPedidoPL estadoPedidoPL = optional.get().getEstado();
		
		assertEquals(1, numeroEntidadesAfectadas);
		assertEquals(estadoPedidoPL, EstadoPedidoPL.SERVIDO);
	}
	
	@Test
	void cancelarTest() {
		
		Long numeroPedido = 1010L;
		
		int numeroEntidadesAfectadas = pedidoPLRepository.cancelar(numeroPedido);
		assertEquals(1,numeroEntidadesAfectadas);
		
		Optional<PedidoPL> optional = pedidoPLRepository.findById(numeroPedido);
		EstadoPedidoPL estadoPedidoPL = optional.get().getEstado();
		
		assertEquals(1, numeroEntidadesAfectadas);
		assertEquals(estadoPedidoPL, EstadoPedidoPL.CANCELADO);
	}
	
}
