package com.sinensia.pollosfelices.backend.business.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sinensia.pollosfelices.backend.business.model.Camarero;
import com.sinensia.pollosfelices.backend.business.model.Establecimiento;
import com.sinensia.pollosfelices.backend.business.model.Pedido;
import com.sinensia.pollosfelices.backend.integration.model.EstadoPedidoPL;
import com.sinensia.pollosfelices.backend.integration.model.PedidoPL;
import com.sinensia.pollosfelices.backend.integration.repositories.CamareroPLRepository;
import com.sinensia.pollosfelices.backend.integration.repositories.EstablecimientoPLRepository;
import com.sinensia.pollosfelices.backend.integration.repositories.PedidoPLRepository;

@ExtendWith(MockitoExtension.class)
class PedidoServicesImplTest {

	@Mock
	private PedidoPLRepository pedidoPLRepository;
	
	@Mock
	private CamareroPLRepository camareroPLRepository;
	
	@Mock
	private EstablecimientoPLRepository establecimientoPLRepository;
	
	@Mock
	private DozerBeanMapper mapper;
	
	@InjectMocks
	private PedidoServicesImpl pedidoServicesImpl;
	
	private Pedido pedido;
	private Pedido pedido2;
	private PedidoPL pedidoPL;
	private PedidoPL pedidoPL2;
	
	@BeforeEach
	void init() {
		initObjects();
	}
	
	@Test
	void creamos_pedido_ok() {
		
		Camarero camarero = new Camarero();
		Establecimiento establecimiento = new Establecimiento();
		
		camarero.setId(100L);
		establecimiento.setCodigo(100L);
		
		pedido.setNumero(null);
		pedido.setCamarero(camarero);
		pedido.setEstablecimiento(establecimiento);
		
		when(establecimientoPLRepository.existsById(100L)).thenReturn(true);
		when(camareroPLRepository.existsById(100L)).thenReturn(true);
		when(mapper.map(pedido, PedidoPL.class)).thenReturn(pedidoPL);
		
		pedidoServicesImpl.create(pedido);
		
		verify(pedidoPLRepository, times(1)).save(pedidoPL);
		
	}

	@Test
	@Disabled
	void testRead() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void obtenemos_todos_los_pedidos() {
		
		pedidoPL.setNumero(100L);
		pedidoPL2.setNumero(101L);
		
		when(pedidoPLRepository.findAll()).thenReturn(Arrays.asList(pedidoPL, pedidoPL2));
		when(mapper.map(pedidoPL,  Pedido.class)).thenReturn(pedido);
		when(mapper.map(pedidoPL2,  Pedido.class)).thenReturn(pedido2);
		
		List<Pedido> pedidos = pedidoServicesImpl.getAll();
		
		assertEquals(2, pedidos.size());
		assertNotNull(pedidos.get(0));
		assertNotNull(pedidos.get(1));
		assertTrue(pedidos.containsAll(Arrays.asList(pedido, pedido2)));
	}

	@Test
	void procesar_ok() {
		
		pedidoPL.setNumero(100L);
		pedidoPL.setEstado(EstadoPedidoPL.NUEVO);
		
		when(pedidoPLRepository.findById(100L)).thenReturn(Optional.of(pedidoPL));
		
		pedidoServicesImpl.procesar(100L);
		
		verify(pedidoPLRepository, times(1)).procesar(100L);
	}
	
	@Test
	void procesar_numero_no_econtrado() {
		
		when(pedidoPLRepository.findById(200L)).thenReturn(Optional.empty());			
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> { 
			pedidoServicesImpl.procesar(200L);
		});
		
		assertEquals("No existe el pedido número 200", exception.getMessage());
	}
	
	@Test
	void procesar_estado_no_permitido() {
		
		pedidoPL.setNumero(100L);
		pedidoPL.setEstado(EstadoPedidoPL.CANCELADO);
		
		when(pedidoPLRepository.findById(100L)).thenReturn(Optional.of(pedidoPL));
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			pedidoServicesImpl.procesar(100L);
		});
		
		assertEquals("No se puede pasar a estado 'EN_PROCESO' desde el estado 'CANCELADO'", exception.getMessage());
		
	}

	@Test
	@Disabled
	void testEntregar() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testServir() {
		fail("Not yet implemented");
	}

	@Test
	void cancelar_pedido_no_existente() {
		
		pedidoPL.setNumero(100L);
		
		when(pedidoPLRepository.findById(100L)).thenReturn(Optional.empty());
				
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			pedidoServicesImpl.cancelar(100L);
		});
		
		assertEquals("No existe el pedido número 100", exception.getMessage());
	}
	
	@Test
	void cancelar_pedido_cancelado() {
		
		pedidoPL.setNumero(100L);
		pedidoPL.setEstado(EstadoPedidoPL.CANCELADO);
		
		when(pedidoPLRepository.findById(100L)).thenReturn(Optional.of(pedidoPL));
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			pedidoServicesImpl.cancelar(100L);
		});
		
		assertEquals("No se puede pasar a estado 'CANCELADO' desde el estado 'CANCELADO'", exception.getMessage());
	}
	
	// *********************************************************
	//
	// Private Methods
	//
	// *********************************************************

	private void initObjects() {
		
		pedido = new Pedido();
		pedido2 = new Pedido();
		pedidoPL = new PedidoPL();
		pedidoPL2 = new PedidoPL();
		
	}
}
