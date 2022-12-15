package com.pastrycertified.cda.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class ObjectValidationException extends RuntimeException{

    @Getter
    private final Set<String> violation;

    @Getter
    private final String violationSource;
}
