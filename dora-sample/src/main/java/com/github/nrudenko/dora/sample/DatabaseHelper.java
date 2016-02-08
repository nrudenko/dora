package com.github.nrudenko.dora.sample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.nrudenko.dora.commons.IScheme;
import com.github.nrudenko.dora.android.db.DoraSQLiteOpenHelper;
import com.github.nrudenko.dora.sample.model.schemes.AttachScheme;
import com.github.nrudenko.dora.sample.model.schemes.ExampleModelScheme;

import java.util.List;

public class DatabaseHelper extends DoraSQLiteOpenHelper {

    public static final String CONTENT_AUTHORITY = "com.githab.nrudenko.orm";

    private static final String DATABASE_NAME = "test.db";
    private static final int DATABASE_VERSION = 1;

    private static DatabaseHelper instance;

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    protected void appendSchemes(List<Class<? extends IScheme>> schemes) {
        schemes.add(AttachScheme.class);
        schemes.add(ExampleModelScheme.class);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
