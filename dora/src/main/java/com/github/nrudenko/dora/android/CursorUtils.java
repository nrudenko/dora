package com.github.nrudenko.dora.android;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class CursorUtils {

    private CursorUtils() {
    }

    private static <T> T innerCursorToObject(Cursor cursor, Class<T> modelClass) {
        return buildModel(modelClass, cursor);
    }

    private static <T> T buildModel(Class<T> modelClass, Cursor cursor) {
        T model = null;
        return model;
    }

    public static <T> T cursorToObject(Cursor cursor, Class<T> modelClass) {
        if (isCursorEmpty(cursor)) return null;
        return innerCursorToObject(cursor, modelClass);
    }

    public static <T> ArrayList<T> cursorToList(Cursor cursor, Class<T> modelClass) {
        ArrayList<T> items = new ArrayList<T>();
        if (!isCursorEmpty(cursor)) {
            while (cursor.moveToNext()) {
                final T model = buildModel(modelClass, cursor);
                items.add(model);
            }
        }
        return items;
    }

    public static boolean isCursorEmpty(Cursor cursor) {
        return cursor == null || cursor.getCount() == 0;
    }

    public static ContentValues[] listToContentValuesArray(List items) {
        ContentValues[] contentValues = new ContentValues[items.size()];
        // TODO sync for items?
        for (int i = 0; i < items.size(); i++) {
            Object o = items.get(i);
            contentValues[i] = objectToContentValues(o);
        }
        return contentValues;
    }

    public static ContentValues objectToContentValues(Object model) {
        ContentValues contentValues = null;
        return contentValues;
    }

    /**
     * Close cursor if it's not <code>null</code>.
     *
     * @return <code>true</code> if closed, <code>false</code> if was <code>null</code>
     */
    public static boolean closeSafely(Cursor cursor) {
        if (cursor == null) {
            return false;
        } else {
            cursor.close();
            return true;
        }
    }
}
