package com.oasis.apigestmenu.exception;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;

public class ExceptionResponse {
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss")
	private LocalDateTime timestamp;
	
	private String messageUsuario;
	private String messageDesenvolvedor;
	private String details;
	private Integer status;
	
	public ExceptionResponse() {}

	public ExceptionResponse(LocalDateTime timestamp, 
							 String messageUsuario, 
							 String messageDesenvolvedor,
							 String details, 
							 Integer status) {
		this.timestamp = timestamp;
		this.messageUsuario = messageUsuario;
		this.messageDesenvolvedor = messageDesenvolvedor;
		this.details = details;
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessageUsuario() {
		return messageUsuario;
	}

	public String getMessageDesenvolvedor() {
		return messageDesenvolvedor;
	}

	public String getDetails() {
		return details;
	}

	public Integer getStatus() {
		return status;
	}

}
