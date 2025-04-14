package com.reysas_pdi.backend.core.exceptions;

public class DataAbsentException  extends  RuntimeException{
    public DataAbsentException(String message, Throwable err){
        super(message,err);
    }
}
