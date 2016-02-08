package com.github.nrudenko.orm.example.model;

import com.github.nrudenko.orm.annotation.DbColumn;
import com.github.nrudenko.orm.annotation.Table;

@Table
public class Attach2 {
    String url2;
    @DbColumn(additional = "UNIQUE ON CONFLICT REPLACE")
    String messageId2;
}
