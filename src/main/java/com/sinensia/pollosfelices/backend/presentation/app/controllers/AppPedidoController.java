package com.sinensia.pollosfelices.backend.presentation.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sinensia.pollosfelices.backend.business.services.PedidoServices;

@Controller
@RequestMapping("/app")
public class AppPedidoController {

	@Autowired
	private PedidoServices pedidoServices;
	
	@GetMapping("/pedidos")
	public ModelAndView getAll(ModelAndView mav){

		mav.setViewName("pedidos");
		mav.addObject("pedidos", pedidoServices.getAllPedido1DTO());

		return mav;
	}
	
}
