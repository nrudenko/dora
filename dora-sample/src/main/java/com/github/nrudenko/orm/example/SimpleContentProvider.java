package com.github.nrudenko.orm.example;

import com.github.nrudenko.orm.android.db.LikeOrmContentProvider;
import com.github.nrudenko.orm.android.db.LikeOrmSQLiteOpenHelper;

public class SimpleContentProvider extends LikeOrmContentProvider {

    @Override
    public LikeOrmSQLiteOpenHelper getDbHelper() {
        return new DatabaseHelper(getContext());
    }
}
