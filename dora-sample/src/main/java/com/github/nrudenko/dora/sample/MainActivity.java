package com.github.nrudenko.dora.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.github.nrudenko.dora.example.R;


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
