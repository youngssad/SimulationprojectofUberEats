package com.example.simulationproject.common;


//take care the exception

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class GlobalExceptionHandler {
    //employee name is non-repeatable, if we try to add new employee with repeated name, exception throw out
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){

        //find the duplicated name
        if(ex.getMessage().contains("Duplicate entry")){
            String[] split= ex.getMessage().split(" ");
            String msg= split[2] +"already exist";
            return R.error(msg);

        }

        return R.error("unknown error");

    }

    //Handler CustomException
    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException ex){

        return R.error(ex.getMessage());

    }
}
