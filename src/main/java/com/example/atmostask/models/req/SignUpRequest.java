package com.example.atmostask.models.req;

import com.example.atmostask.models.custom.ResponseMessages;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignUpRequest {

    @NotBlank(message = ResponseMessages.USER_NAME_REQ)
    private String username;

    @NotBlank(message = ResponseMessages.EMAIL_REQ)
    @Email(message = ResponseMessages.NOT_VALID_EMAIL_REQ)
    private String email;

    @Size(min = 5, max = 16, message = ResponseMessages.NOT_VALID_PASSWORD_REQ)
    private String password;

    @Pattern(regexp = "[0-9]+", message = ResponseMessages.ONLY_NUMBERS)
    private String age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
