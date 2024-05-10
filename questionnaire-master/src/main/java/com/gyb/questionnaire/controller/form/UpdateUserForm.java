package com.gyb.questionnaire.controller.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 修改用户信息Form
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserForm {
    private String username;
    private String realName;
    @Range(min = 0,max = 100,message = "The entered age is illegal")
    private int age;
    @Range(min = 0,max = 1)
    private int sex;
}
