package supply.master.productcateloug.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import supply.master.productcateloug.dto.ErrorResponse;
import supply.master.productcateloug.exception.SPMException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        ErrorResponse errorResponse = gerErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SPMException.class)
    public ResponseEntity<ErrorResponse> handleSPMException(SPMException ex) {
        ErrorResponse errorResponse = ex.getErrorResponse();
        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(ex.getErrorResponse().getStatus()));
    }

    public ErrorResponse gerErrorResponse(HttpStatus status, String message){
        return new ErrorResponse(status.value(),status.getReasonPhrase(),message);
    }

}
