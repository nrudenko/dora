package com.github.nrudenko.dora.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.github.nrudenko.dora.commons.IScheme;

import java.util.ArrayList;
import java.util.List;

public abstract class DoraSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String TAG = DoraSQLiteOpenHelper.class.getSimpleName();

    public static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS ";

    public DoraSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    protected abstract void appendSchemes(List<Class<? extends IScheme>> schemes);

    private List<Class<? extends IScheme>> getSchemesClasses() {
        List<Class<? extends IScheme>> schemes = new ArrayList<Class<? extends IScheme>>();
        appendSchemes(schemes);
        return schemes;
    }

    protected void createTables(SQLiteDatabase db) {
        List<Class<? extends IScheme>> schemesClasses = getSchemesClasses();

        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < schemesClasses.size(); i++) {
            Class<? extends IScheme> aClass = schemesClasses.get(i);
            String createSql = null;
            try {
                createSql = aClass.newInstance().getCreateSql();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Log.i(TAG, createSql);
            sql.append(createSql);
        }
        db.execSQL(sql.toString());
    }

    protected void dropTables(SQLiteDatabase db) {
        List<Class<? extends IScheme>> schemesClasses = getSchemesClasses();

        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < schemesClasses.size(); i++) {
            sql.setLength(0);
            //TODO: drop tables
//            Class<? extends IScheme> aClass = schemesClasses.get(i);
//            sql.append(DROP_TABLE_IF_EXISTS).append(schemeWrapper.getTableName()).append(";");
            db.execSQL(sql.toString());
        }
    }

}
