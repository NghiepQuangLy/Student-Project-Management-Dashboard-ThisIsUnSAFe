package edu.monash.userprojectservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@Component
public class ExceptionsHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> handle(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError ->  fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
    }
}
