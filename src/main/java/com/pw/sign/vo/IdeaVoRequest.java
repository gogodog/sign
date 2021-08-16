package com.pw.sign.vo;

import lombok.Data;

import java.util.List;

@Data
public class IdeaVoRequest {

    private String desc;
    private List<String> steps;
    private List<String> tags;

}
