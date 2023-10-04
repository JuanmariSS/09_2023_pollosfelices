package com.sinensia.pollosfelices.backend.auditoria.presentation.api.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.pollosfelices.backend.auditoria.business.model.RequestLog;
import com.sinensia.pollosfelices.backend.auditoria.business.services.RequestLogServices;

@RestController
@RequestMapping("/request-logs")
public class RequestLogController {

	@Autowired
	private RequestLogServices requestLogServices;
	
	@GetMapping
	public List<RequestLog> getAll(){
		return requestLogServices.getAll();
	}
}
