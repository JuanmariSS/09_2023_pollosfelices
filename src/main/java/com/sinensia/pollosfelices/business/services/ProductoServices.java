package com.sinensia.pollosfelices.business.services;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.sinensia.pollosfelices.business.model.Categoria;
import com.sinensia.pollosfelices.business.model.Producto;

public interface ProductoServices {

	/**
	 * Devuelve el la ID que le ha otorgado el sistema al nuevo producto.
	 * 
	 * Si la ID del producto no es null lanza IllegalStateException
	 * 
	 */
	Long create(Producto producto);
	
	Optional<Producto> read(Long codigo);
	
	void update(Producto producto);
	void delete(Long codigo);
	
	List<Producto> getAll();
	
	/**
		Incluye los extremos
	 */
	List<Producto> getBetweenPriceRange(double min, double max);
	
	/**
		No incluye los extremos
	 */
	List<Producto> getBetweenDates(Date desde, Date hasta);
	
	List<Producto> getDescatalogados();
	
	/**
	 * Devuelve todos los productos cuyo nombre contiene el texto
	 * pasado como argumento.
	 * 
	 * Se ignoran mayusculas/minusculas
	 * 
	 * Ejemplo:
	 * 
	 * texto: "ca" devolvería: "Caracoles", "crema catalatna", "queso de vaca"
	 * 
	 */
	List<Producto> getByNombreLikeIgnoreCase(String texto);
	
	List<Producto> getByCategoria(Categoria categoria);
	
	int getNumeroTotalProductos();
	
	/**
	 * 
	 * Incrementa un porcentaje el precio de los productos que se pasan.
	 * 
	 * Ejemplo:
	 * 
	 * Si porcentaje = 2.0 ---> los incrementa un 2.0%
	 * 
	 */
	void incrementarPrecio(List<Producto> productos, double porcentaje);
	
	/**
	 * 
	 * Incrementa un porcentaje el precio de los productos que se pasan (por código).
	 * 
	 * Ejemplo:
	 * 
	 * Si porcentaje = 2.0 ---> los incrementa un 2.0%
	 * 
	 */
	
	void incrementarPrecio(long[] codigos, double porcentaje);
	
	Map<Categoria, Integer> getEstadisticaNumeroProductosByCategoria();
	Map<Categoria, Double> getEstadisticaPrecioMedioProductosByCategoria();
	

}
