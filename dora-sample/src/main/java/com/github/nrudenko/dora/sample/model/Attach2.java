package com.github.nrudenko.dora.sample.model;

import com.github.nrudenko.dora.annotation.DbColumn;
import com.github.nrudenko.dora.annotation.Table;

@Table
public class Attach2 {
    String url2;
    @DbColumn(additional = "UNIQUE ON CONFLICT REPLACE")
    String messageId2;
}
