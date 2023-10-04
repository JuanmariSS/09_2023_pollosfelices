package com.sinensia.pollosfelices.backend.auditoria.presentation.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sinensia.pollosfelices.backend.auditoria.business.services.RequestLogServices;

@Controller
@RequestMapping("/auditoria/logs")
public class AppRequestLogController {

	@Autowired
	private RequestLogServices requestLogServices;
	
	@GetMapping
	public ModelAndView getRequestLogsPage(ModelAndView mav) {
		
		mav.setViewName("auditoria/request-logs");
		mav.addObject("logs", requestLogServices.getAll());
		
		return mav;
	}
}
