package com.sinensia.pollosfelices.backend.auditoria.integration.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="REQUEST_LOGS")
public class RequestLogPL implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="REQUEST_LOGS_SEQ")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TIME_STAMP")
	private Date timeStamp;
	
	private String ip;
	private String method;
	private String resource;
	private int statusCode;
	private String contentType;
	private long elapsedTime;
	
	public RequestLogPL() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestLogPL other = (RequestLogPL) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "RequestLogPL [id=" + id + ", timeStamp=" + timeStamp + ", ip=" + ip + ", method=" + method
				+ ", resource=" + resource + ", statusCode=" + statusCode + ", contentType=" + contentType
				+ ", elapsedTime=" + elapsedTime + "]";
	}

}
