package com.pastrycertified.cda.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OperationNoPermittedException extends RuntimeException {

    private final String errorMsg;

    private final String OperationId;

    private final String source;

    private final String dependency;
}
