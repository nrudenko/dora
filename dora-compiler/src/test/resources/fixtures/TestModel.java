package fixtures;

import com.github.nrudenko.dora.annotation.DbColumn;
import com.github.nrudenko.dora.annotation.Table;
import com.github.nrudenko.dora.commons.DbType;

import java.util.Date;

@Table()
public class TestModel {
    String field;
    @DbColumn(name = "name")
    int customName;
    @DbColumn(type = DbType.TEXT_NOT_NULL)
    String customType;
    @DbColumn(additional = "ON CONFLICT IGNORE")
    long customAdditional;
    @DbColumn(isVirtual = true)
    String customIsVirtual;
    TestEnum enumField;
    Date dateField;
    byte[] blobField;

    public TestModel() {

    }

}
