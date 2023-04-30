package com.obifyconsulting.cmsbe.exception;

import com.obifyconsulting.cmsbe.dto.ErrorDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Log4j2
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDTO>> handleFieldValidation(MethodArgumentNotValidException manv){

        List<ErrorDTO> errorList = new ArrayList<>();
        ErrorDTO errorDTO = null;
        List<FieldError> fieldErrorList = manv.getBindingResult().getFieldErrors();

        for(FieldError fe: fieldErrorList){
            log.debug("Inside field validation: {} - {}", fe.getField(), fe.getDefaultMessage());
            log.info("Inside field validation: {} - {}", fe.getField(), fe.getDefaultMessage());
            errorDTO = new ErrorDTO();
            errorDTO.setCode(fe.getField());
            errorDTO.setMessage(fe.getDefaultMessage());
            errorList.add(errorDTO);
        }

        return new ResponseEntity<List<ErrorDTO>>(errorList, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorDTO>> handleBusinessException(BusinessException bex){
        for(ErrorDTO em: bex.getErrors()){
            log.debug("BusinessException is thrown - level- debug: {} - {}", em.getCode(), em.getMessage());
            log.info("BusinessException is thrown - level- info: {} - {}", em.getCode(), em.getMessage());
            log.warn("BusinessException is thrown - level-warn: {} - {}", em.getCode(), em.getMessage());
            log.error("BusinessException is thrown - level-error: {} - {}", em.getCode(), em.getMessage());
        }
        return new ResponseEntity<List<ErrorDTO>>(bex.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
