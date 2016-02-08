package com.github.nrudenko.orm.commons;

import android.content.ContentValues;
import android.database.Cursor;

public interface IConverter<T> {
    ContentValues to(T object);

    T from(Cursor cursor);
}
