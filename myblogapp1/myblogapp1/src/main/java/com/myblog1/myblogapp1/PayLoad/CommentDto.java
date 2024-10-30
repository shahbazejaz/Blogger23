package com.myblog1.myblogapp1.PayLoad;

import lombok.Data;

@Data
public class CommentDto {
    private long id;
    private String body;
    private String email;
    private String name;
}
