package com.example.exception;

import com.example.payload.status.BaseStatus;

public class TempHandler extends GeneralException {

    public TempHandler(BaseStatus status) {
        super(status);
    }
}