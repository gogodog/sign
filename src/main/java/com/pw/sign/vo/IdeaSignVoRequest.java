package com.pw.sign.vo;

import lombok.Data;

@Data
public class IdeaSignVoRequest {

    private Integer ideaId;
    private String lastName;
    private String firstName;
    private String signType;
    private String base64;

}
