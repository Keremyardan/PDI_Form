package com.reysas_pdi.backend.core.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
