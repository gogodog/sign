package com.pw.sign.vo;

import lombok.Data;

import javax.swing.text.html.HTML;
import java.util.List;

@Data
public class IdeaVoRequest {

    private String desc;
    private List<String> steps;
    private List<Tag> tags;
    private String title;

    @Data
    public static class Tag {
        private String id;
        private String cnName;
    }
}
