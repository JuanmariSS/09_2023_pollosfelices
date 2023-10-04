package com.sinensia.pollosfelices.backend.auditoria.presentation.api.restcontrollers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.pollosfelices.backend.auditoria.business.model.RequestLog;
import com.sinensia.pollosfelices.backend.auditoria.business.services.RequestLogServices;
import com.sinensia.pollosfelices.backend.presentation.config.PresentationException;

@RestController
@RequestMapping("/request-logs")
public class RequestLogController {

	@Autowired
	private RequestLogServices requestLogServices;
	
	//http://localhost:8080/request-logs?desde=2020-10-25 21:10:23&hasta=2020-10-25 21:20:30
	
	@GetMapping
	public List<RequestLog> getAll(@RequestParam(name="desde", required=false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date desde,
								   @RequestParam(name="hasta", required=false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date hasta){
				
		List<RequestLog> logs = null;
		
		if(desde != null && hasta != null) {
			logs = requestLogServices.getBetweenDates(desde, hasta);
		} else {
			logs = requestLogServices.getAll();
		}
		
		return logs;
	}
	
	@GetMapping("/{id}")
	public RequestLog read(@PathVariable Long id) {
		
		Optional<RequestLog> optional = requestLogServices.read(id);
		
		if(optional.isEmpty()) {
			throw new PresentationException("No existe el log con id " + id, HttpStatus.NOT_FOUND);
		}
		
		return optional.get();
	}
	
}
