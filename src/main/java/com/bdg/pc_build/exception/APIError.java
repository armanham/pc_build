package com.bdg.pc_build.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class APIError {

    private String errorMessage;

    private String errorCode;

    private String request;

    private String requestType;

    private String dateAndTime;
}