package fixtures.schemes;

import com.github.nrudenko.dora.commons.Column;
import com.github.nrudenko.dora.commons.DbType;
import com.github.nrudenko.dora.commons.IScheme;

/* AUTO-GENERATED FILE */
public final class ExampleModelScheme implements IScheme{

    public static final String TABLE = "example_model";

    public static final Column _ID = new Column("example_model._id", DbType.INT_DEFAULT_ZERO, "PRIMARY KEY AUTOINCREMENT");
    public static final Column TEXT = new Column("example_model.text", DbType.TEXT);
    public static final Column DATE = new Column("example_model.date", DbType.NUMERIC);
    public static final Column INT_VAL = new Column("example_model.intVal", DbType.INT_DEFAULT_ZERO);

    @Override
    public String getCreateSql(){
        return "CREATE TABLE IF NOT EXISTS example_model (_id INTEGER DEFAULT 0 PRIMARY KEY AUTOINCREMENT,text TEXT,date NUMERIC,intVal INTEGER DEFAULT 0);";
    }
}

