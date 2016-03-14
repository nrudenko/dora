package fixtures.model;

import com.github.nrudenko.dora.annotation.Table;

import java.util.Date;

@Table
public class ExampleModel {

    public String text;
    public Date date;
    public int intVal;

    public ExampleModel() {}

    public ExampleModel(String text) {
        this.text = text;
    }
}
