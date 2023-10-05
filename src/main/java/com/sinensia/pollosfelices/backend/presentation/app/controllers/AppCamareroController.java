package com.sinensia.pollosfelices.backend.presentation.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sinensia.pollosfelices.backend.business.services.CamareroServices;

@Controller
@RequestMapping("/app")
public class AppCamareroController {

	@Autowired
	private CamareroServices camareroServices;
	
	@GetMapping("/camareros")
	public ModelAndView getAll(ModelAndView mav){
	
		mav.setViewName("camareros");
		mav.addObject("camareros", camareroServices.getAll());

		return mav;
	}
	
}
