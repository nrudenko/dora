package fixtures;

import android.content.ContentValues;
import android.database.Cursor;
import com.github.nrudenko.dora.commons.IConverter;
import fixtures.model.Attach;
import fixtures.schemes.AttachScheme;

/* AUTO-GENERATED FILE */
public class Attach$$Converter implements IConverter<Attach> {

    @Override
    public ContentValues to(Attach object) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AttachScheme.URL.name, object.url);
        contentValues.put(AttachScheme.MESSAGE_ID.name, object.messageId);
        return contentValues;
    }

    @Override
    public Attach from(Cursor cursor) {
        Attach object = new Attach();
        object.url = cursor.getString(cursor.getColumnIndex(AttachScheme.URL.name));
        object.messageId = cursor.getString(cursor.getColumnIndex(AttachScheme.MESSAGE_ID.name));
        return object;
    }

}


