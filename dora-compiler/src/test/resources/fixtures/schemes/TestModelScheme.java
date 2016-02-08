package fixtures.schemes;

import com.github.nrudenko.dora.commons.Column;
import com.github.nrudenko.dora.commons.IScheme;
import com.github.nrudenko.dora.commons.DbType;

/* AUTO-GENERATED FILE */
public final class TestModelScheme implements IScheme{

    public static final String TABLE = "test_model";

    public static final Column _ID = new Column("test_model._id", DbType.INT, "PRIMARY KEY AUTOINCREMENT");
    public static final Column FIELD = new Column("test_model.field", DbType.TEXT);
    public static final Column NAME = new Column("test_model.name", DbType.INT);
    public static final Column CUSTOM_TYPE = new Column("test_model.customType", DbType.TEXT_NOT_NULL);
    public static final Column CUSTOM_ADDITIONAL = new Column("test_model.customAdditional", DbType.NUMERIC, "ON " +
            "CONFLICT IGNORE");
    public static final Column CUSTOM_IS_VIRTUAL = new Column("test_model.customIsVirtual", DbType.TEXT);
    public static final Column ENUM_FIELD = new Column("test_model.enumField", DbType.TEXT);
    public static final Column DATE_FIELD = new Column("test_model.dateField", DbType.NUMERIC);
    public static final Column BLOB_FIELD = new Column("test_model.blobField", DbType.BLOB);

    @Override
    public String getCreateSql(){
        return "CREATE TABLE IF NOT EXISTS test_model (_id INTEGER DEFAULT 0 PRIMARY KEY AUTOINCREMENT,field TEXT," +
                "name INTEGER DEFAULT 0 ,customType TEXT NOT NULL ,customAdditional NUMERIC ON CONFLICT IGNORE,customIsVirtual TEXT ,enumField TEXT,dateField NUMERIC,blobField BLOB);";
    }
}
