package fixtures;

import fixtures.schemes.TestModelScheme;
import fixtures.TestModel;
import android.database.Cursor;
import android.content.ContentValues;
import com.github.nrudenko.dora.commons.IConverter;
import com.github.nrudenko.dora.commons.Adapters.EnumAdapter;
import fixtures.TestEnum;
import com.github.nrudenko.dora.commons.Adapters.DateAdapter;

/* AUTO-GENERATED FILE */
public class TestModel$$Converter implements IConverter<TestModel> {
    EnumAdapter<TestEnum> enumFieldAdapter = new EnumAdapter<TestEnum>(TestEnum.class);
    DateAdapter dateAdapter = new DateAdapter();

    @Override
    public ContentValues to(TestModel object) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TestModelScheme.FIELD.name, object.field);
        contentValues.put(TestModelScheme.NAME.name, object.customName);
        contentValues.put(TestModelScheme.CUSTOM_TYPE.name, object.customType);
        contentValues.put(TestModelScheme.CUSTOM_ADDITIONAL.name, object.customAdditional);
        contentValues.put(TestModelScheme.CUSTOM_IS_VIRTUAL.name, object.customIsVirtual);
        contentValues.put(TestModelScheme.ENUM_FIELD.name, enumFieldAdapter.from(object.enumField));
        contentValues.put(TestModelScheme.DATE_FIELD.name, dateAdapter.from(object.dateField));
        contentValues.put(TestModelScheme.BLOB_FIELD.name, object.blobField);
        return contentValues;
    }

    @Override
    public TestModel from(Cursor cursor) {
        TestModel object = new TestModel();
        object.field = cursor.getString(cursor.getColumnIndex(TestModelScheme.FIELD.name));
        object.customName = cursor.getInt(cursor.getColumnIndex(TestModelScheme.NAME.name));
        object.customType = cursor.getString(cursor.getColumnIndex(TestModelScheme.CUSTOM_TYPE.name));
        object.customAdditional = cursor.getLong(cursor.getColumnIndex(TestModelScheme.CUSTOM_ADDITIONAL.name));
        object.customIsVirtual = cursor.getString(cursor.getColumnIndex(TestModelScheme.CUSTOM_IS_VIRTUAL.name));
        object.enumField = enumFieldAdapter.to(cursor.getString(cursor.getColumnIndex(TestModelScheme.ENUM_FIELD.name)));
        object.dateField = dateAdapter.to(cursor.getLong(cursor.getColumnIndex(TestModelScheme.DATE_FIELD.name)));
        object.blobField = cursor.getBlob(cursor.getColumnIndex(TestModelScheme.BLOB_FIELD.name));
        return object;
    }

}
