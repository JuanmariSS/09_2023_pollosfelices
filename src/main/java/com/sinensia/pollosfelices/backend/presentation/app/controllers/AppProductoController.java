package com.sinensia.pollosfelices.backend.presentation.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sinensia.pollosfelices.backend.business.model.Producto;
import com.sinensia.pollosfelices.backend.business.services.ProductoServices;

@Controller
@RequestMapping("/app/productos")
public class AppProductoController {

	@Autowired
	private ProductoServices productoServices;
	
	@GetMapping
	public ModelAndView getAll(ModelAndView mav, @RequestParam(value="codigo", required=false) Long codigo){
	
		if (codigo != null) {
			Producto producto = productoServices.read(codigo).orElse(null);
			mav.addObject("producto", producto);
			mav.setViewName("ficha-producto");
		} else {
			List<Producto> productos =  productoServices.getAll();
			mav.addObject("productos", productos);
			mav.setViewName("productos");
		}
	
		return mav;
	}
	
}
