package com.sinensia.pollosfelices.backend.integration.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sinensia.pollosfelices.backend.integration.model.CategoriaPL;
import com.sinensia.pollosfelices.backend.integration.model.ProductoPL;

@Repository
public interface ProductoPLRepository extends JpaRepository<ProductoPL, Long>{

	List<ProductoPL> findByPrecioBetween(double min, double max);
	
	List<ProductoPL> findByFechaAltaBetween(Date desde, Date hasta);
	
	List<ProductoPL> findByDescatalogadoTrue();
	
	List<ProductoPL> findByNombreLikeIgnoreCase(String nombre);
	
	List<ProductoPL> findByCategoria(CategoriaPL categoria);
	
	@Query("UPDATE ProductoPL p SET p.precio = p.precio + (p.precio * :porcentaje / 100) WHERE p IN :productos")
	@Modifying
	int incrementarPrecios(List<ProductoPL> productos, double porcentaje);
	
	@Query("UPDATE ProductoPL p SET p.precio = p.precio + (p.precio * :porcentaje / 100) WHERE p.codigo IN :codigos")
	@Modifying
	int incrementarPrecios(long[] codigos, double porcentaje);

	@Query(value="SELECT C.ID, "
			   + "       C.NOMBRE, "
			   + "       COUNT(P.NOMBRE) "
			   + "FROM   CATEGORIAS C LEFT JOIN PRODUCTOS P ON P.ID_CATEGORIA = C.ID "
			   + "GROUP BY C.ID "
			   + "ORDER BY C.ID ", nativeQuery=true)
	List<Object[]> getEstadisticaNumeroProductos();
	
	@Query(value="SELECT C.ID, "
			   + "       C.NOMBRE, "
			   + "       AVG(P.PRECIO) "
			   + "FROM   CATEGORIAS C LEFT JOIN PRODUCTOS P ON P.ID_CATEGORIA = C.ID "
			   + "GROUP BY C.ID "
			   + "ORDER BY C.ID ", nativeQuery=true)
	List<Object[]> getEstadisticaPrecioMedio();
	
}
