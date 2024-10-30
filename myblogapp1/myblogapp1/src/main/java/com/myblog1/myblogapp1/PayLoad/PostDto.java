package com.myblog1.myblogapp1.PayLoad;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PostDto {


    public long id;
    @NotNull
    @Size(min = 4,message = "Post title should not be less than 4 characters")
    public String title;
    @NotNull
    @Size(min = 10,message = "Post description should not be less than 10 characters")
    public String description;
    @NotNull
    @NotEmpty
    public String content;
}
