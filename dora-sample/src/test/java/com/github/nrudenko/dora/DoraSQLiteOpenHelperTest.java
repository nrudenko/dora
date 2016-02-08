package com.github.nrudenko.dora;

import android.database.sqlite.SQLiteDatabase;

import com.github.nrudenko.dora.sample.DatabaseHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;

import static org.mockito.Mockito.verify;

@RunWith(DoraTestRunner.class)
public class DoraSQLiteOpenHelperTest {

    private DatabaseHelper dbHelper;
    @Mock
    private SQLiteDatabase db;

    @Before
    public void setUp() throws Exception {
        dbHelper = new DatabaseHelper(Robolectric.getShadowApplication().getApplicationContext());
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnCreate() throws Exception {
        dbHelper.onCreate(db);
        verify(db).execSQL("CREATE TABLE IF NOT EXISTS attach (_id INTEGER DEFAULT 0 PRIMARY KEY AUTOINCREMENT," +
                "url TEXT,messageId TEXT UNIQUE ON CONFLICT REPLACE);" +
                "CREATE TABLE IF NOT EXISTS example_model (_id INTEGER DEFAULT 0 PRIMARY KEY " +
                "AUTOINCREMENT,text TEXT,date NUMERIC,intVal INTEGER DEFAULT 0);"
        );
    }
}