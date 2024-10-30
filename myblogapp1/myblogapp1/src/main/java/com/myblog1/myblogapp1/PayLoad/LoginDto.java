package com.myblog1.myblogapp1.PayLoad;

import lombok.Data;

@Data
public class LoginDto {

    private String usernameOrEmail;
    private String password;
}
