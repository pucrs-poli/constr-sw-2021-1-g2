package br.com.pucrs.resources.src.handler;

import br.com.pucrs.resources.src.domain.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse<String>> handlerException(Exception e) {
        ErrorResponse<String> response = new ErrorResponse<>();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setData(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
