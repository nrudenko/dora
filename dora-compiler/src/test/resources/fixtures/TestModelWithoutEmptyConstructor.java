package fixtures;

import com.github.nrudenko.dora.annotation.DbColumn;
import com.github.nrudenko.dora.annotation.Table;
import com.github.nrudenko.dora.commons.DbType;

import java.util.Date;

@Table
public class TestModelWithoutEmptyConstructor {

    int field;

    public TestModelWithoutEmptyConstructor(int field){
        this.field = field;
    }

}
