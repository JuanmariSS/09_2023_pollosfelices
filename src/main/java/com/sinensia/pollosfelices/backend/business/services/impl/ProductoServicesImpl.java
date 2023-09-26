package com.sinensia.pollosfelices.backend.business.services.impl;

import java.util.Date;
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
import com.sinensia.pollosfelices.backend.business.services.ProductoServices;
import com.sinensia.pollosfelices.backend.integration.model.ProductoPL;
import com.sinensia.pollosfelices.backend.integration.repositories.ProductoPLRepository;

@Service
public class ProductoServicesImpl implements ProductoServices{

	@Autowired
	private ProductoPLRepository productoPLRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	@Transactional
	public Long create(Producto producto) {
		
		if(producto.getCodigo() != null) {
			throw new IllegalArgumentException("No se puede crear un producto que ya tiene código.");
		}
		
		Long codigo = System.currentTimeMillis();
		
		producto.setCodigo(codigo);
		
		ProductoPL productoPL = mapper.map(producto, ProductoPL.class);
		
		productoPLRepository.save(productoPL);
		
		return codigo;
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
			throw new IllegalArgumentException("No se puede actualizar un producto que con código null.");
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
	
		return productoPLRepository.findAll().stream()
				.map(x -> mapper.map(x, Producto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<Producto> getBetweenPriceRange(double min, double max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> getBetweenDates(Date desde, Date hasta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> getDescatalogados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> getByNombreLikeIgnoreCase(String texto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> getByCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumeroTotalProductos() {
		return (int) productoPLRepository.count();
	}

	@Override
	public void incrementarPrecio(List<Producto> productos, double porcentaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void incrementarPrecio(long[] codigos, double porcentaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Categoria, Integer> getEstadisticaNumeroProductosByCategoria() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Categoria, Double> getEstadisticaPrecioMedioProductosByCategoria() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
