package fixtures.schemes;

import com.github.nrudenko.dora.commons.Column;
import com.github.nrudenko.dora.commons.DbType;
import com.github.nrudenko.dora.commons.IScheme;

/* AUTO-GENERATED FILE */
public final class AttachScheme implements IScheme{

    public static final String TABLE = "attach";

    public static final Column _ID = new Column("attach._id", DbType.INT_DEFAULT_ZERO, "PRIMARY KEY AUTOINCREMENT");
    public static final Column URL = new Column("attach.url", DbType.TEXT);
    public static final Column MESSAGE_ID = new Column("attach.messageId", DbType.TEXT, "UNIQUE ON CONFLICT REPLACE");

    @Override
    public String getCreateSql(){
        return "CREATE TABLE IF NOT EXISTS attach (_id INTEGER DEFAULT 0 PRIMARY KEY AUTOINCREMENT,url TEXT,messageId TEXT UNIQUE ON CONFLICT REPLACE);";
    }
}

