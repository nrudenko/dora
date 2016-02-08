package fixtures;

import fixtures.schemes.TestModelScheme;
import fixtures.TestModel;
import android.database.Cursor;
import android.content.ContentValues;
import com.github.nrudenko.dora.commons.IConverter;
import com.github.nrudenko.dora.commons.Adapters.BooleanAdapter;
import com.github.nrudenko.dora.commons.Adapters.DateAdapter;
import com.github.nrudenko.dora.commons.Adapters.EnumAdapter;
import fixtures.TestEnum;

/* AUTO-GENERATED FILE */
public class TestModel$$Converter implements IConverter<TestModel> {
    BooleanAdapter boolAdapter = new BooleanAdapter();
    DateAdapter dateAdapter = new DateAdapter();
    EnumAdapter<TestEnum> enumFieldAdapter = new EnumAdapter<TestEnum>(TestEnum.class);

    @Override
    public ContentValues to(TestModel object) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TestModelScheme.TEXT_FIELD.name, object.textField);
        contentValues.put(TestModelScheme.PRIMITIVE_INT_FIELD.name, object.primitiveIntField);
        contentValues.put(TestModelScheme.INT_FIELD.name, object.intField);
        contentValues.put(TestModelScheme.PRIMITIVE_LONG_FIELD.name, object.primitiveLongField);
        contentValues.put(TestModelScheme.LONG_FIELD.name, object.longField);
        contentValues.put(TestModelScheme.PRIMITIVE_SHORT_FIELD.name, object.primitiveShortField);
        contentValues.put(TestModelScheme.SHORT_FIELD.name, object.shortField);
        contentValues.put(TestModelScheme.PRIMITIVE_FLOAT_FIELD.name, object.primitiveFloatField);
        contentValues.put(TestModelScheme.FLOAT_FIELD.name, object.floatField);
        contentValues.put(TestModelScheme.PRIMITIME_DOUBLE_FIELD.name, object.primitimeDoubleField);
        contentValues.put(TestModelScheme.DOUBLE_FIELD.name, object.doubleField);
        contentValues.put(TestModelScheme.PRIMITIVE_BOOL_FIELD.name, boolAdapter.from(object.primitiveBoolField));
        contentValues.put(TestModelScheme.BOOL_FIELD.name, boolAdapter.from(object.boolField));
        contentValues.put(TestModelScheme.BLOB_PRIMITIVE_FIELD.name, object.blobPrimitiveField);
        contentValues.put(TestModelScheme.DATE_FIELD.name, dateAdapter.from(object.dateField));
        contentValues.put(TestModelScheme.ENUM_FIELD.name, enumFieldAdapter.from(object.enumField));
        contentValues.put(TestModelScheme.NAME.name, object.customName);
        contentValues.put(TestModelScheme.CUSTOM_TYPE.name, object.customType);
        contentValues.put(TestModelScheme.CUSTOM_ADDITIONAL.name, object.customAdditional);
        contentValues.put(TestModelScheme.CUSTOM_IS_VIRTUAL.name, object.customIsVirtual);
        return contentValues;
    }

    @Override
    public TestModel from(Cursor cursor) {
        TestModel object = new TestModel();
        object.textField = cursor.getString(cursor.getColumnIndex(TestModelScheme.TEXT_FIELD.name));
        object.primitiveIntField = cursor.getInt(cursor.getColumnIndex(TestModelScheme.PRIMITIVE_INT_FIELD.name));
        object.intField = cursor.getInt(cursor.getColumnIndex(TestModelScheme.INT_FIELD.name));
        object.primitiveLongField = cursor.getLong(cursor.getColumnIndex(TestModelScheme.PRIMITIVE_LONG_FIELD.name));
        object.longField = cursor.getLong(cursor.getColumnIndex(TestModelScheme.LONG_FIELD.name));
        object.primitiveShortField = cursor.getShort(cursor.getColumnIndex(TestModelScheme.PRIMITIVE_SHORT_FIELD.name));
        object.shortField = cursor.getShort(cursor.getColumnIndex(TestModelScheme.SHORT_FIELD.name));
        object.primitiveFloatField = cursor.getFloat(cursor.getColumnIndex(TestModelScheme.PRIMITIVE_FLOAT_FIELD.name));
        object.floatField = cursor.getFloat(cursor.getColumnIndex(TestModelScheme.FLOAT_FIELD.name));
        object.primitimeDoubleField = cursor.getDouble(cursor.getColumnIndex(TestModelScheme.PRIMITIME_DOUBLE_FIELD.name));
        object.doubleField = cursor.getDouble(cursor.getColumnIndex(TestModelScheme.DOUBLE_FIELD.name));
        object.primitiveBoolField = boolAdapter.to(cursor.getInt(cursor.getColumnIndex(TestModelScheme.PRIMITIVE_BOOL_FIELD.name)));
        object.boolField = boolAdapter.to(cursor.getInt(cursor.getColumnIndex(TestModelScheme.BOOL_FIELD.name)));
        object.blobPrimitiveField = cursor.getBlob(cursor.getColumnIndex(TestModelScheme.BLOB_PRIMITIVE_FIELD.name));
        object.dateField = dateAdapter.to(cursor.getLong(cursor.getColumnIndex(TestModelScheme.DATE_FIELD.name)));
        object.enumField = enumFieldAdapter.to(cursor.getString(cursor.getColumnIndex(TestModelScheme.ENUM_FIELD.name)));
        object.customName = cursor.getInt(cursor.getColumnIndex(TestModelScheme.NAME.name));
        object.customType = cursor.getString(cursor.getColumnIndex(TestModelScheme.CUSTOM_TYPE.name));
        object.customAdditional = cursor.getLong(cursor.getColumnIndex(TestModelScheme.CUSTOM_ADDITIONAL.name));
        object.customIsVirtual = cursor.getString(cursor.getColumnIndex(TestModelScheme.CUSTOM_IS_VIRTUAL.name));
        return object;
    }

}
