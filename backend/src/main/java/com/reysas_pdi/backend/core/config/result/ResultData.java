package com.reysas_pdi.backend.core.config.result;

import lombok.Getter;

@Getter
public class ResultData<T> extends Result {
    private T data;

    public  ResultData(boolean status, String message, String httpCode) {
        super(status, message,httpCode);
        this.data = data;
    }

    public  boolean isSuccess() {
        return super.isStatus();
    }

}
