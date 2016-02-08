package com.github.nrudenko.dora.sample.model;

import com.github.nrudenko.dora.annotation.DbColumn;
import com.github.nrudenko.dora.annotation.Table;

@Table
public class Attach {
    int _id;
    String url;
    @DbColumn(additional = "UNIQUE ON CONFLICT REPLACE")
    String messageId;
    public Attach() {}

    public Attach(String url, String messageId) {
        this.url = url;
        this.messageId = messageId;
    }
}
