package com.sinensia.pollosfelices.backend.presentation.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sinensia.pollosfelices.backend.business.model.Producto;
import com.sinensia.pollosfelices.backend.business.services.ProductoServices;

@Controller
@RequestMapping("/app")
public class AppProductoController {

	@Autowired
	private ProductoServices productoServices;
	
	@GetMapping("/productos")
	public ModelAndView getAll(ModelAndView mav, 
							   @RequestParam(value="codigo", required=false) Long codigo,
							   @RequestParam(name="texto", required=false) String texto){
	
		if (codigo != null) {
			Producto producto = productoServices.read(codigo).orElse(null);
			mav.addObject("producto", producto);
			mav.setViewName("ficha-producto");
		} else {
			mav.setViewName("productos");
			if(texto!= null) {
				mav.addObject("productos", productoServices.getByNombreLikeIgnoreCase(texto));
				mav.addObject("texto", texto);
			} else {
				mav.addObject("productos", productoServices.getAll());
			}

		}
	
		return mav;
	}
	
	@GetMapping("/formulario-filtro")
	public String getFormularioFiltro() {
		return "formulario-filtro-productos";
	}
	
}
