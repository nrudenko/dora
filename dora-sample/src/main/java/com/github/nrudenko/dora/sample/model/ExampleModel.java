package com.github.nrudenko.dora.sample.model;

import com.github.nrudenko.dora.annotation.Table;

import java.util.Date;

@Table
public class ExampleModel {

    String text;
    Date date;
    int intVal;

    public ExampleModel() {}

    public ExampleModel(String text) {
        this.text = text;
    }
}
