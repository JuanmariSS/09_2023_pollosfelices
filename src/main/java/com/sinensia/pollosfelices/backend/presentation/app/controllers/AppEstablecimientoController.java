package com.sinensia.pollosfelices.backend.presentation.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sinensia.pollosfelices.backend.business.services.EstablecimientoServices;

@Controller
@RequestMapping("/app")
public class AppEstablecimientoController {

	@Autowired
	private EstablecimientoServices establecimientoServices;
	
	@GetMapping("/establecimientos")
	public ModelAndView getAll(ModelAndView mav){
	
		mav.setViewName("establecimientos");
		mav.addObject("establecimientos", establecimientoServices.getAll());

		return mav;
	}
	
	
}
