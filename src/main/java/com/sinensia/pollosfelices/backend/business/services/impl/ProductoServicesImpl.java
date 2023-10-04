package com.sinensia.pollosfelices.backend.business.services.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinensia.pollosfelices.backend.business.model.Categoria;
import com.sinensia.pollosfelices.backend.business.model.Producto;
import com.sinensia.pollosfelices.backend.business.model.dtos.Producto1DTO;
import com.sinensia.pollosfelices.backend.business.model.dtos.Producto2DTO;
import com.sinensia.pollosfelices.backend.business.services.ProductoServices;
import com.sinensia.pollosfelices.backend.integration.model.CategoriaPL;
import com.sinensia.pollosfelices.backend.integration.model.ProductoPL;
import com.sinensia.pollosfelices.backend.integration.repositories.ProductoPLRepository;

@Service
public class ProductoServicesImpl implements ProductoServices{

	@Autowired
	private ProductoPLRepository productoPLRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	private SimpleDateFormat sdf;
	
	@Override
	@Transactional
	public Long create(Producto producto) {
		
		if(producto.getCodigo() != null) {
			throw new IllegalStateException("No se puede crear un producto que ya tiene código.");
		}
		
		ProductoPL productoPL = mapper.map(producto, ProductoPL.class);
		
		ProductoPL createdProductoPL = productoPLRepository.save(productoPL);
		
		return createdProductoPL.getCodigo();
	}

	@Override
	public Optional<Producto> read(Long codigo) {
		Optional<ProductoPL> optionalPL = productoPLRepository.findById(codigo);
		return optionalPL.isEmpty() ? Optional.empty() : Optional.of(mapper.map(optionalPL.get(), Producto.class));	
	}

	@Override
	@Transactional
	public void update(Producto producto) {
		
		Long codigo = producto.getCodigo();
		
		if(codigo == null) {
			throw new IllegalArgumentException("No se puede actualizar un producto con código null.");
		}
		
		boolean existe = productoPLRepository.existsById(codigo);
		
		if(!existe) {
			throw new IllegalArgumentException("No existe el producto con código " + codigo);
		}
		
		ProductoPL productoPL = mapper.map(producto, ProductoPL.class);
		
		productoPLRepository.save(productoPL);
		
	}

	@Override
	@Transactional
	public void delete(Long codigo) {
		
		boolean existe = productoPLRepository.existsById(codigo);
		
		if(!existe) {
			throw new IllegalArgumentException("No existe el producto con código " + codigo);
		}
		
		productoPLRepository.deleteById(codigo);
		
	}

	@Override
	public List<Producto> getAll() {
		return convertProductosPLToProductos(productoPLRepository.findAll());
	}

	@Override
	public List<Producto> getBetweenPriceRange(double min, double max) {
		return convertProductosPLToProductos(productoPLRepository.findByPrecioBetween(min, max));
	}

	@Override
	public List<Producto> getBetweenDates(Date desde, Date hasta) {
		return convertProductosPLToProductos(productoPLRepository.findByFechaAltaBetween(desde, hasta));
	}

	@Override
	public List<Producto> getDescatalogados() {
		return convertProductosPLToProductos(productoPLRepository.findByDescatalogadoTrue());
	}
  
	@Override
	public List<Producto> getByNombreLikeIgnoreCase(String texto) {
		return convertProductosPLToProductos(productoPLRepository.findByNombreLikeIgnoreCase("%" + texto + "%"));
	}

	@Override
	public List<Producto> getByCategoria(Categoria categoria) {
		
		CategoriaPL categoriaPL = mapper.map(categoria, CategoriaPL.class);
	
		return convertProductosPLToProductos(productoPLRepository.findByCategoria(categoriaPL));
	}

	@Override
	public int getNumeroTotalProductos() {
		return (int) productoPLRepository.count();
	}

	@Override
	public void incrementarPrecio(List<Producto> productos, double porcentaje) {
		
		List<ProductoPL> productosPL = productos.stream()
				.map(x -> mapper.map(x, ProductoPL.class))
				.collect(Collectors.toList());
		
		productoPLRepository.incrementarPrecios(productosPL, porcentaje);
		
	}

	@Override
	public void incrementarPrecio(long[] codigos, double porcentaje) {
		productoPLRepository.incrementarPrecios(codigos, porcentaje);
	}

	@Override
	public Map<Categoria, Integer> getEstadisticaNumeroProductosByCategoria() {
		
		List<Object[]> resultados = productoPLRepository.getEstadisticaNumeroProductos();
		
		Map<Categoria, Integer> mapaResultados = new HashMap<>();;
		
		resultados.stream().forEach(x -> {
			Categoria categoria = new Categoria();
			categoria.setCodigo((Long) x[0]);
			categoria.setNombre((String) x[1]);
			mapaResultados.put(categoria, ((Long) x[2]).intValue());
		});
		
		return mapaResultados;
	}

	@Override
	public Map<Categoria, Double> getEstadisticaPrecioMedioProductosByCategoria() {
		
		List<Object[]> resultados = productoPLRepository.getEstadisticaPrecioMedio();
		
		Map<Categoria, Double> mapaResultados = new HashMap<>();;
		
		resultados.stream().forEach(x -> {
			Categoria categoria = new Categoria();
			categoria.setCodigo((Long) x[0]);
			categoria.setNombre((String) x[1]);
			BigDecimal precioMediAsBigDecimal = (BigDecimal) x[2];
			Double precioMedio = precioMediAsBigDecimal != null ? precioMediAsBigDecimal.doubleValue() : null;
			precioMedio = precioMedio != null ? Math.round(precioMedio * 100.0) / 100.0 : null;
			mapaResultados.put(categoria, precioMedio);
		});
		
		return mapaResultados;
	}
	
	@Override
	public List<Producto1DTO> getProducto1DTOs() {
		return productoPLRepository.getProductos1DTO();
	}

	@Override
	public List<Producto2DTO> getProducto2DTOs() {
	
		return productoPLRepository.getProductos2DTO().stream()
				.map(x -> new Producto2DTO((String )x[0], sdf.format((Date) x[1])))
				.collect(Collectors.toList());
		
	}
	
	// *********************************************************
	//
	// Private Methods
	//
	// *********************************************************
	
	private List<Producto> convertProductosPLToProductos(List<ProductoPL> productosPL){
		
		return productosPL.stream()
				.map(x -> mapper.map(x, Producto.class))
				.collect(Collectors.toList());
	}

}
