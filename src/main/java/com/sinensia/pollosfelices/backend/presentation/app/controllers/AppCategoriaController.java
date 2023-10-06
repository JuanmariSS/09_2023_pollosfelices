package com.sinensia.pollosfelices.backend.presentation.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sinensia.pollosfelices.backend.business.model.Categoria;
import com.sinensia.pollosfelices.backend.business.services.CategoriaServices;

@Controller
@RequestMapping("/app")
public class AppCategoriaController {

	@Autowired
	private CategoriaServices categoriaServices;
	
	@GetMapping("/categorias")
	public ModelAndView getAll(ModelAndView mav) {
		
		mav.addObject("categorias", categoriaServices.getAll());
		mav.setViewName("categorias");
		
		return mav;
	}
	
	// 1.- Necesitamos un end-point para devolver el formulario (GET). 
	//     Vamos a aprovecharnos de las prestaciones de Spring Web para instanciar una nueva categoría y pasárse al formulario
		
	@GetMapping("/alta-categoria")
	public ModelAndView getFormularioAlta(ModelAndView mav) {
		mav.addObject("categoria", new Categoria());
		mav.setViewName("formulario-alta-categoria");
		return mav;
	}
	
	// 2.- Necesitamos un end-point para recoger la Categoria enviada por el formulario y darla de alta (categoriaServices.create())
	//     El end-point atenderá a peticiones POST
	//     Este end-point debería encargarse de la validación de los datos.
	//     
	//     La respuesta será una redirección para que el cliente (navegador) solicite la página de listado de categorias
	
	@PostMapping("/alta-categoria")
	public RedirectView crearCategoria(@ModelAttribute Categoria categoria) {
		
		if(categoria.getNombre() == null || categoria.getNombre().equals("")) {
			// TODO 
		}
		
		categoriaServices.create(categoria);
		return new RedirectView("categorias");
	}
}
