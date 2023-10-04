package com.sinensia.pollosfelices.backend.auditoria.business.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinensia.pollosfelices.backend.auditoria.business.model.RequestLog;
import com.sinensia.pollosfelices.backend.auditoria.business.services.RequestLogServices;
import com.sinensia.pollosfelices.backend.auditoria.integration.model.RequestLogPL;
import com.sinensia.pollosfelices.backend.auditoria.integration.repositories.RequestLogPLRepository;

@Service
public class RequestLogServicesImpl implements RequestLogServices{

	@Autowired
	private RequestLogPLRepository requestLogPLRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
		
	@Override
	@Transactional
	public Long create(RequestLog requestLog) {
		
		if(requestLog.getId() != null) {
			throw new IllegalStateException("No se puede crear un log de request que ya tiene id.");
		}
		
		RequestLogPL requestLogPL = mapper.map(requestLog, RequestLogPL.class);
		
		RequestLogPL createdRequestLogPL = requestLogPLRepository.save(requestLogPL);
		
		return createdRequestLogPL.getId();
	}

	@Override
	public Optional<RequestLog> read(Long id) {
		Optional<RequestLogPL> optionalPL = requestLogPLRepository.findById(id);
		return optionalPL.isEmpty() ? Optional.empty() : Optional.of(mapper.map(optionalPL.get(), RequestLog.class));	
	}

	@Override
	public List<RequestLog> getAll() {
		
		return requestLogPLRepository.findAll().stream()
				.map(x -> mapper.map(x, RequestLog.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<RequestLog> getBetweenDates(Date desde, Date hasta) {
		
		return requestLogPLRepository.findByTimeStampBetween(desde, hasta).stream()
				.map(x -> mapper.map(x, RequestLog.class))
				.collect(Collectors.toList());
	}

}
