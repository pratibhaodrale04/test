package com.val.globalexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.val.customexceptions.ProductDuplicateException;
import com.val.dto.ErrorResponse;

@ControllerAdvice
public class GlobalException {
	
	 @ExceptionHandler(ProductDuplicateException.class)
	    public ResponseEntity<ErrorResponse> handleProductError(ProductDuplicateException exception) {
	        return new ResponseEntity<ErrorResponse>(new ErrorResponse(exception.getCode(), exception.getMessage()), HttpStatus.BAD_REQUEST);
	    }

}
