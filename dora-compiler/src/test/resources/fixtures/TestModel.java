package fixtures;

import com.github.nrudenko.dora.annotation.DbColumn;
import com.github.nrudenko.dora.annotation.Table;
import com.github.nrudenko.dora.commons.DbType;

import java.util.Date;

@Table()
public class TestModel {
    String textField;

    int primitiveIntField;
    Integer intField;

    long primitiveLongField;
    Long longField;

    short primitiveShortField;
    Short shortField;

    float primitiveFloatField;
    Float floatField;

    double primitimeDoubleField;
    Double doubleField;

    boolean primitiveBoolField;
    Boolean boolField;

    byte[] blobPrimitiveField;

    Date dateField;

    TestEnum enumField;

    @DbColumn(name = "name")
    int customName;

    @DbColumn(type = DbType.TEXT_NOT_NULL)
    String customType;

    @DbColumn(additional = "ON CONFLICT IGNORE")
    long customAdditional;

    @DbColumn(isVirtual = true)
    String customIsVirtual;

    public TestModel() {

    }

}
