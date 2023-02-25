package com.pastrycertified.cda.models;

public enum GenerateCharactersInvoice {
    CHARACTERS("abcdefghijklmnopqrstuvwxyz"),
    NUMBERS("0123456789");

    private String value;

    GenerateCharactersInvoice(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
