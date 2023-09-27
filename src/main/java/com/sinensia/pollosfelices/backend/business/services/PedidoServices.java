package com.sinensia.pollosfelices.backend.business.services;

import java.util.List;
import java.util.Optional;

import com.sinensia.pollosfelices.backend.business.model.Pedido;

public interface PedidoServices {

	Long create(Pedido pedido);
	
	Optional<Pedido> read(Long numero);
	
	List<Pedido> getAll();
	
}
