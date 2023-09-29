package com.sinensia.pollosfelices.backend.integration.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.jdbc.Sql;

import com.sinensia.pollosfelices.backend.integration.model.CategoriaPL;
import com.sinensia.pollosfelices.backend.integration.model.ProductoPL;

@DataJpaTest
@Sql(scripts={"/data/h2/schema_test.sql","/data/h2/data_test.sql"})
public class ProductoPLRepositoryTest {

	@Autowired
	private ProductoPLRepository productoPLRepository;
	
	@Test
	@Disabled
	void findByPrecioBetweenTest() {
		fail("Not implemented yet");
	}
	
	@Test
	@Disabled
	void findByFechaAltaBetweenTest() {
		fail("Not implemented yet");
	}
	
	@Test
	@Disabled
	void findByDescatalogadoTrueTest() {
		fail("Not implemented yet");
	}
	
	@Test
	@Disabled
	void findByNombreLikeIgnoreCaseTest() {
		fail("Not implemented yet");
	}
	
	@Test
	void findByCategoriaTest() {
		
		ProductoPL p1 = new ProductoPL();
		ProductoPL p2 = new ProductoPL();
		ProductoPL p3 = new ProductoPL();
		
		p1.setCodigo(137L);
		p2.setCodigo(138L);
		p3.setCodigo(139L);
		
		List<ProductoPL> productosEsperados = Arrays.asList(p1, p2, p3);
		
		CategoriaPL categoriaPL = new CategoriaPL();
		categoriaPL.setCodigo(7L);
		
		List<ProductoPL> productosPL = productoPLRepository.findByCategoria(categoriaPL);
		
		assertEquals(3, productosPL.size());
		assertTrue(productosPL.containsAll(productosEsperados));
		
	}
	
	@Test
	@Disabled
	void incrementarPreciosTestA() {
		fail("Not implemented yet");
	}
	
	@Test
	@Disabled
	void incrementarPreciosTestB() {
		fail("Not implemented yet");
	}

	@Test
	void getEstadisticaNumeroProductosTest() {

		List<Object[]> resultados = productoPLRepository.getEstadisticaNumeroProductos();
		assertEquals(12, resultados.size());
		
		assertEquals(resultados.get(0)[0], 1L);
		assertEquals(resultados.get(0)[1], "LICOR");
		assertEquals(resultados.get(0)[2], 6L);
		
		assertEquals(resultados.get(8)[0], 9L);
		assertEquals(resultados.get(8)[1], "BOCADILLO");
		assertEquals(resultados.get(8)[2], 11L);
		
		assertEquals(resultados.get(11)[0], 12L);
		assertEquals(resultados.get(11)[1], "ZUMO");
		assertEquals(resultados.get(11)[2], 0L);
	
	}
	
	@Test
	@Disabled
	void getEstadisticaPrecioMedioTest() {
		fail("Not implemented yet");
	}
}
