package fixtures.schemes;

import com.github.nrudenko.dora.commons.Column;
import com.github.nrudenko.dora.commons.IScheme;
import com.github.nrudenko.dora.commons.DbType;

/* AUTO-GENERATED FILE */
public final class TestModelScheme implements IScheme {

    public static final String TABLE = "test_model";

    public static final Column _ID = new Column("test_model._id", DbType.INT_DEFAULT_ZERO, "PRIMARY KEY AUTOINCREMENT");
    public static final Column TEXT_FIELD = new Column("test_model.textField", DbType.TEXT);
    public static final Column PRIMITIVE_INT_FIELD = new Column("test_model.primitiveIntField", DbType.INT_DEFAULT_ZERO);
    public static final Column INT_FIELD = new Column("test_model.intField", DbType.INT_DEFAULT_ZERO);
    public static final Column PRIMITIVE_LONG_FIELD = new Column("test_model.primitiveLongField", DbType.NUMERIC);
    public static final Column LONG_FIELD = new Column("test_model.longField", DbType.NUMERIC);
    public static final Column PRIMITIVE_SHORT_FIELD = new Column("test_model.primitiveShortField", DbType.INT_DEFAULT_ZERO);
    public static final Column SHORT_FIELD = new Column("test_model.shortField", DbType.INT_DEFAULT_ZERO);
    public static final Column PRIMITIVE_FLOAT_FIELD = new Column("test_model.primitiveFloatField", DbType.REAL);
    public static final Column FLOAT_FIELD = new Column("test_model.floatField", DbType.REAL);
    public static final Column PRIMITIME_DOUBLE_FIELD = new Column("test_model.primitimeDoubleField", DbType.REAL);
    public static final Column DOUBLE_FIELD = new Column("test_model.doubleField", DbType.REAL);
    public static final Column PRIMITIVE_BOOL_FIELD = new Column("test_model.primitiveBoolField", DbType.INT_DEFAULT_ZERO);
    public static final Column BOOL_FIELD = new Column("test_model.boolField", DbType.INT_DEFAULT_ZERO);
    public static final Column BLOB_PRIMITIVE_FIELD = new Column("test_model.blobPrimitiveField", DbType.BLOB);
    public static final Column DATE_FIELD = new Column("test_model.dateField", DbType.NUMERIC);
    public static final Column ENUM_FIELD = new Column("test_model.enumField", DbType.TEXT);
    public static final Column NAME = new Column("test_model.name", DbType.INT_DEFAULT_ZERO);
    public static final Column CUSTOM_TYPE = new Column("test_model.customType", DbType.TEXT_NOT_NULL);
    public static final Column CUSTOM_ADDITIONAL = new Column("test_model.customAdditional", DbType.NUMERIC, "ON " +
            "CONFLICT IGNORE");
    public static final Column CUSTOM_IS_VIRTUAL = new Column("test_model.customIsVirtual", DbType.TEXT);

    @Override
    public String getCreateSql() {
        return "CREATE TABLE IF NOT EXISTS test_model (_id INTEGER DEFAULT 0 PRIMARY KEY AUTOINCREMENT,textField TEXT,primitiveIntField INTEGER DEFAULT 0,intField INTEGER DEFAULT 0,primitiveLongField NUMERIC,longField NUMERIC,primitiveShortField INTEGER DEFAULT 0,shortField INTEGER DEFAULT 0,primitiveFloatField REAL,floatField REAL,primitimeDoubleField REAL,doubleField REAL,primitiveBoolField INTEGER DEFAULT 0,boolField INTEGER DEFAULT 0,blobPrimitiveField BLOB,dateField NUMERIC,enumField TEXT,name INTEGER DEFAULT 0 ,customType TEXT NOT NULL ,customAdditional NUMERIC ON CONFLICT IGNORE,customIsVirtual TEXT );";
    }
}