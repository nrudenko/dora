package com.github.nrudenko.orm.example;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.github.nrudenko.orm.example.model.Attach;
import com.github.nrudenko.orm.example.model.Attach$$Converter;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insert();
        query();
        update();
        delete();
    }

    private void delete() {
    }

    private void query() {

    }

    private void insert() {

    }

    private void update() {

    }

}
