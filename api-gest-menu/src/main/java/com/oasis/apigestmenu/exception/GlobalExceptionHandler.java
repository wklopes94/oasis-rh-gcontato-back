package com.oasis.apigestmenu.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	//handle specific exception
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex,
																WebRequest request){
		List<ExceptionResponse> erros = new ArrayList<>();
		
		ExceptionResponse errorDetails = new ExceptionResponse(LocalDateTime.now(),
															   "O Seu pedido contem erros. Verifique e volta a tentar. Caso persistir contacto o Administrador!!",
															   ex.getMessage(),
															   ExceptionUtils.getRootCauseMessage(ex),
															     HttpStatus.BAD_REQUEST.value()
															  );
		erros.add(errorDetails);		
		return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex,
																WebRequest request){
		List<ExceptionResponse> erros = new ArrayList<>();
		
		ExceptionResponse errorDetails = new ExceptionResponse(LocalDateTime.now(),
															   "O Recurso solicitado nao foi encontrado. Por favor verifique o seu pedido e volta a tentar!!",
															   ex.getMessage(),
															   ExceptionUtils.getRootCauseMessage(ex),
															     HttpStatus.NOT_FOUND.value()
															  );
		erros.add(errorDetails);		
		return new ResponseEntity<>(erros, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
																WebRequest request){
		List<ExceptionResponse> erros = new ArrayList<>();
		
		ExceptionResponse errorDetails = new ExceptionResponse(LocalDateTime.now(),
															   "Violação Integridade Base Dados. Por favor verifique o seu pedido e volta a tentar!!",
															   ex.getMessage(),
															   ExceptionUtils.getRootCauseMessage(ex),
															     HttpStatus.BAD_REQUEST.value()
															  );
		erros.add(errorDetails);		
		return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST 
				);
		
	}
	
	
	
	//handle Global Exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request){
		List<ExceptionResponse> erros = new ArrayList<>();
		
		ExceptionResponse errorDetails = new ExceptionResponse(LocalDateTime.now(),
															   "Internal Server ERROR!!",
															   ex.getMessage(),
															   ExceptionUtils.getRootCauseMessage(ex),
															     HttpStatus.INTERNAL_SERVER_ERROR.value()
															  );
		erros.add(errorDetails);		
		return new ResponseEntity<>(erros, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
}