package com.github.nrudenko.dora.compiler.exception;

public class UnknownFieldTypeException extends BaseCompilerException {
    public UnknownFieldTypeException(String type, String name) {
        super("\"" + type + " " + name + "\" unknown field type. Please use IAdapter or add @DbSkipField.");
    }
}
