package br.com.residencia.biblioteca.exception;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handlerAllExceptions(
			Exception ex, WebRequest request
			){
		List<String> detalhes = new ArrayList<>();
		detalhes.add(ex.getLocalizedMessage());
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorResponse error = new ErrorResponse(httpStatus.value(),
				"Erro ao processar a requisição", detalhes);
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementFoundException.class)
	public final ResponseEntity<Object> handlerNoSuchElementFoundException(
			NoSuchElementFoundException ex, WebRequest request){
		List<String> detalhes = new ArrayList<>();
		detalhes.add(ex.getLocalizedMessage());
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		ErrorResponse error = new ErrorResponse(httpStatus.value(),
				"O registro buscado não foi encontrado", detalhes);
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
}






