package com.lucky.eduadmin.ResponseTemplate;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PagePara {
    private long current;
    private long size;
}
