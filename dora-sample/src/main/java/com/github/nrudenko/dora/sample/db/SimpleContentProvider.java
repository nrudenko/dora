package com.github.nrudenko.dora.sample.db;

import com.github.nrudenko.dora.android.db.DoraContentProvider;
import com.github.nrudenko.dora.android.db.DoraSQLiteOpenHelper;

public class SimpleContentProvider extends DoraContentProvider {

    @Override
    public DoraSQLiteOpenHelper getDbHelper() {
        return new DatabaseHelper(getContext());
    }
}
