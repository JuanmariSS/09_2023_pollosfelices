package com.sinensia.pollosfelices.backend.business.services.dummy.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sinensia.pollosfelices.backend.business.model.Categoria;
import com.sinensia.pollosfelices.backend.business.model.Producto;
import com.sinensia.pollosfelices.backend.business.services.ProductoServices;

public class ProductoDummyServicesImpl implements ProductoServices{

	private final Map<Long, Producto> PRODUCTOS = new HashMap<>();
	
	public ProductoDummyServicesImpl() {
		init();
	}
	
	@Override
	public Long create(Producto producto) {
		
		if(producto.getCodigo() != null) {
			throw new IllegalArgumentException("No se puede crear un producto que ya tiene código.");
		}
		
		Long codigo = System.currentTimeMillis();
		producto.setCodigo(codigo);
		PRODUCTOS.put(codigo, producto);
		
		return codigo;
	}

	@Override
	public Optional<Producto> read(Long codigo) {
		return Optional.ofNullable(PRODUCTOS.get(codigo));
	}
	
	@Override
	public void update(Producto producto) {
		
		Long codigo = producto.getCodigo();
		
		if(codigo == null) {
			throw new IllegalArgumentException("No se puede actualizar un producto que con código null.");
		}
		
		boolean existe = PRODUCTOS.containsKey(codigo);
		
		if(!existe) {
			throw new IllegalArgumentException("No existe el producto con código " + codigo);
		}
		
		PRODUCTOS.replace(codigo, producto);
	}

	@Override
	public void delete(Long codigo) {
		
		boolean existe = PRODUCTOS.containsKey(codigo);
		
		if(!existe) {
			throw new IllegalArgumentException("No existe el producto con código " + codigo);
		}
		
		PRODUCTOS.remove(codigo);
		
	}

	@Override
	public List<Producto> getAll() {
		return new ArrayList<>(PRODUCTOS.values());
	}

	@Override
	public List<Producto> getBetweenPriceRange(double min, double max) {
		
		List<Producto> productos = new ArrayList<>();
		
		for(Producto producto: PRODUCTOS.values()) {
			
			if(producto.getPrecio() >= min && producto.getPrecio() <= max) {
				productos.add(producto);
			}
		}
		
		return productos;
	}

	@Override
	public List<Producto> getBetweenDates(Date desde, Date hasta) {
		
		List<Producto> productos = new ArrayList<>();
		
		for(Producto producto: PRODUCTOS.values()) {
			
			if(producto.getFechaAlta() != null && producto.getFechaAlta().after(desde) && producto.getFechaAlta().before(hasta)) {
				productos.add(producto);
			}
		}
		
		return productos;
	}

	@Override
	public List<Producto> getDescatalogados() {
		
		return PRODUCTOS.values().stream()
				.filter(x -> x.isDescatalogado())
				.collect(Collectors.toList());
	}

