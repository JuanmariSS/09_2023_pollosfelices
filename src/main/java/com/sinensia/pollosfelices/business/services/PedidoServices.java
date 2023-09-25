package com.sinensia.pollosfelices.business.services;

import java.util.List;
import java.util.Optional;

import com.sinensia.pollosfelices.business.model.Pedido;

public interface PedidoServices {

	Long create(Pedido pedido);
	
	Optional<Pedido> read(Pedido pedido);
	
	List<Pedido> getAll();
	
}
