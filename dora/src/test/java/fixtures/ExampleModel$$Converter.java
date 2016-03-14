package fixtures;

import android.content.ContentValues;
import android.database.Cursor;
import com.github.nrudenko.dora.commons.Adapters.DateAdapter;
import com.github.nrudenko.dora.commons.IConverter;
import fixtures.model.ExampleModel;
import fixtures.schemes.ExampleModelScheme;

/* AUTO-GENERATED FILE */
public class ExampleModel$$Converter implements IConverter<ExampleModel> {
        DateAdapter dateAdapter = new DateAdapter();

    @Override
    public ContentValues to(ExampleModel object) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ExampleModelScheme.TEXT.name, object.text);
        contentValues.put(ExampleModelScheme.DATE.name, dateAdapter.from(object.date));
        contentValues.put(ExampleModelScheme.INT_VAL.name, object.intVal);
        return contentValues;
    }

    @Override
    public ExampleModel from(Cursor cursor) {
        ExampleModel object = new ExampleModel();
        object.text = cursor.getString(cursor.getColumnIndex(ExampleModelScheme.TEXT.name));
        object.date = dateAdapter.to(cursor.getLong(cursor.getColumnIndex(ExampleModelScheme.DATE.name)));
        object.intVal = cursor.getInt(cursor.getColumnIndex(ExampleModelScheme.INT_VAL.name));
        return object;
    }

}


