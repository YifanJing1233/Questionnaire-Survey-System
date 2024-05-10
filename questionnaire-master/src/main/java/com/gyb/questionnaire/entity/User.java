package com.gyb.questionnaire.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * 用户实体
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String username;
    private String password;
    private String passwordSalt;
    private String realName;
    private String phone;
    private String email;
    private int age;
    private int sex;
    private int isVip;
    private Date createDate;
    private int status;
}
