package com.pw.sign.vo;

import com.pw.sign.entity.BaseEntity;
import lombok.Data;

@Data
public class SearchRequest extends BaseEntity {
    private String key;
}
