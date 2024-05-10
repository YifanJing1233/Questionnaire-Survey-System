package com.gyb.questionnaire.controller.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
    @NotBlank(message = "The username cannot be empty")
    private String username;
    @NotBlank(message = "Password cannot be empty")
    private String password;
    private String code;
}
