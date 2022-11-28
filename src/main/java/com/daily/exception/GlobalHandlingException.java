package com.daily.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlingException {

    //yoktur değer olduğunda sporing yakalayacak
    @ExceptionHandler({HamitMizrakException.class})
    public String handlingNotFoundException(){
        return "Böyle bir data yoktur.";
    }


    //null değer olduğunda sporing yakalayacak
    @ExceptionHandler({NullPointerException.class})
    public String handlingNullPointerException(){
        return "null değer girildi";
    }
}