	@Override
	public List<Producto> getByNombreLikeIgnoreCase(String texto) {
		
		return PRODUCTOS.values().stream()
				.filter(x -> x.getNombre().toLowerCase().contains(texto.toLowerCase()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Producto> getByCategoria(Categoria categoria) {
		
		List<Producto> productos = new ArrayList<>();
		
		for(Producto producto: PRODUCTOS.values()) {
			if(producto.getCategoria().equals(categoria)) {
				productos.add(producto);
			}
		}
		
		return productos;
	}

	@Override
	public int getNumeroTotalProductos() {
		return PRODUCTOS.size();
	}

	@Override
	public void incrementarPrecio(List<Producto> productos, double porcentaje) {
		
		productos.stream().forEach(x -> {
			double nuevoPrecio = x.getPrecio() + x.getPrecio() * porcentaje / 100;
			PRODUCTOS.get(x.getCodigo()).setPrecio(nuevoPrecio);
		});
	}

	@Override
	public void incrementarPrecio(long[] codigos, double porcentaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Categoria, Integer> getEstadisticaNumeroProductosByCategoria() {
		
		Map<Categoria, Integer> resultado = new HashMap<>();
		
		for(Producto producto: PRODUCTOS.values()) {
			
			Categoria categoria = producto.getCategoria();
			
			if(!resultado.containsKey(producto.getCategoria())) {
				resultado.put(categoria, 1);
			} else {
				int cantidadActual = resultado.get(categoria);
				resultado.replace(categoria, cantidadActual + 1);	
			}
			
		}
		
		return resultado;
	}
	
	@Override
	public Map<Categoria, Double> getEstadisticaPrecioMedioProductosByCategoria() {
		
		// TODO Refactorizar para disponer siempre de la media dividiendo el anterior por dos
		// TODO Mirar de crear <Categoria, List<Double>> para acumular
		

		Map<Categoria, Double> resultado = new HashMap<>();
		Map<Categoria, Integer> cuentas = new HashMap<>();
		Map<Categoria, Double> acumulados = new HashMap<>();
		
		for(Producto producto : PRODUCTOS.values()) {
			
			Categoria categoria = producto.getCategoria();
			
			if(!cuentas.containsKey(categoria)){
				cuentas.put(categoria, 1);
				acumulados.put(categoria, producto.getPrecio());
			} else {
				int cantidadActual = cuentas.get(categoria);
				double importeActual = acumulados.get(categoria);
				cuentas.put(categoria, cantidadActual + 1);
				acumulados.put(categoria, importeActual + producto.getPrecio());
			}
			
		}
		
		for(Categoria categoria: cuentas.keySet()) {
			resultado.put(categoria, acumulados.get(categoria) / cuentas.get(categoria));
		}
		
		return resultado;
	}
	
	// *********************************************************
	//
	// Private Methods
	//
	// *********************************************************
	
	private void init() {
		
		Categoria c1 = new Categoria();
		Categoria c2 = new Categoria();
		Categoria c3 = new Categoria();
		Categoria c4 = new Categoria();
		
		c1.setNombre("TAPA");
		c2.setNombre("LICOR");
		c3.setNombre("REFRESCO");
		c4.setNombre("REPOSTERIA");
		
		Date fecha1 = null;
		Date fecha2 = null;
		Date fecha3 = null;
		Date fecha4 = null;
		Date fecha5 = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			fecha1 = sdf.parse("01/10/2022");
			fecha2 = sdf.parse("02/10/2022");
			fecha3 = sdf.parse("03/10/2022");
			fecha4 = sdf.parse("04/10/2022");
			fecha5 = sdf.parse("04/10/2022");
		} catch(Exception e) {
			
		}
		
		Producto p1 = new Producto();
		Producto p2 = new Producto();
		Producto p3 = new Producto();
		Producto p4 = new Producto();
		Producto p5 = new Producto();
		
		p1.setCodigo(100L);
		p1.setNombre("Vodka");
		p1.setCategoria(c2);
		p1.setPrecio(6.0);
		p1.setDescatalogado(false);
		p1.setDescripcion("Delicioso Vodka de la mejor calidad.");
		p1.setFechaAlta(fecha1);
		
		p2.setCodigo(101L);
		p2.setNombre("Patatas Bravas");
		p2.setCategoria(c1);
		p2.setPrecio(5.75);
		p2.setDescatalogado(true);
		p2.setDescripcion("Patatas bravas con salsa picante.");
		p2.setFechaAlta(fecha2);
		
		p3.setCodigo(102L);
		p3.setNombre("Pimientos fritos");
		p3.setCategoria(c1);
		p3.setPrecio(4.0);
		p3.setDescatalogado(false);
		p3.setDescripcion("Deliciosos pimientos.");
		p3.setFechaAlta(fecha3);
	
		p4.setCodigo(103L);
		p4.setNombre("Crema Catalana");
		p4.setCategoria(c4);
		p4.setPrecio(3.40);
		p4.setDescatalogado(false);
		p4.setDescripcion("Postre de crema");
		p4.setFechaAlta(fecha4);
		
		p5.setCodigo(104L);
		p5.setNombre("Cocacola Zero");
		p5.setCategoria(c3);
		p5.setPrecio(1.80);
		p5.setDescatalogado(false);
		p5.setDescripcion("Lata de CocaCola Zero 33cl");
		p5.setFechaAlta(fecha5);
		
		PRODUCTOS.put(p1.getCodigo(), p1);
		PRODUCTOS.put(p2.getCodigo(), p2);
		PRODUCTOS.put(p3.getCodigo(), p3);
		PRODUCTOS.put(p4.getCodigo(), p4);
		PRODUCTOS.put(p5.getCodigo(), p5);
		
	}

}
