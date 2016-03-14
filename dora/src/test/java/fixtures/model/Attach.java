package fixtures.model;

import com.github.nrudenko.dora.annotation.DbColumn;
import com.github.nrudenko.dora.annotation.Table;

@Table
public class Attach {
    public int _id;
    public String url;
    @DbColumn(additional = "UNIQUE ON CONFLICT REPLACE")
    public String messageId;

    public Attach() {}

    public Attach(String url, String messageId) {
        this.url = url;
        this.messageId = messageId;
    }
}
