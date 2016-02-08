package com.github.nrudenko.dora.compiler.exception;

public class AbsentConstructorException extends BaseCompilerException {
    public AbsentConstructorException() {
        super("Table model should have an empty constructor.");
    }
}
