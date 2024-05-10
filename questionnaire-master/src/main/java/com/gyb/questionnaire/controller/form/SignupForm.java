package com.gyb.questionnaire.controller.form;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupForm {
    @NotBlank(message = "The username cannot be empty")
    @Length(min = 1,max = 20,message = "The username is too long")
    private String username;
    @NotBlank(message = "Password cannot be empty")
    private String password;
    @NotBlank(message = "The verification code is empty")
    private String code;
}
