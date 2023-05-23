package com.cg.movie.exception;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * 
	 * @param ex
	 * @param request
	 * @return exception details
	 */
	@ExceptionHandler(MovieException.class)
	public ResponseEntity<ExceptionDetails> movieAppException(MovieException ex, WebRequest request)
	{
		ExceptionDetails details=new ExceptionDetails(new Date(), ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionDetails> methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request)
	{
		ExceptionDetails details=new ExceptionDetails(new Date(), ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);
	}
	
}
