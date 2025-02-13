package com.example.exception;

import com.example.payload.ReasonDto;
import com.example.payload.status.BaseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private final BaseStatus status;

    public ReasonDto getErrorReasonHttpStatus(){
        return this.status.getReasonHttpStatus();
    }
}