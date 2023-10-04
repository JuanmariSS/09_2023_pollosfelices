package com.sinensia.pollosfelices.backend.auditoria.integration.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sinensia.pollosfelices.backend.auditoria.integration.model.RequestLogPL;

@Repository
public interface RequestLogPLRepository extends JpaRepository<RequestLogPL, Long>{

	List<RequestLogPL> findByTimeStampBetweenOrderByTimeStampDesc(Date desde, Date hasta);
}
