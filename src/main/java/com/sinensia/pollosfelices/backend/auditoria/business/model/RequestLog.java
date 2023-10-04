package com.sinensia.pollosfelices.backend.auditoria.business.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class RequestLog implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Date timeStamp;
	private String ip;
	private String method;
	private String resource;
	private int statusCode;
	private String contentType;
	private long elapsedTime;
	
	public RequestLog() {
		
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
		RequestLog other = (RequestLog) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "RequestLog [id=" + id + ", timeStamp=" + timeStamp + ", ip=" + ip + ", method=" + method + ", resource="
				+ resource + ", statusCode=" + statusCode + ", contentType=" + contentType + ", elapsedTime="
				+ elapsedTime + "]";
	}
	
}
