package com.github.nrudenko.orm.compiler.exception;

public class UnknownFieldType extends BaseCompilerException {
    public UnknownFieldType(String type, String name) {
        super("\"" + type + " " + name + "\" unknown field type. Please use IAdapter or add @DbSkipField.");
    }
}
